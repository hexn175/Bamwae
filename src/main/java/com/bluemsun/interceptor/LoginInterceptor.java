//package com.bluemsun.Interceptor;
//
//import com.alibaba.fastjson.JSON;
//import com.bluemsun.utils.JWTUtil;
//import com.bluemsun.utils.JedisUtil;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwt;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
////登录拦截器
//public class LoginInterceptor implements HandlerInterceptor {
//
//    private final JedisUtil jedisUtil;
//
//    public LoginInterceptor(JedisUtil jedisUtil) {
//        this.jedisUtil = jedisUtil;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String responseData = null;
//        // 获取请求头中的token验证字符串
//        String headerToken =null;
//        // 检测当前页面，设置当前页不是登录、注册页面时就对其进行拦截
//        // 具体方法就是检测URL中有没有 login 或 register 字符串
//        if (!request.getRequestURI().contains("login")&&!request.getRequestURI().contains("register")) {
//            headerToken = request.getHeader("token");
//            if (headerToken != null) {
//                try {
//                    Claims verifyToken = JWTUtil.verifyToken(headerToken);
//                    //这里判断了用户端与管理员端的token不可以通用
//                    if ((request.getRequestURI().contains("user") && verifyToken.getSubject().equals("BBSUser"))
//                            || (request.getRequestURI().contains("manager") && verifyToken.getSubject().equals("BBSManager"))) {
//                        //先去redis的token黑名单找，如果找到了：说明这个token即使未过期也不能使用
//                        //                       如果未找到：再去判断他是否过期
//                        if (jedisUtil.get("token:"+headerToken) == null) {
//                            // 对token更新与验证
//                            headerToken = JWTUtil.updateToken(headerToken);
//                        } else {
//
//                            responseData = "The token has expired!";
//                        }
//                    } else {
//                        responseData = "Token is legitimate!";
//                    }
//                } catch (Exception e) {
//
//                    // 这里的ResponseData类自定义的返回信息类
//                    responseData = "The token has expired!";
//                }
//            } else {
//                // 如果没有token，返回错误信息
//                responseData = "There is no token!";
//            }
//            //如果是退出登录，则需要把返回的token加入黑名单
//            if (request.getRequestURI().contains("logOut")) {
////                jedisUtil.set("token:"+headerToken,headerToken);
//                responseData = "The token has expired!";
//            }
//        }
//        // 如果有错误信息
//        if (responseData != null) {
//            response.getWriter().write(JSON.toJSONString(responseData));
//            return false;
//        } else {
//            // 将token加入返回的header中
//            response.setHeader("token", headerToken);
//            return true;
//        }
////        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}