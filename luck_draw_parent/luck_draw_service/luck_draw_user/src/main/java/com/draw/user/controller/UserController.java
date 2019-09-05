package com.draw.user.controller;


import com.draw.entity.Result;
import com.draw.user.pojo.User;
import com.draw.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService  userService;
    @RequestMapping("/findByUsername")
    public User   findByUsername(@RequestParam("username") String username){
        User  user=userService.findByUsername(username);
        return user;
    }


    @RequestMapping("/findJoinUserHead")
    public List<String> findJoinUserHead(String id){
        List<String>  headers=userService.findJoinUserHead(id);
        return headers;
    }
}
