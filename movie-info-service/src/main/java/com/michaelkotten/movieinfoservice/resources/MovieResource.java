package com.michaelkotten.movieinfoservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaelkotten.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        List<Movie> movies = Arrays.asList(new Movie("100", "Joker",
                        "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."),
                new Movie("200", "El Camino: A Breaking Bad Movie",
                        "In the wake of his dramatic escape from captivity, Jesse Pinkman must come to terms with his past in order to forge some kind of future."));

        return movies.stream()
                     .filter(movie -> movie.getMovieId()
                                           .equals(movieId))
                     .findFirst()
                     .orElse(null);
    }

}
