package com.recruit.wm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
//@ComponentScan("com.recruit.wm")
@MapperScan("com.recruit.wm.dao")
public class RecruitStarterApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(RecruitStarterApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("????????????????????????????????");
    }
}
