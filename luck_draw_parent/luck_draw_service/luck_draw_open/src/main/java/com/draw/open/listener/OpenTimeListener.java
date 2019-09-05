package com.draw.open.listener;


import com.draw.open.service.OpenService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenTimeListener {

    @Autowired
    private OpenService  openService;
    @RabbitListener(queues = "openTime_draw")
    public void autoTack(String message){
        openService.open();//自动开奖
    }
}
