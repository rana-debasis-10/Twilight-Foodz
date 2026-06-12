package com.twilight.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {
    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
