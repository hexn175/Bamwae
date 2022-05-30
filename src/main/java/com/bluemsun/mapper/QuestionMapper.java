package com.bluemsun.mapper;

import com.bluemsun.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    //按照id返回题目
    Question getQuestionById(int id);

    //返回所有该关卡题目的id
    List<Integer> getAllQuestionIdByCheckPoint(Integer checkpointsId);

}
