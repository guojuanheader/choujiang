package com.draw.open;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.draw.infos.feign","com.draw.user.feign"})
@MapperScan(basePackages = {"com.changgou.open.dao"})
@EnableScheduling    //开启定时任务
public class OpenApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenApplication.class,args);
    }
}
