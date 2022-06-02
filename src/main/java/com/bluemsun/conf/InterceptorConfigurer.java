//package com.bluemsun.conf;
//
//import com.bluemsun.interceptor.LoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class InterceptorConfigurer implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //创建拦截器类
//        HandlerInterceptor interceptor=new LoginInterceptor();
//        List<String> patterns=new ArrayList<>();
//        patterns.add("/user/login");
//        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
//    }
//}
