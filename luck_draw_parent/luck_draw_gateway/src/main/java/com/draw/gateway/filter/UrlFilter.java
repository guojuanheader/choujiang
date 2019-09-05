package com.draw.gateway.filter;

public class UrlFilter {
    //购物车订单微服务都需要用户登录，必须携带令牌，所以所有路径都过滤,订单微服务需要过滤的地址
    public static String orderFilterPath = "/api/user/**,/api/info/**,/api/join/**,/api/cart/**,/api/categoryReport/**,/api/orderConfig/**,/api/order/**,/api/orderItem/**,/api/orderLog/**,/api/preferential/**,/api/returnCause/**,/api/returnOrder/**,/api/returnOrderItem/**";


    public static boolean hasAuthorize(String url){
        String[] split = orderFilterPath.replace("**", "").split(",");
        for (String value : split) {
            if (url.startsWith(value)){
                return true;
            }
        }
            return false;
    }

}
