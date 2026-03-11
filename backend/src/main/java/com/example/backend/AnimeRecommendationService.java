package com.example.backend;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class AnimeRecommendationService {

    private static final List<Anime> ANIME_LIBRARY = List.of(
            new Anime("AOT", "Attack on Titan", List.of("Action", "Drama", "Fantasy"), 9.1, 2013,
                    "Humanity's last survivors fight towering titans behind giant walls.",
                    "https://images.unsplash.com/photo-1578632767115-351597cf2477?auto=format&fit=crop&w=900&q=80"),
            new Anime("FMAB", "Fullmetal Alchemist: Brotherhood", List.of("Action", "Adventure", "Fantasy"), 9.2,
                    2009, "Two brothers search for the Philosopher's Stone to restore what they lost.",
                    "https://images.unsplash.com/photo-1511512578047-dfb367046420?auto=format&fit=crop&w=900&q=80"),
            new Anime("YYH", "Your Lie in April", List.of("Romance", "Drama", "Music"), 8.6, 2014,
                    "A piano prodigy rediscovers music and life through a free-spirited violinist.",
                    "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?auto=format&fit=crop&w=900&q=80"),
            new Anime("DS", "Demon Slayer", List.of("Action", "Fantasy", "Adventure"), 8.7, 2019,
                    "A kind-hearted boy joins the Demon Slayer Corps to save his sister.",
                    "https://images.unsplash.com/photo-1542204625-de293a9b7b0b?auto=format&fit=crop&w=900&q=80"),
            new Anime("VIO", "Violet Evergarden", List.of("Drama", "Slice of Life", "Romance"), 8.9, 2018,
                    "A former child soldier learns empathy by writing letters for others.",
                    "https://images.unsplash.com/photo-1519682337058-a94d519337bc?auto=format&fit=crop&w=900&q=80"),
            new Anime("STEINS", "Steins;Gate", List.of("Sci-Fi", "Thriller", "Drama"), 9.0, 2011,
                    "A self-proclaimed mad scientist discovers time travel and its consequences.",
                    "https://images.unsplash.com/photo-1534447677768-be436bb09401?auto=format&fit=crop&w=900&q=80"),
            new Anime("HNTR", "Hunter x Hunter", List.of("Action", "Adventure", "Fantasy"), 9.0, 2011,
                    "A young boy pursues his father and battles through dangerous trials.",
                    "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?auto=format&fit=crop&w=900&q=80"),
            new Anime("MOB", "Mob Psycho 100", List.of("Action", "Comedy", "Supernatural"), 8.8, 2016,
                    "A powerful psychic middle-schooler tries to live a normal life.",
                    "https://images.unsplash.com/photo-1489515217757-5fd1be406fef?auto=format&fit=crop&w=900&q=80"));

    public List<String> getGenres() {
        Set<String> genres = new LinkedHashSet<>();
        ANIME_LIBRARY.stream()
                .flatMap(anime -> anime.genres().stream())
                .sorted()
                .forEach(genres::add);
        return List.copyOf(genres);
    }

    public List<Anime> recommendByGenre(String genre, int limit) {
        String requested = genre == null ? "" : genre.toLowerCase(Locale.ROOT);
        return ANIME_LIBRARY.stream()
                .filter(anime -> anime.genres().stream()
                        .map(value -> value.toLowerCase(Locale.ROOT))
                        .anyMatch(value -> value.equals(requested)))
                .sorted(Comparator.comparingDouble(Anime::rating).reversed())
                .limit(Math.max(limit, 1))
                .collect(Collectors.toList());
    }
}
