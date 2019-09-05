package com.draw.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private StringRedisTemplate  stringRedisTemplate;
    public String getJtiFromCookie(ServerHttpRequest request) {
        HttpCookie httpCookie = request.getCookies().getFirst("uid");
        if (httpCookie != null) {
            String jti = httpCookie.getValue();
            return jti;
        }
            return null;
    }

    public String getJwtFromRedis(String jti) {
        String jwt = stringRedisTemplate.boundValueOps(jti).get();
        return jwt;
    }
}
