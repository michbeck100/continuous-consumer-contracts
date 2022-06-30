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
import com.michaelkotten.moviecatalogservice.models.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith({PactConsumerTestExt.class, SpringExtension.class})
@PactTestFor(providerName = "movie-info-service")
@SpringBootTest(classes = {MovieCatalogServiceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ConsumerTest
class MovieServiceConsumerTest {

    @Pact(provider = "movie-info-service", consumer = "movie-catalog-service")
    public RequestResponsePact getMoviePact(PactDslWithProvider builder) {
        return builder
                .given("movie service contains movie")
                .uponReceiving("a request to GET movie")
                .method("GET")
                .path("/movies/100")
                .willRespondWith()
                .status(200)
                .matchHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(new PactDslJsonBody()
                        .stringMatcher("movieId", "\\d+", "100")
                        .stringMatcher("name", ".+", "Joker")
                        .stringMatcher("description", ".*", "This is a description")
                )
                .toPact();
    }

    @Test
    void testGetMoviePact(MockServer mockServer) {
        MovieService movieService = new MovieService(new RestTemplate(), mockServer.getUrl());
        Movie movie = movieService.getMovie("100");
        assertThat(movie).isNotNull();
        assertThat(movie.getMovieId()).isEqualTo("100");
        assertThat(movie.getName()).isEqualTo("Joker");
    }
}
