package com.bluemsun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluemsun.entity.User;
import com.bluemsun.mapper.UserMapper;
import com.bluemsun.service.UserService;
import com.bluemsun.utils.HttpClientUtil;
import com.bluemsun.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String code) throws Exception{
        //请求地址
        String requestUrl = WxUtil.getWxServerUrl(code);
        // 发送请求
        String response = HttpClientUtil.getRequest(requestUrl);
        //格式化JSON数据
        User wxUser = JSONObject.parseObject(response, User.class);
        //
        User user = userMapper.login(wxUser.getOpenId());
        if (user == null) {
            System.out.println("用户不存在");
        } else {
            if (!wxUser.getSessionKey().equals(user.getSessionKey())) {
                System.out.println("更新sessionKey");
            }
        }
        return user;
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectById(id);
    }

}
