package com.draw.join;


import com.draw.interceptor.FeignInterceptor;
import com.draw.join.util.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.draw.infos.dao")
@EnableFeignClients(basePackages = {"com.draw.user.feign","com.draw.infos.feign"})
public class JoinApplication {
    public static void main(String[] args) {
        SpringApplication.run(JoinApplication.class,args);
    }

    @Bean
    public TokenDecode  tokenDecode(){
        return new TokenDecode();
    }


    @Bean
    public FeignInterceptor    feignInterceptor(){
        return new FeignInterceptor();
    }
}
