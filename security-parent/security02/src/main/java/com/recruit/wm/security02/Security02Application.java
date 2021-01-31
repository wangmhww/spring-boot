package com.recruit.wm.security02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication(scanBasePackages = {"com.recruit.wm.security02"})
@EnableGlobalMethodSecurity(jsr250Enabled = true,securedEnabled = true,prePostEnabled = true,proxyTargetClass = true)
public class Security02Application {

    public static void main(String[] args) {
        SpringApplication.run(Security02Application.class, args);
    }

}
