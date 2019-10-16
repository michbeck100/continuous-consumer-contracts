package com.michaelkotten.moviecatalogservice.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.michaelkotten.moviecatalogservice.models.CatalogItem;
import com.michaelkotten.moviecatalogservice.models.Movie;
import com.michaelkotten.moviecatalogservice.models.Rating;
import com.michaelkotten.moviecatalogservice.models.UserRating;
import com.michaelkotten.moviecatalogservice.services.MovieService;
import com.michaelkotten.moviecatalogservice.services.UserRatingService;

@ExtendWith(MockitoExtension.class)
class CatalogResourceTest {

    private static final String USER_ID = "100";
    private static final String MOVIE_ID = "movie";

    private CatalogResource catalogResource;

    @Mock
    private UserRatingService userRatingService;

    @Mock
    private MovieService movieService;

    @Test
    void shouldReturnCatalogItem() {
        Rating rating = new Rating(MOVIE_ID, 5);
        UserRating userRating = new UserRating(USER_ID, Collections.singletonList(rating));
        doReturn(userRating)
                .when(userRatingService).getUserRating(USER_ID);
        Movie movie = new Movie(MOVIE_ID, "name", "description");
        doReturn(movie)
                .when(movieService).getMovie(MOVIE_ID);
        catalogResource = new CatalogResource(userRatingService, movieService);

        List<CatalogItem> catalog = catalogResource.getCatalog(USER_ID);

        assertThat(catalog).hasSize(1);
        CatalogItem catalogItem = catalog.get(0);
        assertThat(catalogItem.getName()).isEqualTo(movie.getName());
        assertThat(catalogItem.getDesc()).isEqualTo(movie.getDescription());
        assertThat(catalogItem.getRating()).isEqualTo(rating.getRating());
    }

}
