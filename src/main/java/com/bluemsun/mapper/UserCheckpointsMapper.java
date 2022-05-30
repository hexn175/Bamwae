package com.bluemsun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCheckpointsMapper extends BaseMapper {

    //获取用户已关卡解锁的id
    List<Integer> getCheckpointsUnLockByUserId(Integer userId);

}
