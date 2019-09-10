package com.draw.open.listener;

import com.draw.open.service.OpenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class TimeKafkaConsumer {

    @Autowired
    private OpenService  openService;
    /**
     * 监听,有消息就读取
     */
    @KafkaListener(topicPartitions = {@TopicPartition(topic = "match", partitions = {"0"})})
    public void listenPartition0(@Payload String message, @Headers MessageHeaders headers) {
        log.info("message:{}",message);
        openService.open();
    }
}