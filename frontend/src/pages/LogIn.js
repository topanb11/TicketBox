import React, { useState } from "react";
import NavBar from "../components/NavBar";
import { styled } from "@mui/system";
import Logo from "../assets/logo.svg";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";

const Wrapper = styled("div")({
  width: "100%",
  display: "flex",
  justifyContent: "center",
  height: "70vh",
  alignItems: "center",
});

const FormContainer = styled("div")({
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  gap: "25px",
  alignItems: "center",
  width: "20%",
});

const SubmitButton = styled(Button)({
  borderRadius: "0px",
  fontFamily: "Roboto, sans-serif",
  boxShadow: "none",
  height: "45px",
});

const LogIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = () => {
    const data = {
      email,
      password,
    };
    console.log(data);
  };

  return (
    <>
      <NavBar></NavBar>
      <Wrapper>
        <FormContainer>
          <img src={Logo} width="60%"></img>
          <TextField
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            id="Email"
            label="Email"
            variant="outlined"
            fullWidth
          />
          <TextField
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            id="Password"
            label="Password"
            variant="outlined"
            fullWidth
          />
          <SubmitButton
            variant="contained"
            disabled={email === "" || password === ""}
            onClick={handleSubmit}
            sx={{ color: "white" }}
            fullWidth
          >
            LOG IN
          </SubmitButton>
        </FormContainer>
      </Wrapper>
    </>
  );
};

export default LogIn;
