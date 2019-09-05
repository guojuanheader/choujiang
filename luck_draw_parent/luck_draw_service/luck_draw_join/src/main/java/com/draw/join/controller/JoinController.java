package com.draw.join.controller;


import com.draw.entity.Result;
import com.draw.entity.StatusCode;
import com.draw.join.service.JoinService;
import com.draw.join.util.TokenDecode;
import com.draw.user.feign.UserFeign;
import com.draw.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/join")
public class JoinController {

    @Autowired
    private TokenDecode  tokenDecode;
    @Autowired
    private UserFeign  userFeign;
    @Autowired
    private JoinService  joinService;
    @RequestMapping("/add")
    public Result  add(@RequestParam("did") String did){
        String username = tokenDecode.getUserInfo().get("username");
        User user = userFeign.loadUserInfo(username);
        String uid = user.getId();
        joinService.add(uid,did);
        return new Result(true, StatusCode.OK,"参加抽奖成功");
    }
}
