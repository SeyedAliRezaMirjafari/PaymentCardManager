package com.sed.payment_service;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan({
        "com.sed.payment_service.apiControllers",
        "com.sed.payment_service.common.exceptions",
        "com.sed.payment_service.common.*",
        "com.sed.payment_service.config",
        "com.sed.payment_service.dataaccess.*",
        "com.sed.payment_service.services.*"})
@EnableJpaRepositories({"com.sed.payment_service"})
@EnableSwagger2
public class ApplicationConfig {
    @Bean
    public Docket swaggerPersonApi10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sed.payment_service.apiControllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Person API").description("Documentation Employee API v1.0").build());
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }
}
