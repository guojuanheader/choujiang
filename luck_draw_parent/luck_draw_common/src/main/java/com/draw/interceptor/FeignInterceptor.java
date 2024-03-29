package com.draw.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 自定义拦截器, 拦截所有请求
 * 每次微服务调用之前都先检查下头文件，将请求的头文件中的令牌数据再放入到header中
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //拿到请求对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes!=null){
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request!=null){
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames!=null){
                    while (headerNames.hasMoreElements()){
                        String herderName = headerNames.nextElement();
                        if (herderName.equals("authorization")){
                            //得到Bearer jwt
                            String headerValue = request.getHeader(herderName);

                            /**
                             * 向下传递令牌
                             */
                            requestTemplate.header(herderName,headerValue);
                        }
                    }
                }
            }
        }

    }
}
