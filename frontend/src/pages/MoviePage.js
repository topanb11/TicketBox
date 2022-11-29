import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import NavBar from "../components/NavBar";

const MoviePage = () => {
	const location = useLocation();

	return (
		<>
			<NavBar/>
			<h1>movie page</h1>
			<h3>movie search is: {location.state.name}</h3>
		</>
	)
 }

export default MoviePage;
