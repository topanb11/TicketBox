import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { styled } from "@mui/system";
import Button from "@mui/material/Button";
import Logo from "../assets/logo.png"

const Container = styled("div")({
  display: "flex",
  width: "100%",
  justifyContent: "space-around",
  paddingTop: "20px",
  paddingBottom:"20px"
});

const NavButton = styled(Button)({
  color: "black",
  backgroundColor:"white",
  fontFamily: "Roboto, sans-serif",
  "&:hover": {
    backgroundColor:"white",
    textDecoration: "underline",
  },
});

const TextContainer = styled('div')({
    display:"flex",
    gap:"20px"
})

const Icon = styled('img')({
    "&:hover": {
        cursor:"pointer"
      },
})

const NavBar = (props) => {
  const navigate = useNavigate();
  const [loggedIn, setLoggedIn] = useState(false);
  if (loggedIn) {
    return (
      <Container>
        <Icon src={Logo} width="50px" onClick={() => navigate("/")}></Icon>
        <TextContainer>
            <NavButton variant="text" onClick={() => navigate("/services")}>
            Log Out
            </NavButton>
        </TextContainer>
      </Container>
    );
  } else {
    return (
      <Container>
          <Icon src={Logo} width="50px" onClick={() => navigate("/")}></Icon>
          <TextContainer>
                <NavButton variant="text" onClick={() => navigate("/register")}>
                Create Account
                </NavButton>
                <NavButton variant="text" onClick={() => navigate("/login")}>
                Log In
                </NavButton>
          </TextContainer>
      </Container>
    );
  }
};
export default NavBar;
