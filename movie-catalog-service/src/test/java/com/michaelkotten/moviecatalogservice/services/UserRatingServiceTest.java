package com.michaelkotten.moviecatalogservice.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.michaelkotten.moviecatalogservice.models.Rating;
import com.michaelkotten.moviecatalogservice.models.UserRating;

@ExtendWith(MockitoExtension.class)
class UserRatingServiceTest {
    private static final String RATINGS_SERVICE_URL = "ratings-data-service";
    private static final String USER_ID = "100";
    private static final String MOVIE_ID = "movie";

    @Mock
    private RestTemplate restTemplate;

    private UserRatingService service;

    @Test
    void shouldReturnUserRating() {
        UserRating expected = new UserRating(USER_ID, Collections.singletonList(new Rating(MOVIE_ID, 5)));
        doReturn(expected)
                .when(restTemplate).getForObject(RATINGS_SERVICE_URL + "/ratingsdata/user/100", UserRating.class);

        service = new UserRatingService(restTemplate, RATINGS_SERVICE_URL);

        UserRating userRating = service.getUserRating(USER_ID);
        assertThat(userRating).isNotNull();
        assertThat(userRating).isEqualTo(expected);
    }
}
