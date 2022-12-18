package com.epam.javacc.microservices.businessservices.one.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@Profile("test")
@Configuration
class WebClientTestConfig {

    @LoadBalanced
    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    WebClient.Builder webClientBuilderTwo() {
        return WebClient.builder().baseUrl("http://localhost:11001");
    }
}