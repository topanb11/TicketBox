import React, { useState } from "react";
import { styled } from "@mui/system";

const Available = styled("div")({
  backgroundColor: "white",
  border: "#E16138 solid 1px",
  color: "#E16138",
  height: "40px",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  ":hover": {
    transform: "scale(1.05)",
    cursor: "pointer",
  },
});

const Selected = styled("div")({
  backgroundColor: "#E16138",
  border: "#E16138 solid 1px",
  color: "white",
  height: "40px",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  ":hover": {
    transform: "scale(1.05)",
    cursor: "pointer",
  },
});

const Unavailable = styled("div")({
  backgroundColor: "black",
  border: "black solid 1px",
  color: "white",
  height: "40px",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
});

const Seat = (props) => {
  // render available seat
  if (props.state == "available") {
    return (
      <Available onClick={() => props.handleClick(props.seatNo)}>
        {props.seatNo}
      </Available>
    );
  }
  // render selected seat
  if (props.state == "selected") {
    return (
      <Selected onClick={() => props.handleClick(props.seatNo)}>
        {props.seatNo}
      </Selected>
    );
  }
  // render unavailable seat
  if (props.state == "unavailable") {
    return <Unavailable>{props.seatNo}</Unavailable>;
  }
};

export default Seat;
