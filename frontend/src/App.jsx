import { useEffect, useMemo, useState } from 'react';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

function App() {
  const [genres, setGenres] = useState([]);
  const [selectedGenre, setSelectedGenre] = useState('Action');
  const [recommendations, setRecommendations] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    fetch(`${API_BASE_URL}/api/genres`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Could not load genres (${response.status})`);
        }
        return response.json();
      })
      .then((data) => {
        setGenres(data.genres || []);
        if (data.genres?.length > 0) {
          setSelectedGenre((current) => (data.genres.includes(current) ? current : data.genres[0]));
        }
      })
      .catch((fetchError) => setError(fetchError.message));
  }, []);

  useEffect(() => {
    if (!selectedGenre) return;
    setIsLoading(true);
    setError('');

    fetch(`${API_BASE_URL}/api/recommendations?genre=${encodeURIComponent(selectedGenre)}&limit=6`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Could not load recommendations (${response.status})`);
        }
        return response.json();
      })
      .then((data) => setRecommendations(data.recommendations || []))
      .catch((fetchError) => setError(fetchError.message))
      .finally(() => setIsLoading(false));
  }, [selectedGenre]);

  const subtitle = useMemo(() => {
    if (!selectedGenre) return 'Pick a genre to start.';
    return `Top picks for ${selectedGenre} anime`;
  }, [selectedGenre]);

  return (
    <main className="page">
      <section className="hero card">
        <p className="eyebrow">Anime Recommender</p>
        <h1>Find your next favorite series</h1>
        <p className="muted">{subtitle}</p>
      </section>

      <section className="filters card">
        <h2>Browse by genre</h2>
        <div className="genre-list" role="list" aria-label="Anime genres">
          {genres.map((genre) => (
            <button
              key={genre}
              type="button"
              className={`genre-pill ${selectedGenre === genre ? 'active' : ''}`}
              onClick={() => setSelectedGenre(genre)}
            >
              {genre}
            </button>
          ))}
        </div>
      </section>

      {error && <p className="error">Error: {error}</p>}

      <section className="results">
        {isLoading ? (
          <p className="loading">Loading recommendations...</p>
        ) : (
          recommendations.map((anime) => (
            <article className="anime-card" key={anime.id}>
              <img src={anime.imageUrl} alt={`${anime.title} cover`} loading="lazy" />
              <div className="anime-card-content">
                <div className="anime-meta">
                  <h3>{anime.title}</h3>
                  <span>
                    ⭐ {anime.rating} · {anime.year}
                  </span>
                </div>
                <p>{anime.synopsis}</p>
                <div className="tag-wrap">
                  {anime.genres.map((genre) => (
                    <span className="tag" key={`${anime.id}-${genre}`}>
                      {genre}
                    </span>
                  ))}
                </div>
              </div>
            </article>
          ))
        )}
      </section>
    </main>
  );
}

export default App;
