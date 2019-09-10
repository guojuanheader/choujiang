package com.draw.infos.controller;


import com.draw.entity.Result;
import com.draw.entity.StatusCode;
import com.draw.infos.pojo.Info;
import com.draw.infos.service.InfoService;
import com.draw.user.feign.UserFeign;
import com.draw.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/info")
public class InfoController {


    @Autowired
    private InfoService infoService;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/findById")
    public Result findById(@RequestParam("id") String id,@RequestParam(value = "uid",required = false) String  uid) {
        Map<String,Object> map = new HashMap<>();
        Info info = infoService.findById(id);
        //String uid = tokenDecode.getUserInfo().get("id");
        //拆开
        if (!StringUtils.isEmpty(uid)) {
            BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(uid);
            Object o = boundHashOperations.get(id);
            //所以这里o 为null 正常
            if (o == null) {
                map.put("result", "未抽奖");
            } else {
                map.put("result", "已抽奖");
            }
        }
        map.put("info", info);

        return new Result(true, StatusCode.OK, "查找成功", map);
    }

    /**
     * 查询所有抽奖人的头像
     * @param id
     * @return
     */
    @RequestMapping("/users")
    public Result findJoinUserHead(@RequestParam("id") String id) {
        Map map = new HashMap<>();
        List<String> userHeads = userFeign.findJoinUserHead(id);
        map.put("userHeaders", userHeads);
        map.put("joinNum", userHeads.size());
        return new Result(true, StatusCode.OK, "查询成功", map);
    }


    @RequestMapping("/update")
    public void update(@RequestParam("did") String did){
        infoService.update(did);
    }


    /**
     * 查询所有抽奖
     */
    @RequestMapping("/all")
    public Result findAll() {
        List<Info>  infos=infoService.findAll();
        return new Result(true, StatusCode.OK, "查询成功",infos );
    }


    @RequestMapping("/updateOpen")
    public Result updateOpen(@RequestParam("did")String id,@RequestParam("code") Long  code){
        infoService.updateOpen(id,code);
        return new Result(true,StatusCode.OK,"修改成功");
    }
}
