package com.michaelkotten.movieinfoservice.resources;

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

import com.michaelkotten.movieinfoservice.IntegrationTest;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieResource.class)
@IntegrationTest
class MovieResourceIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldGetMovieInfo() throws Exception {
        mvc.perform(get("/movies/100").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.movieId", is("100")))
           .andExpect(jsonPath("$.name", is("Joker")));
    }
}
