package com.draw.infos;


import com.draw.infos.util.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.draw.infos.dao")
@EnableFeignClients(basePackages = {"com.draw.user.feign"})
public class InfosApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfosApplication.class,args);
    }


    @Bean
    public TokenDecode  tokenDecode(){
        return new TokenDecode();
    }
}
