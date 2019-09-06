package com.draw.user.service.impl;

import com.draw.user.dao.UserMapper;
import com.draw.user.pojo.User;
import com.draw.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class UserServiceImpl  implements UserService {
    
    @Autowired
    private UserMapper  userMapper;
    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user;
    }

    @Override
    public List<String> findJoinUserHead(String id) {
        List<String> heads=userMapper.findJoinUserHead(id);
        return heads;
    }

    @Override
    public User findUser(String id) {
        return userMapper.findUser(id);
    }
}
