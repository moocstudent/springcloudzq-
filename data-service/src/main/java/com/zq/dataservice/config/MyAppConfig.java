package com.zq.dataservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyAppConfig {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
