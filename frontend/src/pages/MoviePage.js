import React, { useEffect, useState, useContext } from "react";
import { styled } from "@mui/system";
import { useLocation } from "react-router-dom";
import NavBar from "../components/NavBar";
import MovieItem from "../components/MovieItem";
import { MoviesContext } from "../context/MoviesContext.js";
import { listItemSecondaryActionClasses } from "@mui/material";

const Header = styled("div")({
  display: "flex",
  width: "100%",
  justifyContent: "start",
  marginTop: "2.5%",
});

const Title = styled("div")({
  fontFamily: "Bebas Neue, cursive",
  fontSize: "72px",
  marginLeft: "10%",
});

const MoviePage = () => {
  const location = useLocation();
  // display page title and render movie list by calling MovieItem component
  return (
    <>
      <NavBar />
      <Header>
        <Title>SEARCH RESULTS FOR "{location.state.search}"</Title>
      </Header>
      <MovieItem search={location.state.search} />
    </>
  );
};

export default MoviePage;
