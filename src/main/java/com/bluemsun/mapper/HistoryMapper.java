package com.bluemsun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bluemsun.entity.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper {

    //根据id获取历史
    History getHistoryById(@Param("id") Integer id);

    //根据id获取历史分段list
    List<String> getHistoryPartsById(@Param("id") Integer id);

}
