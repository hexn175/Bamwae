package com.bluemsun.service;

import com.bluemsun.dto.CheckPointsDto;
import com.bluemsun.entity.Question;

import java.util.List;

public interface CheckPointsService {

    //获取用户已解锁的关卡
    List<Integer> getCheckPointsUnlockOfUser(Integer userId);
    //通关后，将纪录加到数据库里,并存到redis 的DB3里
    Integer add(CheckPointsDto checkPointsUnlockDto);
    //根据题目id获取题目
    Question getQuestion(Integer questionId);
    //获取所有该关卡的题目id
    public List<Integer> getQuestionIdsOfCheckPoint(Integer id);

}
