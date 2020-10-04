package com.sed.notification_service;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.location", "file:config/constants.yml,file:config/notification_config.yml,file:config/log-config.yml");
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

}
