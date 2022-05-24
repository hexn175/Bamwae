package com.bluemsun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@MapperScan("com.bluemsun.mapper")
@ServletComponentScan("com.bluemsun")
@SpringBootApplication(scanBasePackages = "com.bluemsun")
public class BamwaeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BamwaeApplication.class, args);
    }
}
