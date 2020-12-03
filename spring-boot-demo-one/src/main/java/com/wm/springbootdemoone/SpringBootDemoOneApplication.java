package com.wm.springbootdemoone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.wm.springbootdemoone.dao"})
public class SpringBootDemoOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoOneApplication.class, args);
    }

}
