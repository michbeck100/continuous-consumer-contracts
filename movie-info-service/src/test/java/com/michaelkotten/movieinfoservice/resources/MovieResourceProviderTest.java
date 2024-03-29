package com.michaelkotten.movieinfoservice.resources;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import com.michaelkotten.movieinfoservice.MovieInfoServiceApplication;
import com.michaelkotten.movieinfoservice.ProviderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MovieInfoServiceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PactBroker(tags = "${pactbroker.tags:prod}")
//@PactFolder("../movie-catalog-service/target/pacts")
@Provider("movie-info-service")
@ProviderTest
public class MovieResourceProviderTest {

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

    @State({"movie service contains movie"})
    public void setupTestdata() {
        // setup test data here
    }

}
