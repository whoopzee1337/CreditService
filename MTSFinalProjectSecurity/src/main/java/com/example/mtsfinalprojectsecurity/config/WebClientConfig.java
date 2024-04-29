package com.example.mtsfinalprojectsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClient() {
        return WebClient.builder().baseUrl("http://localhost:8765");
    }
}
