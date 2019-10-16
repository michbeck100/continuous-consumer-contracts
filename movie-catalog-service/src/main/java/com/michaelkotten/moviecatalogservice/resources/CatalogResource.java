package com.michaelkotten.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaelkotten.moviecatalogservice.models.CatalogItem;
import com.michaelkotten.moviecatalogservice.models.Movie;
import com.michaelkotten.moviecatalogservice.models.UserRating;
import com.michaelkotten.moviecatalogservice.services.MovieService;
import com.michaelkotten.moviecatalogservice.services.UserRatingService;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    private final UserRatingService userRatingService;
    private final MovieService movieService;

    @Autowired
    public CatalogResource(UserRatingService userRatingService, MovieService movieService) {
        this.userRatingService = userRatingService;
        this.movieService = movieService;
    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = userRatingService.getUserRating(userId);

        return userRating.getRatings()
                         .stream()
                         .map(rating -> {
                             Movie movie = movieService.getMovie(rating.getMovieId());
                             return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                         })
                         .collect(Collectors.toList());

    }
}
