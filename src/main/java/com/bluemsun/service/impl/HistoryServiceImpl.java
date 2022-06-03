package com.bluemsun.service.impl;

import com.bluemsun.entity.History;
import com.bluemsun.mapper.HistoryMapper;
import com.bluemsun.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryMapper historyMapper;

    @Override
    public History getHistoryById(Integer id) {
        History history = historyMapper.getHistoryById(id);
        history.setPart(historyMapper.getHistoryPartsById(id));
        return history;
    }


}
