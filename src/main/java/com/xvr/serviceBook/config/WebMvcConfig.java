package com.xvr.serviceBook.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcConfig {

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
