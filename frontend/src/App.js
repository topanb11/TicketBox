import { BrowserRouter, Route, Routes, } from "react-router-dom";
import { createTheme } from '@mui/material/styles';
import { ThemeProvider } from '@emotion/react';
import Home from "./pages/Home"
const theme = createTheme({
  palette: {
    primary: {
      main: '#E16138',
    },
  },
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home/>}/>
          </Routes>
        </BrowserRouter>
      </div>
    </ThemeProvider>
  );
}

export default App;
