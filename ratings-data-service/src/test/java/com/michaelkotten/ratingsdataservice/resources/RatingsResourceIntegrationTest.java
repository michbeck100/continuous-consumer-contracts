package com.michaelkotten.ratingsdataservice.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.michaelkotten.ratingsdataservice.IntegrationTest;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RatingsResource.class)
@IntegrationTest
class RatingsResourceIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void shouldGetMovieRating() throws Exception {
        mvc.perform(get("/ratingsdata/movies/100").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.movieId", is("100")))
           .andExpect(jsonPath("$.rating", is(4)));
    }

    @Test
    void shouldGetUserRatings() throws Exception {
        mvc.perform(get("/ratingsdata/user/100").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.userId", is("100")))
           .andExpect(jsonPath("$.ratings", hasSize(2)))
           .andExpect(jsonPath("$.ratings[0].movieId", is("100")))
           .andExpect(jsonPath("$.ratings[0].rating", is(3)))
           .andExpect(jsonPath("$.ratings[1].movieId", is("200")))
           .andExpect(jsonPath("$.ratings[1].rating", is(4)));
    }

}
