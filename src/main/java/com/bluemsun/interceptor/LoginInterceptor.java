package com.bluemsun.interceptor;

import com.bluemsun.utils.JWTUtil;
import com.bluemsun.utils.JedisUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登录拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Gson gson = new Gson();
        String responseData = null;
        // 获取请求头中的token验证字符串
        String headerToken =null;
        // 检测当前页面，设置当前页不是登录页面时就对其进行拦截
        // 具体方法就是检测URL中有没有 login字符串
        if (!request.getRequestURI().contains("login")) {
            headerToken = request.getHeader("token");
            if (headerToken != null) {
                try {
                    Claims verifyToken = JWTUtil.verifyToken(headerToken);
                    if (jedisUtil.get("token:"+headerToken,0) != null) {
                        // 对token更新与验证
                        headerToken = JWTUtil.updateToken(headerToken);
                        //同时需要加入到redis里，设置过期时间30min
                        jedisUtil.expire("token:"+verifyToken.getId(),1800,0);
                    } else {
                        responseData = "The token has expired!";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // 如果没有token，返回错误信息
                responseData = "There is no token!";
            }
        }
        // 如果有错误信息
        if (responseData != null) {
            response.getWriter().write(gson.toJson(responseData));
            return false;
        } else {
            // 将token加入返回的header中
            response.setHeader("token", headerToken);
            return true;
        }
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}