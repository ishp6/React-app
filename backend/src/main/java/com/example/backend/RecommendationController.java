package com.example.backend;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:5173", "http://127.0.0.1:5173" })
public class RecommendationController {

    private final AnimeRecommendationService animeRecommendationService;

    public RecommendationController(AnimeRecommendationService animeRecommendationService) {
        this.animeRecommendationService = animeRecommendationService;
    }

    @GetMapping("/genres")
    public Map<String, List<String>> genres() {
        return Map.of("genres", animeRecommendationService.getGenres());
    }

    @GetMapping("/recommendations")
    public Map<String, Object> recommendations(@RequestParam String genre,
            @RequestParam(defaultValue = "6") int limit) {
        List<Anime> recommendations = animeRecommendationService.recommendByGenre(genre, limit);
        return Map.of(
                "genre", genre,
                "count", recommendations.size(),
                "recommendations", recommendations);
    }
}
