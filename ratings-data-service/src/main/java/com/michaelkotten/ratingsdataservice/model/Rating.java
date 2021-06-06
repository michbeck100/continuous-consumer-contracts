package com.michaelkotten.ratingsdataservice.model;

import java.util.Objects;

public class Rating {

    private String movieId;
    private int rating;

    public Rating(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rating that = (Rating) o;
        return rating == that.rating && movieId.equals(that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, rating);
    }

    @Override
    public String toString() {
        return "Rating{" +
            "movieId='" + movieId + '\'' +
            ", rating=" + rating +
            '}';
    }
}
