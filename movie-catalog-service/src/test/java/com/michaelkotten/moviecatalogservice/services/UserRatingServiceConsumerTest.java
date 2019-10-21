package com.michaelkotten.moviecatalogservice.services;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.michaelkotten.moviecatalogservice.ConsumerTest;
import com.michaelkotten.moviecatalogservice.MovieCatalogServiceApplication;
import com.michaelkotten.moviecatalogservice.models.UserRating;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

@ExtendWith({PactConsumerTestExt.class, SpringExtension.class})
@PactTestFor(providerName = "ratings-data-service", port = "8888")
@SpringBootTest(classes = {MovieCatalogServiceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"ratings-data-service.url=http://localhost:8888"})
@ConsumerTest
class UserRatingServiceConsumerTest {

    @Autowired
    private UserRatingService userRatingService;

    @Pact(provider = "ratings-data-service", consumer = "movie-catalog-service")
    public RequestResponsePact getMoviePact(PactDslWithProvider builder) {
        return builder
                .given("ratings service contains ratings for user with id = 100")
                .uponReceiving("a request to GET ratings for user with id = 100")
                .method("GET")
                .path("/ratingsdata/user/100")
                .willRespondWith()
                .status(200)
                .matchHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
                .body(new PactDslJsonBody()
                        .stringType("userId", "100")
                        .minArrayLike("ratings", 2)
                        .stringType("movieId")
                        .integerType("rating")
                        .closeObject()
                )
                .toPact();
    }

    @Test
    void testGetMoviePact(MockServer mockServer) {
        UserRating userRating = userRatingService.getUserRating("100");
        assertThat(userRating).isNotNull();
        assertThat(userRating.getUserId()).isEqualTo("100");
        assertThat(userRating.getRatings()).isNotEmpty();
    }
}
