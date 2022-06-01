package com.bluemsun.controller;


import com.bluemsun.entity.History;
import com.bluemsun.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    //获取故事
    @GetMapping("/getHistoryById")
    @ResponseBody
    public Map getHistoryById(Integer id) {
        History history = historyService.getHistoryById(id);
        Map map = new HashMap();
        if (history != null) {
            map.put("code",0);
            map.put("data",history);
        } else {
            map.put("code",1);
        }
        return map;
    }

}
