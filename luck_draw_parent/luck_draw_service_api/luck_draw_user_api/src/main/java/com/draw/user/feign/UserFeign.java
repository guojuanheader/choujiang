package com.draw.user.feign;


import com.draw.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "user")
public interface UserFeign {


    @RequestMapping("/user/findByUsername")
    public User   loadUserInfo( @RequestParam("username") String username);


    @RequestMapping("/user/findJoinUserHead")
    public List<String> findJoinUserHead(String id);
 }
