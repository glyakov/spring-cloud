package com.epam.javacc.microservices.businessservices.one.controller;

import com.epam.javacc.microservices.businessservices.one.api.ExternalApi;
import com.epam.javacc.microservices.businessservices.shared.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class OneController {

    @Value("${app.user.role}")
    private String appUserRole;

    private final ExternalApi externalApi;

    @GetMapping
    public Mono<String> getFromTwo() {
        final Mono<String> responseMono = externalApi.callExternalApiTwo();
        return responseMono.map(response ->
                "Hi from OneController "
                + appUserRole
                + "<br>"
                + "response from second: "
                + response);
    }

    @GetMapping("/product")
    public Mono<ProductDto> getProductFromTwo() {
        return externalApi.callApiTwoForProduct();
    }

    @GetMapping("/exception")
    public Mono<String> getFromTwoWithException() {
        final Mono<String> responseMono = externalApi.callExternalApiTwoWithException();
        return responseMono.map(response ->
                "Hi from OneController "
                        + "<br>"
                        + "response from second: "
                        + response);
    }
}
