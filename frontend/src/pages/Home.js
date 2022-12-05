import React, { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { styled } from "@mui/system";
import NavBar from "../components/NavBar.js";
import Logo from "../assets/logo.png";
import Icon from "../assets/Saly-1.png";
import { alertClasses, Button, TextField } from "@mui/material";
import { UserContext } from "../context/UserContext.js";
import { MoviesContext } from "../context/MoviesContext.js";
import axios from "axios";

const Page = styled("div")({
  height: "100vh",
});

const Header = styled("div")({
  display: "flex",
  width: "100%",
  justifyContent: "center",
  alignItems: "center",
  gap: "100px",
  position: "relative",
  top: "10%",
});

const TextContainer = styled("div")({
  display: "flex",
  flexDirection: "column",
  gap: "20px",
});

const Title = styled("div")({
  fontFamily: "Bebas Neue, cursive",
  fontSize: "72px",
});

const SubTitle = styled("div")({
  fontFamily: "Roboto, sans-serif",
  color: "#0F1020",
  fontWeight: "800",
  fontSize: "24px",
});

const Body = styled("div")({
  fontFamily: "Roboto, sans-serif",
  color: "#0F1020",
  fontWeight: "400",
  fontSize: "20px",
});

const SearchButton = styled(Button)({
  borderRadius: "0px",
  fontFamily: "Roboto, sans-serif",
  boxShadow: "none",
  height: "45px",
});

const CancelButton = styled(Button)({
  borderRadius: "0px",
  fontFamily: "Roboto, sans-serif",
  boxShadow: "none",
});

const CancelContainer = styled("div")({
  display: "flex",
  width: "100%",
  justifyContent: "center",
  alignItems: "center",
  gap: "20px",
  position: "relative",
  top: "20%",
  flexDirection: "column",
  paddingBottom: "7%",
});

const CancelSubContainer = styled("div")({
  display: "flex",
  gap: "20px",
  textAlign: "center",
});

const Home = () => {
  const navigate = useNavigate();
  const [search, setSearch] = useState("");
  const [ticketNo, setTicketNo] = useState("");
  const { user, setUser } = useContext(UserContext);
  const { movies, setMovies } = useContext(MoviesContext);

  // event handler to update state on input change
  const handleChange = (event) => {
    // user is changing Search field
    if (event.target.id == "search") {
      setSearch(event.target.value);
    }
    // user is changing Cancel ticket field
    if (event.target.id == "ticketNo") {
      setTicketNo(event.target.value);
    }
  };

  // on search submit, redirect user to movies page and pass in search query
  const handleSearchSubmit = () => {
    navigate("/movies", {
      state: {
        search: search,
      },
    });
  };

  // on ticket cancelation submit, check if user is logged in and call ticket cancellation api
  const handleCancelSubmit = () => {
    const isRu = user !== null;
    axios
      .delete("http://localhost:8080/api/v1/ticket/delete", {
        data: {
          ticketNo: ticketNo,
          isRu: isRu,
        },
      })
      .then((response) => {
        alert(response.data);
        setTicketNo("");
      })
      .catch((response) => {
        alert(response.response.data.message);
        console.log(response);
      });
  };

  // on page load, call movies api to get all movies
  useEffect(() => {
    axios.get("http://localhost:8080/api/v1/movie/all").then((response) => {
      // add selectedShowtime property to each movie and set to first showtime
      let movies = response.data;
      for (let i = 0; i < movies.length; i++) {
        movies[i]["selectedShowtime"] = movies[i].showtimes[0];
      }
      setMovies(movies); // update movies context
    });
  }, []);

  return (
    <Page>
      <NavBar></NavBar>
      <Header>
        <img src={Icon} width="25%"></img>
        <TextContainer>
          <Title>SEARCH FOR A MOVIE</Title>
          <TextField
            value={search}
            onChange={handleChange}
            id="search"
          ></TextField>
          <SearchButton variant="contained" onClick={handleSearchSubmit}>
            Search
          </SearchButton>
        </TextContainer>
      </Header>
      <CancelContainer>
        <SubTitle>CANCEL TICKET</SubTitle>
        <Body>
          Looking to cancel a purchased ticket? Enter your ticket number below
        </Body>
        <CancelSubContainer>
          <TextField
            label="Ticket number"
            id="ticketNo"
            onChange={handleChange}
            value={ticketNo}
            inputProps={{ maxLength: 36 }}
          ></TextField>
          <CancelButton
            variant="outlined"
            onClick={handleCancelSubmit}
            disabled={ticketNo.trim() == "" || ticketNo.length != 36}
          >
            CANCEL TICKET
          </CancelButton>
        </CancelSubContainer>
      </CancelContainer>
    </Page>
  );
};
export default Home;
