import { useState, useMemo } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { createTheme } from "@mui/material/styles";
import { ThemeProvider } from "@emotion/react";
import Home from "./pages/Home";
import Register from "./pages/Register";
import LogIn from "./pages/LogIn";
import MoviePage from "./pages/MoviePage";
import SeatSelection from "./pages/SeatSelection";
import { UserContext } from "./context/UserContext";
import { MoviesContext } from "./context/MoviesContext";

// custom theme
const theme = createTheme({
  palette: {
    primary: {
      main: "#E16138",
    },
  },
});

function App() {
  // set up context for user and movies list
  const [user, setUser] = useState(null);
  const [movies, setMovies] = useState([]);
  const userContextValue = useMemo(() => ({ user, setUser }), [user, setUser]);
  const moviesContextValue = useMemo(
    () => ({ movies, setMovies }),
    [movies, setMovies]
  );

  return (
    <ThemeProvider theme={theme}>
      <div>
        <BrowserRouter>
          <MoviesContext.Provider value={moviesContextValue}>
            <UserContext.Provider value={userContextValue}>
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<LogIn />} />
                <Route path="/movies" element={<MoviePage />} />
                <Route path="/seatselection" element={<SeatSelection />} />
              </Routes>
            </UserContext.Provider>
          </MoviesContext.Provider>
        </BrowserRouter>
      </div>
    </ThemeProvider>
  );
}

export default App;
