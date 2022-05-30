package com.bluemsun.controller;


import com.bluemsun.dto.CheckPointsDto;
import com.bluemsun.entity.Question;
import com.bluemsun.service.impl.CheckPointsServiceImpl;
import com.bluemsun.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cpc")
public class CheckPointController {

    @Autowired
    CheckPointsServiceImpl checkPointsService;

    /**
     * 获取用户已经解锁的关卡的idList
     * @param request
     * @return map
     */

    @GetMapping("/getCheckPoint")
    @ResponseBody
    public Map getCheckPointOfUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map map = new HashMap();
        if (token != null) {
            Claims claims = JWTUtil.verifyToken(token);
            Integer userId = Integer.getInteger(claims.getId());
            List<Integer> idList = checkPointsService.getCheckPointsUnlockOfUser(userId);
            map.put("idList",idList);
            map.put("code",0);
        } else map.put("code",1);
        return map;
    }

    /**
     * 将用户通关的关卡记录到数据库
     * @param request
     * @param checkPointsUnlockDto
     * @return map
     */

    @PostMapping("/unLockCheckPoint")
    @ResponseBody
    public Map unLockCheckPoint(HttpServletRequest request, @RequestBody CheckPointsDto checkPointsUnlockDto) {
        String token = request.getHeader("token");
        Claims claims = JWTUtil.verifyToken(token);
        Integer userId = Integer.getInteger(claims.getId());
        checkPointsUnlockDto.setUserId(userId);
        Map map = new HashMap();
        Integer add = checkPointsService.add(checkPointsUnlockDto);
        if (add != 0) {
            map.put("code",0);
        } else {
            map.put("code",1);
        }
        return map;
    }


    //获取该关卡的所有题目id
    @GetMapping("/getQuestionIds")
    @ResponseBody
    public Map getQuestionIdsOfCheckPoint(Integer id) {
        List<Integer> idList = checkPointsService.getQuestionIdsOfCheckPoint(id);
        Map map = new HashMap();
        if (idList != null) {
            map.put("code",0);
            map.put("idList",idList);
        } else {
            map.put("code",1);
        }
        return map;
    }

    //获取题目
    @GetMapping("/getQuestion")
    @ResponseBody
    public Map getQuestion(Integer id) {
        Map map = new HashMap();
        Question question = checkPointsService.getQuestion(id);
        if (question != null) {
            map.put("code",0);
            map.put("question",question);
        } else {
            map.put("code",1);
        }
        return map;
    }


}
