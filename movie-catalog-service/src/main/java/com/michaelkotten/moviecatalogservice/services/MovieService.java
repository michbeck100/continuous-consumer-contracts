package com.michaelkotten.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.michaelkotten.moviecatalogservice.models.Movie;

@Service
public class MovieService {
    private RestTemplate restTemplate;
    private String movieServiceUrl;

    @Autowired
    public MovieService(RestTemplate restTemplate, @Value("${movie-info-service.url}") String movieServiceUrl) {
        this.restTemplate = restTemplate;
        this.movieServiceUrl = movieServiceUrl;
    }

    public Movie getMovie(String movieId) {
        Movie movie = restTemplate.getForObject(movieServiceUrl + "/movies/" + movieId,
                Movie.class);
        return movie;
    }
}
