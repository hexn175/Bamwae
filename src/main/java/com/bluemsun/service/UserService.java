package com.bluemsun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bluemsun.entity.User;
import org.springframework.stereotype.Service;


public interface UserService extends IService<User> {
    User login(String code) throws Exception;
    User selectUserById(Integer id);
}
