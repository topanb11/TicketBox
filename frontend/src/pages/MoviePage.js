import React, { useEffect, useState } from "react";
import { styled } from '@mui/system';
import { useLocation } from "react-router-dom";
import NavBar from "../components/NavBar";
import MovieItem from "../components/MovieItem";

const Header = styled('div')({
  display: "flex",
  width: "100%",
  justifyContent: "start",
	marginTop: "2.5%",
})

const Title = styled('div')({
  fontFamily: "Bebas Neue, cursive",
  fontSize:"72px",
	marginLeft: "10%"
})

const MoviePage = () => {
	const location = useLocation();
	return (
		<>
			<NavBar/>
			<Header>
				<Title>SEARCH RESULTS FOR "{location.state.name}"</Title>
			</Header>
			<MovieItem data={location.state.movies} title={location.state.name}/>
		</>
	)
 }

export default MoviePage;
