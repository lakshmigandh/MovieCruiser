package com.cts.sr.moviecruiser.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class Swagger {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.cts.sr.moviecruiser.user.controller"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("User Controller", "This API provides user operations for authenticating and registering users"))
                .apiInfo(metaData());
    }
    
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Movie Cruiser Auth Application",
                "User Management For Movie Cruiser",
                "1.0",
                "Terms of service",
                new Contact("Lakshmi Gandh", "https://github.com/lakshmigandh", "LakshmiGandh.M@cognizant.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
    
}