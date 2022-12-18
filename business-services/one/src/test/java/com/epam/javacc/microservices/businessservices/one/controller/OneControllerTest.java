package com.epam.javacc.microservices.businessservices.one.controller;

import com.epam.javacc.microservices.businessservices.shared.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureStubRunner(
        ids = "com.epam.javacc:two:+:stubs:11001",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class OneControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getProductFromTwo() {
        // when:
        final ProductDto product = webTestClient.get()
                .uri("/v1/product")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody()
                .blockFirst();

        // then:
        assertNotNull(product);
        assertEquals(2, product.getId());
    }
}