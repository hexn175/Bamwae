package com.bluemsun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluemsun.entity.User;
import com.bluemsun.mapper.UserCheckpointsMapper;
import com.bluemsun.mapper.UserMapper;
import com.bluemsun.service.UserService;
import com.bluemsun.utils.HttpClientUtil;
import com.bluemsun.utils.JWTUtil;
import com.bluemsun.utils.JedisUtil;
import com.bluemsun.utils.WxUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    JedisUtil jedisUtil;

    @Transactional
    @Override
    public Map login(String code) throws Exception{
        Gson gson = new Gson();
        // 请求地址
        String requestUrl = WxUtil.getWxServerUrl(code);
        // 发送请求
        String response = HttpClientUtil.getRequest(requestUrl);
        // 格式化JSON数据
        User wxUser = gson.fromJson(response,User.class);
        User user = null;
        String token = null;
        Map<String, Object> map = new HashMap<String, Object>();
        if (wxUser.getOpenId() != null) {
            // 查询数据库里是否有此用户
            user = userMapper.selectUserByOpenId(wxUser.getOpenId());
            // 若没有，将该用户add到数据库
            if (user == null) {
                userMapper.addUser(wxUser);
            }
            user = userMapper.selectUserByOpenId(wxUser.getOpenId());
            // 使用数据库的id来生成token(不使用openID)
            String id = userMapper.selectUserByOpenId(wxUser.getOpenId()).getId().toString();
            token = JWTUtil.generateToken(id, "bamwae", "wxUser");
            jedisUtil.set("token:"+user.getId(),token,0);
            jedisUtil.expire("token:"+user.getId(),1800,0);
            map.put("user",user);
            map.put("token",token);
        }
        return map;
    }

    @Override
    public User selectUserById(Integer id) {
//        System.out.println(jedisUtil.get("test"));
        jedisUtil.set("test:"+"first","hello user111",1);
        return userMapper.selectById(id);
    }

}
