package com.michaelkotten.moviecatalogservice.services;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.michaelkotten.moviecatalogservice.ConsumerTest;
import com.michaelkotten.moviecatalogservice.MovieCatalogServiceApplication;
import com.michaelkotten.moviecatalogservice.models.UserRating;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith({PactConsumerTestExt.class, SpringExtension.class})
@PactTestFor(providerName = "ratings-data-service")
@SpringBootTest(classes = {MovieCatalogServiceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ConsumerTest
class UserRatingServiceConsumerTest {

    @Pact(provider = "ratings-data-service", consumer = "movie-catalog-service")
    public RequestResponsePact getMoviePact(PactDslWithProvider builder) {
        return builder
                .given("ratings service contains ratings for user")
                .uponReceiving("a request to GET ratings for user")
                .method("GET")
                .path("/ratingsdata/user/100")
                .willRespondWith()
                .status(200)
                .matchHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(new PactDslJsonBody()
                        .stringMatcher("userId", "\\d+", "100")
                        .minArrayLike("ratings", 2)
                        .stringMatcher("movieId", "\\d+", "100")
                        .integerType("rating")
                        .closeObject()
                )
                .toPact();
    }

    @Test
    void testGetMoviePact(MockServer mockServer) {
        UserRatingService userRatingService = new UserRatingService(new RestTemplate(), mockServer.getUrl());
        UserRating userRating = userRatingService.getUserRating("100");
        assertThat(userRating).isNotNull();
        assertThat(userRating.getUserId()).isEqualTo("100");
        assertThat(userRating.getRatings()).isNotEmpty();
    }
}
