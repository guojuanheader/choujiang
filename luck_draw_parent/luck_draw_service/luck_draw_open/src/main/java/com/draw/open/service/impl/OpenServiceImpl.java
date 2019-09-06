package com.draw.open.service.impl;


import com.alibaba.fastjson.JSON;
import com.draw.entity.Result;
import com.draw.infos.feign.InfoFeign;
import com.draw.infos.pojo.Info;
import com.draw.open.service.OpenService;
import com.draw.user.feign.UserFeign;
import com.draw.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OpenServiceImpl implements OpenService {

    @Autowired
    private InfoFeign  infoFeign;
    @Autowired
    private UserFeign userFeign;
    @Override
    public void open() {
        Date date = new Date();
        List<Info> infos = (List<Info>) infoFeign.findAll().getData();
        for (Info info : infos) {
            if (info.getOpenTime().before(date)||info.getOpenNum()==info.getJoinNum()){
                //开奖
                Random random = new Random();
                Long randNum = Long.valueOf(random.nextInt(info.getJoinNum() - 10001 + 1)+10001);
                String uid = infoFeign.findUid(randNum, info.getId()).getData().toString();
                User user = JSON.parseObject(JSON.toJSONString(userFeign.findUser(uid).getData()), User.class);
                System.out.println("中将得user是"+user);

                infoFeign.updateOpen(info.getId());
            }
        }
    }
}
