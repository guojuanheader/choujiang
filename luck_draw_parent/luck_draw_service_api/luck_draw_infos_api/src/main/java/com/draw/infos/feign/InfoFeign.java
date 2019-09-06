package com.draw.infos.feign;


import com.draw.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "info")
public interface InfoFeign {

    @RequestMapping("info/findById")
    public Result findById(@RequestParam("id") String id);

    @RequestMapping("info/update")
    public void update(@RequestParam("did") String did);

    @RequestMapping("info/all")
    public Result findAll();

    @RequestMapping("userAndDraw/findUid")
    public Result findUid(@RequestParam("code")Long code,@RequestParam("did")String  did);


    @RequestMapping("info/updateOpen")
    public Result updateOpen(@RequestParam("did")String id);
}
