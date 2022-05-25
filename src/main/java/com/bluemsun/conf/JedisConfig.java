package com.bluemsun.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import lombok.extern.slf4j.Slf4j;

@ConfigurationProperties(prefix = "spring.redis")
@Configuration
@Slf4j
@Data
public class JedisConfig {

    private String host;

    private int port;

    private int timeout;

    private int maxIdle;

    private String password;

    @Bean
    public JedisPool redisPoolFactory()  throws Exception{
        JedisPool jedisPool = new JedisPool(host, port,null,password);
        return jedisPool;
    }
}

