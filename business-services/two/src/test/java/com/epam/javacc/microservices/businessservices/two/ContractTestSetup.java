package com.epam.javacc.microservices.businessservices.two;

import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class ContractTestSetup {

    @Autowired
    private ApplicationContext context;

    @BeforeEach
    void setup() {
        RestAssuredWebTestClient.applicationContextSetup(context);
    }
}