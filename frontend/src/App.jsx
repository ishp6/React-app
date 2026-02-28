import { useEffect, useState } from 'react';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

function App() {
  const [message, setMessage] = useState('Loading message...');
  const [error, setError] = useState('');

  useEffect(() => {
    fetch(`${API_BASE_URL}/api/message`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Request failed with status ${response.status}`);
        }
        return response.json();
      })
      .then((data) => setMessage(data.message))
      .catch((fetchError) => {
        setError(fetchError.message);
        setMessage('Could not load backend message.');
      });
  }, []);

  return (
    <main className="container">
      <h1>Simple React + Java App</h1>
      <p>This message is served by the Java backend:</p>
      <blockquote>{message}</blockquote>
      {error && <p className="error">Error: {error}</p>}
    </main>
  );
}

export default App;
