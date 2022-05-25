package com.bluemsun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bluemsun.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService extends IService<User> {
    Map login(String code) throws Exception;
    User selectUserById(Integer id);
}
