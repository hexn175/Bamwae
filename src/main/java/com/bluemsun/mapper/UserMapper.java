package com.bluemsun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bluemsun.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User login(String openId);
}
