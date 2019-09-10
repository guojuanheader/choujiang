package com.draw.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling  //注解用于开启任务调度
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run( TaskApplication.class,args );
    }
}