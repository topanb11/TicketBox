import styled from "@emotion/styled";
import React, { useEffect, useState, useContext } from "react";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import Button from "@mui/material/Button";
import { Navigate, useNavigate } from "react-router";
import { MoviesContext } from "../context/MoviesContext.js";
import moment from "moment";

const MovieListContainer = styled("ul")({
  paddingLeft: "10%",
  paddingRight: "10%",
});

const MovieListItem = styled("li")({
  listStyle: "none",
  borderTop: "solid 1px",
  width: "95%",
  height: "220px",
  display: "flex",
  alignItems: "flex-end",
  marginBottom: "20px",
});

const MovieDetailContainer = styled("div")({
  display: "flex",
  flexDirection: "column",
  font: "Roboto",
  fontSize: 22,
  marginLeft: "25px",
});

const MovieTitle = styled("h3")({
  fontSize: 32,
  height: 20,
  fontFamily: "Roboto, sans-serif",
});

const ViewSeatsButton = styled(Button)({
  backgroundColor: "#E16138",
  color: "white",
  width: "342px",
  height: "48px",
  border: "none",
  fontSize: 16,
  boxShadow: "none",
  borderRadius: "0",
});

const MovieImg = styled("img")({
  width: "130px",
  height: "200px",
});

const ShowtimeText = styled("div")({
  height: 40,
  fontFamily: "Roboto, sans-serif",
});

const DropdownMenuStyle = {
  width: "220px",
  height: "50px",
};

const MovieItem = ({ search }) => {
  const { movies, setMovies } = useContext(MoviesContext);
  const navigate = useNavigate();

  const handleTimeChange = (event, data) => {
    let newMovies = [...movies];
    for (let i = 0; i < movies.length; i++) {
      if (newMovies[i].id == data.id) {
        for (let j = 0; j < newMovies[i].showtimes.length; j++) {
          if (newMovies[i].showtimes[j].timestamp == event.target.value) {
            newMovies[i].selectedShowtime = newMovies[i].showtimes[j];
            break;
          }
        }
        break;
      }
    }
    setMovies(newMovies);
  };

  const convertTime = (time) => {
    console.log(time);
    let unixtime = Date.parse(time);
    console.log(unixtime);
    unixtime /= 1000;
    return moment.unix(unixtime).format("MMM Do YYYY, h:mm A");
  };

  const handleClick = (movie) => {
    console.log(movie);
    navigate("/seatselection", {
      state: {
        movie,
      },
    });
  };

  if (
    movies.filter((data) => data.name.toLowerCase().includes(search)).length ==
    0
  ) {
    return (
      <MovieListContainer>
        <ShowtimeText>No movies found</ShowtimeText>
      </MovieListContainer>
    );
  }

  return (
    <>
      <MovieListContainer>
        {movies
          .filter((data) => data.name.toLowerCase().includes(search))
          .map((data) => (
            <MovieListItem key={data.id}>
              <MovieImg src={data.cover} />
              <MovieDetailContainer>
                <MovieTitle>{data.name}</MovieTitle>
                <ShowtimeText>Showtimes</ShowtimeText>
                <FormControl sx={DropdownMenuStyle} size="small">
                  <Select
                    value={data.selectedShowtime.timestamp}
                    onChange={(event) => handleTimeChange(event, data)}
                  >
                    {data.showtimes.map((showtime) => (
                      <MenuItem
                        key={showtime.showtimeID}
                        value={showtime.timestamp}
                      >
                        {convertTime(showtime.timestamp)}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <ViewSeatsButton
                  variant="contained"
                  onClick={() => handleClick(data)}
                >
                  VIEW SEATS
                </ViewSeatsButton>
              </MovieDetailContainer>
            </MovieListItem>
          ))}
      </MovieListContainer>
    </>
  );
};

export default MovieItem;
