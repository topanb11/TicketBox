import { BrowserRouter, Route, Routes } from "react-router-dom";
import { createTheme } from "@mui/material/styles";
import { ThemeProvider } from "@emotion/react";
import Home from "./pages/Home";
import Register from "./pages/Register";
import LogIn from "./pages/LogIn";
<<<<<<< HEAD
import MoviePage from "./pages/MoviePage";
=======
import SeatSelection from "./pages/SeatSelection";

>>>>>>> 28bb30496b9e5132cb8693bcf7d39f4da62a1310
const theme = createTheme({
  palette: {
    primary: {
      main: "#E16138",
    },
  },
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<LogIn />} />
<<<<<<< HEAD
						<Route path="/movies" element={<MoviePage />}/>
=======
            <Route path="/seatselection" element={<SeatSelection />} />
>>>>>>> 28bb30496b9e5132cb8693bcf7d39f4da62a1310
          </Routes>
        </BrowserRouter>
      </div>
    </ThemeProvider>
  );
}

export default App;
