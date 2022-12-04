import React, { useEffect, useState } from "react";
import { styled } from '@mui/system';
import { useLocation } from "react-router-dom";
import NavBar from "../components/NavBar";
import MovieItem from "../components/MovieItem";
import covers from "../components/Covers";

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
	var hash = Object.create(null);

	// Merging movies with their respective covers
	const data = location.state.movies;	
	data.concat(covers).forEach((obj) => {
		hash[obj.id] = Object.assign(hash[obj.id] || {}, obj);
	});
	var mergedData = Object.keys(hash).map((key) => {
		return hash[key];
	});

	return (
		<>
			<NavBar/>
			<Header>
				<Title>SEARCH RESULTS FOR "{location.state.name}"</Title>
			</Header>
			<MovieItem data={mergedData} title={location.state.name}/>
		</>
	)
 }

export default MoviePage;
