package com.bluemsun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;


@MapperScan("com.bluemsun.mapper")
@ServletComponentScan("com.bluemsun")
@SpringBootApplication(scanBasePackages = "com.bluemsun")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class BamwaeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BamwaeApplication.class, args);
    }
}
