package com.draw.open.service.impl;


import com.alibaba.fastjson.JSON;
import com.draw.infos.feign.InfoFeign;
import com.draw.infos.pojo.Info;
import com.draw.open.dao.OpenMapper;
import com.draw.open.service.OpenService;
import com.draw.user.feign.UserFeign;
import com.draw.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class OpenServiceImpl implements OpenService {


    @Autowired
    private InfoFeign  infoFeign;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private OpenMapper  openMapper;
    @Override
    public void open() {

        String num="111";
        log.info("open  method  start num:{}",num);
        Date date = new Date();
        Object data = infoFeign.findAll().getData();
        String s = JSON.toJSONString(data);
        List<Info> infos = JSON.parseArray(s,Info.class);
        for (Info info : infos) {
            if (info.getOpenTime().getTime()<= date.getTime()||info.getOpenNum()==info.getJoinNum()){
                //开奖
                Random random = new Random();
                Long randNum = Long.valueOf(random.nextInt((info.getJoinNum().intValue()+10000) - 10001 + 1)+10001);
                String uid = infoFeign.findUid(randNum, info.getId()).getData().toString();
                User user = JSON.parseObject(JSON.toJSONString(userFeign.findUser(uid).getData()), User.class);
                System.out.println("中将得user是"+user);
                //修改抽奖项为已抽奖
                infoFeign.updateOpen(info.getId(),randNum);
                //中奖表里添加数据
                String id = UUID.randomUUID().toString();
                int win_num = Integer.parseInt(String.valueOf(randNum));
                openMapper.insertWin(id,user.getId(),info.getId(),win_num);
            }
        }
    }


}
