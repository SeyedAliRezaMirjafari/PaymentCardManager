package com.sed.card_service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({
        "com.sed.card_service.apiControllers",
        "com.sed.card_service.common.exceptions",
        "com.sed.card_service.common.*",
        "com.sed.card_service.config",
        "com.sed.card_service.dataaccess.*",
        "com.sed.card_service.services.*"})
@EnableJpaRepositories({"com.sed.card_service"})
public class ApplicationConfig {
}
