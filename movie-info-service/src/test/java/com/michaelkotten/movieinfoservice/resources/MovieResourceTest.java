package com.michaelkotten.movieinfoservice.resources;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import com.michaelkotten.movieinfoservice.models.Movie;

class MovieResourceTest {

    private MovieResource movieResource = new MovieResource();

    @Test
    void shouldReturnMovieInfo() {
        Movie movieInfo = movieResource.getMovieInfo("100");
        assertThat(movieInfo).isNotNull();
        assertThat(movieInfo.getMovieId()).isEqualTo("100");
        assertThat(movieInfo.getName()).isEqualTo("Joker");
    }

}
