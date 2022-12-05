import React, { useState, useContext } from "react";
import NavBar from "../components/NavBar";
import { styled } from "@mui/system";
import Logo from "../assets/logo.svg";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import DialogTitle from "@mui/material/DialogTitle";
import Dialog from "@mui/material/Dialog";
import DialogContent from "@mui/material/DialogContent";
import { Link, useNavigate } from "react-router-dom";
import { UserContext } from "../context/UserContext";
import axios from "axios";

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

const ButtonContainer = styled("div")({
  display: "flex",
  width: "100%",
  gap: "20px",
  paddingTop: "20px",
  justifyContent: "center",
});

const SubmitButton = styled(Button)({
  borderRadius: "0px",
  fontFamily: "Roboto, sans-serif",
  boxShadow: "none",
});

const CancelButton = styled(Button)({
  borderRadius: "0px",
  fontFamily: "Roboto, sans-serif",
  boxShadow: "none",
});

const Body = styled("div")({
  fontFamily: "Roboto, sans-serif",
  color: "#0F1020",
  fontWeight: "400",
  fontSize: "20px",
});

const LogIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [dialogOpen, setDialogOpen] = useState(false);
  const navigate = useNavigate();
	const { user, setUser } = useContext(UserContext);

  const emailRgx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;

  const continueAsGuest = () => {
    setDialogOpen(false);
    setUser(null);
    navigate("/");
  };

  const reactivateAccount = () => {
    axios
      .post("http://localhost:8080/api/v1/user/reactivate", {id: user.id})
      .then( response => {
        setUser(response.data);
        navigate("/");
      })
      .catch(function(error) {
        console.log(error.response.data.message);
      });
  };

  const renderDialog = () => {
    return (
      <Dialog open={dialogOpen} onClose={() => setDialogOpen(false)}>
        <DialogTitle>Unable to log in</DialogTitle>
        <DialogContent>
          <Body>
            Your account has expired. Please reactivate your account for $9.99
            or head back to the home page to continue as a guest.
          </Body>
          <ButtonContainer>
            <CancelButton variant="outlined" onClick={continueAsGuest}>
              CONTINUE AS GUEST
            </CancelButton>
            <SubmitButton variant="contained" onClick={reactivateAccount}>
              ACTIVATE ACCOUNT
            </SubmitButton>
          </ButtonContainer>
        </DialogContent>
      </Dialog>
    );
  };

  const handleSubmit = () => {
		axios.post("http://localhost:8080/api/v1/user/login", {
			email: email,
			password: password
		})
		.then(response => {
      setUser(response.data);
      const currentDate = new Date();
			const receivedDate = new Date(response.data.validUntil);
			const isRU = true;
			const validRU = (receivedDate.getTime() > currentDate.getTime()) ? true : false;
			if (isRU && !validRU) {
        setDialogOpen(true);
        
      } else {
        alert("Login Successful!");
        setDialogOpen(false);
        navigate("/");
      }
		})
		.catch(response => {
			alert(response.response.data.message);
		})
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
            disabled={!emailRgx.test(email) || password === ""}
            onClick={handleSubmit}
            sx={{ color: "white" }}
            fullWidth
          >
            LOG IN
          </SubmitButton>
        </FormContainer>
      </Wrapper>
      {renderDialog()}
    </>
  );
};

export default LogIn;
