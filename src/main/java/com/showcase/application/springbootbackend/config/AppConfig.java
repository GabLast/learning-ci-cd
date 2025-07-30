package com.showcase.application.springbootbackend.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //Object Mapper configuration. Alt option to StringToDateConverter
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer dateCustomizer() {
        return builder -> builder
                .simpleDateFormat("yyyy-MM-dd")
                .failOnUnknownProperties(false);
    }
}
