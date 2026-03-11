package com.example.backend;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void genresEndpointReturnsKnownGenres() throws Exception {
        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genres", hasItem("Action")))
                .andExpect(jsonPath("$.genres", hasItem("Drama")));
    }

    @Test
    void recommendationEndpointFiltersByGenreAndReturnsRankedAnime() throws Exception {
        mockMvc.perform(get("/api/recommendations").queryParam("genre", "Action").queryParam("limit", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genre").value("Action"))
                .andExpect(jsonPath("$.count").value(3))
                .andExpect(jsonPath("$.recommendations[0].genres", hasItem("Action")))
                .andExpect(jsonPath("$.recommendations[0].rating", greaterThan(8.0)));
    }
}
