package com.draw.infos.service.impl;


import com.draw.infos.dao.UserAndDrawMapper;
import com.draw.infos.service.UserAndDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAndDrawServiceImpl implements UserAndDrawService {

    @Autowired
    private UserAndDrawMapper  userAndDrawMapper;
    @Override
    public String findUid(Long code, String did) {
        return userAndDrawMapper.findUid(code,did);
    }
}
