package com.michaelkotten.ratingsdataservice.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.michaelkotten.ratingsdataservice.ProviderTest;
import com.michaelkotten.ratingsdataservice.RatingsDataServiceApplication;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RatingsDataServiceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PactBroker(tags = "${pactbroker.tags:prod}")
//@PactFolder("../movie-catalog-service/target/pacts")
@Provider("ratings-data-service")
@ProviderTest
class RatingsResourceProviderTest {

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    void setupTestTarget(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", serverPort, "/"));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State({"ratings service contains ratings for user"})
    public void setupTestdata() {
        // setup test data here
    }

}
