package com.epam.javacc.microservices.businessservices.twoapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TwoController {

    @GetMapping
    public Mono<String> getHello() {
        return Mono.just("Hello from Two-API application!");
    }
}
