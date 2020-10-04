package com.sed.payment_service;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ApplicationConfig.class)
public class PaymentServiceApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.location", "classpath:payment_resource_config.yml,file:config/payment_config.yml,file:config/constants.yml,file:config/log-config.yml");
        //System.setProperty("spring.profiles.active", "LocalApi");
        SpringApplication.run(PaymentServiceApplication.class, args);



    }

}
