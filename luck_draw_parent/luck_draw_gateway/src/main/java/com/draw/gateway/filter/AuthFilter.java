package com.draw.gateway.filter;


import com.draw.gateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthFilter  implements GlobalFilter, Ordered {

    @Autowired
    private AuthService authService;
    private static final String LOGIN_URL="http://localhost:9200/oauth/toLogin";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //是否是登录，是的话放行
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getURI().getPath();
        System.out.println(!UrlFilter.hasAuthorize(path));
        if ("/api/oauth/login".equals(path) || !UrlFilter.hasAuthorize(path)){
            return chain.filter(exchange);
        }
        //cookie中是否存在jti
        String jti =authService.getJtiFromCookie(request);
        if (StringUtils.isEmpty(jti)){
            //如果为空拒绝访问
            /*response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
            //跳转到登录页面  携带请求的页面url
            return toLoginPage(exchange,LOGIN_URL+"?from="+request.getURI());
        }
        //redis中是否存在jwt
        String jwt=authService.getJwtFromRedis(jti);
        if (StringUtils.isEmpty(jwt)){
            //如果为空拒绝访问
           /* response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
            //跳转到登录页面  携带请求的页面url
            return toLoginPage(exchange,LOGIN_URL+"?from="+request.getURI());
        }
        //对当前请求进行加强（给令牌）
        request.mutate().header("Authorization","Bearer "+jwt);
        return chain.filter(exchange);
    }

    /**
     * jtl或者jwt为空重定向到登录页面
     * @param exchange
     * @param loginUrl
     * @return
     */
    private Mono<Void> toLoginPage(ServerWebExchange exchange, String loginUrl) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().add("Location",loginUrl);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
