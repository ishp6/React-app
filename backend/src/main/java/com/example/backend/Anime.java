package com.example.backend;

import java.util.List;

public record Anime(
        String id,
        String title,
        List<String> genres,
        double rating,
        int year,
        String synopsis,
        String imageUrl) {
}
