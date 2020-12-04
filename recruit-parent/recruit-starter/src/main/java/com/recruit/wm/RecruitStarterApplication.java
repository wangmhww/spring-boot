package com.recruit.wm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
//@ComponentScan("com.recruit.wm")
@MapperScan("com.recruit.wm.dao")
public class RecruitStarterApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RecruitStarterApplication.class, args);
    }

    /**
     * war启动配置入口
     *
     * @param builder spring应用构造器
     * @return spring应用构造器
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RecruitStarterApplication.class);
    }
}
