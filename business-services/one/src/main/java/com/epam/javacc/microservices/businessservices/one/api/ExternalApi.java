package com.epam.javacc.microservices.businessservices.one.api;

import com.epam.javacc.microservices.businessservices.shared.dto.ProductDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExternalApi {

    private final WebClient.Builder webClientBuilder;
    private final WebClient.Builder webClientBuilderTwo;

    @CircuitBreaker(name = "two-service", fallbackMethod = "fallbackCircuitBreaker")
    @RateLimiter(name = "two-service", fallbackMethod = "fallbackRateLimiter")
    public Mono<String> callExternalApiTwo() {
        return webClientBuilder.baseUrl("http://two-service").build()
                .get()
                .uri("/v1")
                .retrieve()
                .bodyToMono(String.class)
                .log();
    }

    public Mono<ProductDto> callApiTwoForProduct() {
        return webClientBuilderTwo.build().get().uri("/v1/product").retrieve().bodyToMono(ProductDto.class);
    }

    @CircuitBreaker(name = "two-service", fallbackMethod = "fallbackCircuitBreaker")
    // @RateLimiter(name = "two-service", fallbackMethod = "fallbackRateLimiter")
    public Mono<String> callExternalApiTwoWithException() {
        return webClientBuilder.baseUrl("http://two-service").build()
                .get().uri("/v1/exception").retrieve().bodyToMono(String.class);
    }

    public Mono<String> fallbackCircuitBreaker(final Exception exception) {
        log.error("fallbackCircuitBreaker :: ", exception);
        return Mono.just("Hi from fallbackCircuitBreaker!");
    }

    public Mono<String> fallbackRateLimiter(final Exception exception) {
        log.error("fallbackRateLimiter :: ", exception);
        return Mono.just("Hi from fallbackRateLimiter!");
    }
}