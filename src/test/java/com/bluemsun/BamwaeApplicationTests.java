package com.bluemsun;

import com.bluemsun.service.HistoryService;
import com.bluemsun.utils.JWTUtil;
import com.bluemsun.utils.JedisUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BamwaeApplicationTests {

    @Autowired
    JedisUtil jedisUtil;
    @Autowired
    HistoryService historyService;

    @Test
    void contextLoads() {
        String token = JWTUtil.generateToken("1", "bamwae", "wxUser");
        jedisUtil.set("token:1",token,0);
    }

    @Test
    void getHistory() {
        System.out.println(historyService.getHistoryById(2));
    }

}
