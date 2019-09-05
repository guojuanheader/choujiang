package com.draw.infos.service.impl;

import com.draw.infos.dao.InfoMapper;
import com.draw.infos.dao.UserAndDrawMapper;
import com.draw.infos.pojo.Info;
import com.draw.infos.service.InfoService;
import com.draw.infos.util.TokenDecode;
import com.draw.user.feign.UserFeign;
import com.draw.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoMapper infoMapper;


    @Override
    public Info findById(String id) {
        Info info = infoMapper.findById(id);

        return info;
    }

    @Override
    public void update(String did) {
        infoMapper.update(did);
    }

    @Override
    public List<Info> findAll() {

        return infoMapper.findAll();
    }


}
