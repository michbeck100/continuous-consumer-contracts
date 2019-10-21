package com.michaelkotten.ratingsdataservice.resources;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.michaelkotten.ratingsdataservice.model.Rating;
import com.michaelkotten.ratingsdataservice.model.UserRating;

class RatingsResourceTest {

    private RatingsResource ratingsResource = new RatingsResource();

    @Test
    void shouldGetMovieRating() {
        Rating movieRating = ratingsResource.getMovieRating("100");
        assertThat(movieRating).isNotNull();
        assertThat(movieRating.getMovieId()).isEqualTo("100");
        assertThat(movieRating.getRating()).isEqualTo(4);
    }

    @Test
    void shouldGetUserRatings() {
        UserRating userRatings = ratingsResource.getUserRatings("100");
        assertThat(userRatings).isNotNull();
        assertThat(userRatings.getUserId()).isEqualTo("100");
        assertThat(userRatings.getRatings()).hasSize(2);
        assertThat(userRatings.getRatings()).containsSequence(new Rating("100", 3), new Rating("200", 4));
    }

}
