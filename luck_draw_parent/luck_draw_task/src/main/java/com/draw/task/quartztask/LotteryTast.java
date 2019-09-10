package com.draw.task.quartztask;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class LotteryTast {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private RabbitTemplate  rabbitTemplate;
    @Scheduled(cron = " 0/30  *   *  *  *    ?")
    public void   authOpen(){
        //rabbitTemplate.convertAndSend("","openTime_draw","_");
        kafkaTemplate.send("match",0,"timeOpen","-");
    }

}
