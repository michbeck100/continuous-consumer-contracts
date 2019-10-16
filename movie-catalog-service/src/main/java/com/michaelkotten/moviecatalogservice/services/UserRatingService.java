package com.michaelkotten.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.michaelkotten.moviecatalogservice.models.UserRating;

@Service
public class UserRatingService {
    private RestTemplate restTemplate;
    private String ratingServiceUrl;

    @Autowired
    public UserRatingService(RestTemplate restTemplate, @Value("${ratings-data-service.url}") String ratingServiceUrl) {
        this.restTemplate = restTemplate;
        this.ratingServiceUrl = ratingServiceUrl;
    }

    public UserRating getUserRating(String userId) {
        UserRating userRating = restTemplate.getForObject(ratingServiceUrl + "/ratingsdata/user/" + userId,
                UserRating.class);
        return userRating;
    }
}
