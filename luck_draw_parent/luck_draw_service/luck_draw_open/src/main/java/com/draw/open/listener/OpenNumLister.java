package com.draw.open.listener;

import com.draw.open.service.OpenService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class  OpenNumLister{
        @Autowired
        private OpenService openService;
        @RabbitListener(queues = "openNum_draw")
        public void autoTack(String message){
            openService.open();//自动开奖
        }
}
