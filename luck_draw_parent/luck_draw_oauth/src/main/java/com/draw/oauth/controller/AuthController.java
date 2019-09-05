package com.draw.oauth.controller;

import com.draw.entity.Result;
import com.draw.entity.StatusCode;
import com.draw.oauth.service.AuthService;
import com.draw.oauth.util.AuthToken;
import com.draw.oauth.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/oauth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

   /* @GetMapping("/toLogin")
    //required为false表示参数不一定有，defaultValue默认值
    public String toLogin(@RequestParam(name = "from",required = false,defaultValue = "") String from, Model model){
        model.addAttribute("from",from);
        return "login";
    }*/


    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password, HttpServletResponse response) {
        //先到这里
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("用户名不存在");
        }
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("密码不存在");
        }

        AuthToken authToken = authService.applyToken(username, password, clientId, clientSecret);
        //将jti存入cookie
        this.saveJtiToCookie(authToken.getJti(),response);

        return new Result(true, StatusCode.OK,"登录成功",authToken.getJti());
    }




    private void saveJtiToCookie(String jti, HttpServletResponse response) {
        //获得response得方法，可以直接传过来，可以通过下边得
       // HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //cookieDomain域名，uid指cookie名  false表示允许客户端来操作cookie
        CookieUtil.addCookie(response,cookieDomain,"/","uid",jti,cookieMaxAge,false);
    }
}
