package com.draw.infos.controller;


import com.draw.entity.Result;
import com.draw.entity.StatusCode;
import com.draw.infos.service.UserAndDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAndDraw")
public class UserAndDrawController {

    @Autowired
    private UserAndDrawService  userAndDrawService;

    @RequestMapping("/findUid")
    public Result findUid(@RequestParam("code")Long code, @RequestParam("did")String  did){
        String  uid=userAndDrawService.findUid(code, did);
        return new Result(true, StatusCode.OK,"查询成功",uid);
    }
}
