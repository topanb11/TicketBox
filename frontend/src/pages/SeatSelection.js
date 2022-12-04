import React, { useContext, useState } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import { styled } from "@mui/system";
import NavBar from "../components/NavBar.js";
import Grid from "@mui/material/Grid";
import Seat from "../components/Seat";
import Legend from "../components/Legend";
import Checkout from "../components/Checkout.js";
import { UserContext } from "../context/UserContext.js";

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

const SeatSelection = () => {
  // Object that has movie attributes
  const location = useLocation();
  const { user, setUser } = useContext(UserContext);
  const [selectedSeats, setSelectedSeats] = useState([]); // keep track of which seats have been selected
  const unavailableSeats = [1, 5, 7, 12, 25, 34, 35]; // hardcoded unavailable seats
  // 48 seats per theatre
  const seats = [];
  for (let i = 0; i < 48; i++) {
    seats.push(i + 1);
  }
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
        <Body>{location.state.showtime} PM</Body>
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
