package com.recruit.wm.security01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.recruit.wm.security01"})
@MapperScan("com.recruit.wm.security01.dao")
public class Security01Application {

    public static void main(String[] args) {
        SpringApplication.run(Security01Application.class, args);
    }

}
