package com.draw.join.service.impl;

import com.alibaba.fastjson.JSON;
import com.draw.infos.feign.InfoFeign;
import com.draw.infos.pojo.Info;
import com.draw.join.service.JoinService;
import com.draw.infos.dao.UserAndDrawMapper;
import com.draw.infos.pojo.UserAndDraw;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

//这个对象在哪儿创建得是不是在@Autowire那里  在你服务启动后 这个类只会被创建一次 也就是无论你是几个人来
//调用这个类得方法  始终都是同一个实例  （new JoinServiceImpl（）就是一个实例 这个实例加了被spring管理得）
@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserAndDrawMapper userAndDrawMapper;
    @Autowired
    private InfoFeign infoFeign;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //spring 管理得对象（JoinServiceImpl）只会创建1次 那么code就只会初始化1次


    long code = 10000;

    public long getCode() {//成员方法
        synchronized (this) {
            return code++;
        }
    }

    /**
     * 有人参加抽奖
     *
     * @param uid
     * @param did
     */
    //同一个抽奖单2个用户分别点2次 是不是调用了4次方法
    @Override
    public void add(String uid, String did) {
        //判断是否已开奖
        Object data = infoFeign.findById(did).getData();
        Map<String, Object> map = (Map) data;
        Info info = JSON.parseObject(JSON.toJSONString(map.get("info")), Info.class);
        if (info.getOpenTime().before(new Date())) {
            throw new RuntimeException("该抽奖已不能参与");
        }
        //查询user和draw关联表看是否参加过抽奖
        Object drawId = redisTemplate.boundHashOps(uid).get(did);
        if (drawId != null) {
            throw new RuntimeException("参加过该抽奖");
        } else {
            redisTemplate.boundHashOps(uid).put(did, "0");
        }
        //join
        Long code = redisTemplate.boundHashOps("code").increment(did, 1);
        //code +=10000;
        //long mycode = getCode();//这儿走1次
        // redisTemplate.boundHashOps(uid).put(did, code);
        //user和draw关联表增加数据，user得抽奖码
        UserAndDraw userAndDraw = new UserAndDraw();
        userAndDraw.setId(UUID.randomUUID().toString());
        //不能这么干   uuid 是什么了 一堆字符串
        userAndDraw.setUid(uid);
        userAndDraw.setDid(did);
        userAndDraw.setDrawCode(code + 10000);
        userAndDrawMapper.insertTeam(userAndDraw);
        //draw  人数+1
        infoFeign.update(did);
        int joinNum = info.getJoinNum() + 1;
        if (joinNum>=info.getOpenNum()){
            rabbitTemplate.convertAndSend("","openNum_draw","_");
        }

    }

}
