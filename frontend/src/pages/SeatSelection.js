import React, { useContext, useState, useEffect } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import { styled } from "@mui/system";
import NavBar from "../components/NavBar.js";
import Grid from "@mui/material/Grid";
import Seat from "../components/Seat";
import Legend from "../components/Legend";
import Checkout from "../components/Checkout.js";
import { UserContext } from "../context/UserContext.js";
import axios from "axios";

const Wrapper = styled("div")({
  display: "flex",
  flexDirection: "column",
  padding: "50px 10%",
});

const Title = styled("div")({
  fontFamily: "Bebas Neue, cursive",
  fontSize: "48px",
  marginBottom: "10px",
});

const Body = styled("div")({
  fontFamily: "Roboto, sans-serif",
  color: "#0F1020",
  fontWeight: "400",
  fontSize: "20px",
});

const TheatreContainer = styled("div")({
  display: "flex",
  flexDirection: "column",
  padding: "35px 15%",
  alignItems: "center",
});

const Screen = styled("div")({
  width: "100%",
  backgroundColor: "black",
  color: "white",
  height: "40px",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  marginBottom: "5%",
});

const SeatContainer = styled(Grid)({
  padding: "0 10% 5% 10%",
});

const movie = {
  id: "0df2909f-813c-424f-a774-fa7df72cffe4",
  name: "Men in Black",
  cover:
    "https://m.media-amazon.com/images/M/MV5BOTlhYTVkMDktYzIyNC00NzlkLTlmN2ItOGEyMWQ4OTA2NDdmXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg",
};

const showtime = {
  id: "0df2909f-813c-424f-a774-fa7df72cfd12",
  timestamp: "2023-12-04 19:00:00.814",
};

const SeatSelection = () => {
  // Object that has movie attributes
  const location = useLocation();
  const { user, setUser } = useContext(UserContext);
  const [selectedSeats, setSelectedSeats] = useState([]); // keep track of which seats have been selected
  const [unavailableSeats, setUnavailableSeats] = useState([]);
  // 48 seats per theatre
  const seats = [];
  for (let i = 0; i < 48; i++) {
    seats.push(i + 1);
  }

  useEffect(() => {
    getUnavailableSeats();
  }, []);

  const getUnavailableSeats = () => {
    const showtime_id = showtime.id;
    axios
      .get(`http://localhost:8080/api/v1/ticket/seats/by/${showtime_id}`, {})
      .then((response) => {
        console.log(response);
        setUnavailableSeats(response.data);
      })
      .catch((response) => {
        alert(response.response.data.message);
      });
  };

  // handle selecting seats -> add/remove seat from selectedSeats
  const clickSeat = (seat) => {
    console.log("seat clicked", seat);
    if (selectedSeats.includes(seat)) {
      console.log("removing");
      // remove seat from selection
      const updatedSeats = selectedSeats.filter((s) => s !== seat);
      setSelectedSeats(updatedSeats);
    } else {
      console.log("adding");
      // add seat to selection
      const updatedSeats = [...selectedSeats, seat];
      setSelectedSeats(updatedSeats);
    }
  };

  // check state of seat (available, unavailable, selected) and pass in correct value to Seat component
  const renderSeat = (seat) => {
    if (selectedSeats.includes(seat)) {
      return (
        <Grid item xs={2} key={seat}>
          <Seat state="selected" seatNo={seat} handleClick={clickSeat}></Seat>
        </Grid>
      );
    }
    if (unavailableSeats.includes(seat)) {
      return (
        <Grid item xs={2} key={seat}>
          <Seat state="unavailable" seatNo={seat}></Seat>
        </Grid>
      );
    }
    return (
      <Grid item xs={2} key={seat}>
        <Seat state="available" seatNo={seat} handleClick={clickSeat}></Seat>
      </Grid>
    );
  };

  return (
    <>
      <NavBar></NavBar>
      <Wrapper>
        <Title>{location.state.title}</Title>
        <Body>{location.state.showtime.time}</Body>
        <TheatreContainer>
          <Screen>SCREEN</Screen>
          <SeatContainer container spacing={2}>
            {seats.map((seat) => renderSeat(seat))}
          </SeatContainer>
          <Legend></Legend>
        </TheatreContainer>
        <Checkout seats={selectedSeats}></Checkout>
      </Wrapper>
    </>
  );
};

export default SeatSelection;
