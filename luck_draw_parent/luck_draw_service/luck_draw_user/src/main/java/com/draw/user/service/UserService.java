package com.draw.user.service;

import com.draw.user.pojo.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<String> findJoinUserHead(String id);

    User findUser(String id);
}
