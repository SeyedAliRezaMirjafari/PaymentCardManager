package com.sed.card_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ApplicationConfig.class)
public class CardServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.location", "classpath:card_resource_config.yml,file:config/card_config.yml,file:config/log-config.yml");
        SpringApplication.run(CardServiceApplication.class, args);
    }

}
