package com.bluemsun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bluemsun.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //查询用户
    User selectUserByOpenId(String openId);

    //add用户
    Integer addUser(User user);
}
