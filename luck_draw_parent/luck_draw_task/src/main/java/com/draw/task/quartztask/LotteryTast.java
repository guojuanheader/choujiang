package com.draw.task.quartztask;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LotteryTast {

    @Autowired
    private RabbitTemplate  rabbitTemplate;
    @Scheduled(cron = "*  0/30 *  *  *   * ?")
    public void   authOpen(){
        rabbitTemplate.convertAndSend("","openTime_draw","_");
    }

}
