package com.michaelkotten.moviecatalogservice.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.michaelkotten.moviecatalogservice.models.Movie;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private static final String MOVIE_SERVICE_URL = "movie-info-service";
    private static final String USER_ID = "100";
    private static final String MOVIE_ID = "movie";

    @Mock
    private RestTemplate restTemplate;

    private MovieService service;

    @Test
    void shouldReturnMovie() {
        Movie expected = new Movie(MOVIE_ID, "name", "description");
        doReturn(expected)
                .when(restTemplate).getForObject(MOVIE_SERVICE_URL + "/movies/" + MOVIE_ID, Movie.class);

        service = new MovieService(restTemplate, MOVIE_SERVICE_URL);

        Movie movie = service.getMovie(MOVIE_ID);
        assertThat(movie).isNotNull();
        assertThat(movie).isEqualTo(movie);
    }
}
