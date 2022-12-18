package com.epam.javacc.microservices.businessservices.two.controller;

import com.epam.javacc.microservices.businessservices.two.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TwoController {

    //    @Value("${eureka.instance.metadataMap.instanceId}")
//    private String instanceId;
    private static final Integer RANDOM = new Random().nextInt();

    @GetMapping
    public Mono<String> getHello() {
        //Thread.sleep(1100);
        final Mono<String> Result = Mono.just(String.format("Hello from Two application! Instance Id = %s", RANDOM));
        //result.materialize()
        return Result
                .flatMap(s -> Mono.just("After chaining: " + s));
    }

    @GetMapping("/product")
    public Mono<ProductDto> getProduct() {
        return Mono.just(
                new ProductDto()
                        .setId(1L)
                        .setName("Laptop")
                        .setPrice(100.0));
    }

    @GetMapping("/exception")
    public Mono<String> getException() {
        throw new RuntimeException("Oops. Error :(");
    }
}
