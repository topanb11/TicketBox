import React, { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { styled } from "@mui/system";
import NavBar from "../components/NavBar.js";
import Logo from "../assets/logo.png";
import Icon from "../assets/Saly-1.png";
import { Button, TextField } from "@mui/material";
import { UserContext } from "../context/UserContext.js";
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
	const [movies, setMovies] = useState([]);
  const { user, setUser } = useContext(UserContext);

  const handleChange = (event) => {
    if (event.target.id == "search") {
      setSearch(event.target.value);
    }
    if (event.target.id == "ticketNo") {
      setTicketNo(event.target.value);
    }
  };

  const handleSearchSubmit = () => {
    navigate("/movies", { state: { name: search, movies: movies } });
  };

  const handleCancelSubmit = () => {
    console.log(ticketNo.length);
    setTicketNo("");
  };

	useEffect(() => {
		axios.get("http://localhost:8080/api/v1/movie/all")
			.then(function (response) {
				setMovies(response.data);
			})
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
