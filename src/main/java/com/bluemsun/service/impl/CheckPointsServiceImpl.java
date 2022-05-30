package com.bluemsun.service.impl;

import com.bluemsun.dto.CheckPointsDto;
import com.bluemsun.entity.Question;
import com.bluemsun.mapper.QuestionMapper;
import com.bluemsun.mapper.UserCheckpointsMapper;
import com.bluemsun.service.CheckPointsService;
import com.bluemsun.utils.JedisUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckPointsServiceImpl implements CheckPointsService {

    @Autowired
    UserCheckpointsMapper userCheckpointsMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    JedisUtil jedisUtil;


    @Override
    public List<Integer> getCheckPointsUnlockOfUser(Integer userId) {

        return userCheckpointsMapper.getCheckpointsUnLockByUserId(userId);
    }

    @Override
    public Integer add(CheckPointsDto checkPointsUnlockDto) {
        jedisUtil.set("cpud:"+checkPointsUnlockDto.getUserId(),checkPointsUnlockDto.getCheckpointsId().toString(),3);
        return userCheckpointsMapper.insert(checkPointsUnlockDto);
    }

    public List<Integer> getQuestionIdsOfCheckPoint(Integer id) {
        return questionMapper.getAllQuestionIdByCheckPoint(id);
    }

    @Override
    public Question getQuestion(Integer questionId) {
        Gson gson = new Gson();
        Question questionOfR = gson.fromJson(jedisUtil.get("question:" + questionId, 1), Question.class);
        if (questionOfR == null) {
            questionOfR = questionMapper.getQuestionById(questionId);
            jedisUtil.set("question:"+questionId,gson.toJson(questionOfR),1);
        }
        return questionOfR;
    }
}
