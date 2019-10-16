package com.michaelkotten.moviecatalogservice.models;

import java.util.List;

public class UserRating {

    private String userId;
    private List<Rating> ratings;

    protected UserRating() {
    }

    public UserRating(String userId, List<Rating> ratings) {
        this.userId = userId;
        this.ratings = ratings;
    }

    public String getUserId() {
        return userId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

}
