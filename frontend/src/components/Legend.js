import React from "react";
import { styled } from "@mui/system";

const Container = styled("div")({
  display: "flex",
  gap: "20px",
});

const SubContainer = styled("div")({
  display: "flex",
  gap: "10px",
  alignItems: "center",
});

const Box = styled("div")({
  height: "1.5rem",
  width: "1.5rem",
});

const Body = styled("div")({
  fontFamily: "Roboto, sans-serif",
  color: "#0F1020",
  fontWeight: "400",
  fontSize: "20px",
});

const Legend = () => {
  return (
    <Container>
      <SubContainer>
        <Box
          style={{
            backgroundColor: "black",
          }}
        ></Box>
        <Body>Unavailable</Body>
      </SubContainer>
      <SubContainer>
        <Box
          style={{
            backgroundColor: "#E16138",
          }}
        ></Box>
        <Body>Selected</Body>
      </SubContainer>
      <SubContainer>
        <Box
          style={{
            backgroundColor: "white",
            border: "#E16138 1px solid",
          }}
        ></Box>
        <Body>Available</Body>
      </SubContainer>
    </Container>
  );
};

export default Legend;
