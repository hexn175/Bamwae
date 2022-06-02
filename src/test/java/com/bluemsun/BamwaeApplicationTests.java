package com.bluemsun;

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

    @Test
    void contextLoads() {
        String token = JWTUtil.generateToken("1", "bamwae", "wxUser");
        jedisUtil.set("token:1",token,0);
    }

}
