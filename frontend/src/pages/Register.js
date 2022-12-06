import React, { useState, useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import NavBar from "../components/NavBar";
import { styled } from "@mui/system";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { UserContext } from "../context/UserContext";
import axios from "axios";
const Wrapper = styled("div")({
  width: "100%",
  display: "flex",
  justifyContent: "center",
});

const FormContainer = styled("div")({
  display: "flex",
  flexDirection: "column",
  gap: "20px",
  width: "40%",
  justifyContent: "space-around",
  paddingTop: "20px",
  paddingBottom: "50px",
});

const SubmitButton = styled(Button)({
  borderRadius: "0px",
  fontFamily: "Roboto, sans-serif",
  boxShadow: "none",
  height: "45px",
});

const Title = styled("div")({
  fontFamily: "Bebas Neue, cursive",
  fontSize: "48px",
});

const Body = styled("div")({
  fontFamily: "Roboto, sans-serif",
  color: "#0F1020",
  fontWeight: "400",
  fontSize: "20px",
  width: "100%",
  textAlign: "center",
});

const Error = styled("div")({
  fontFamily: "Roboto, sans-serif",
  color: "red",
  fontWeight: "400",
  fontSize: "14px",
  width: "100%",
  textAlign: "center",
});

const Register = () => {
  const navigate = useNavigate();
  const { user, setUser } = useContext(UserContext);

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [address, setAddress] = useState("");
  const [ccNo, setCCNo] = useState("");
  const [ccv, setCCV] = useState("");
  const [expDate, setExpDate] = useState("");
  const [errMessage, setErrMessage] = useState();

  // check validity of each input field
  const validForm = () => {
    const emailRgx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i; // regex to check for valid email
    return (
      emailRgx.test(email) &&
      password != "" &&
      firstName != "" &&
      lastName != "" &&
      address != "" &&
      ccNo.length === 16 &&
      ccv.length === 3 &&
      expDate.length === 4
    );
  };

  // get input values and submit to api to create account
  const handleSubmit = () => {
    const data = {
      email,
      password,
      firstName,
      lastName,
      address,
      creditCardNumber: ccNo,
      ccv,
      expiryDate: expDate,
    };
    axios
      .post("http://localhost:8080/api/v1/user/create", data)
      .then((res) => {
        setUser(res.data);
        navigate("/");
      })
      .catch(function (error) {
        setErrMessage(error.response.data.message);
      });
  };

  // make sure only numbers are entered and credit card number is not more tahn 16 digits
  const handleCCInput = (event) => {
    const val = event.target.value;
    if (val === "" || (val.length < 17 && /^-?\d+$/.test(val))) {
      setCCNo(val);
    }
  };

  // make sure only numbers are entered and ccv is not more tahn 3 digits
  const handleCCVInput = (event) => {
    const val = event.target.value;
    if (val === "" || (val.length < 4 && /^-?\d+$/.test(val))) {
      setCCV(val);
    }
  };

  // make sure only numbers are entered and expirty date is not more tahn 4 digits
  const handleExpDateInput = (event) => {
    const val = event.target.value;
    if (val === "" || (val.length < 5 && /^-?\d+$/.test(val))) {
      setExpDate(val);
    }
  };

  return (
    <>
      <NavBar></NavBar>
      <Wrapper>
        <FormContainer>
          <Title>Register</Title>
          <TextField
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            id="Email"
            label="Email"
            variant="outlined"
          />
          <TextField
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            id="Password"
            label="Password"
            variant="outlined"
          />
          <TextField
            value={firstName}
            onChange={(event) => setFirstName(event.target.value)}
            id="First Name"
            label="First Name"
            variant="outlined"
          />
          <TextField
            value={lastName}
            onChange={(event) => setLastName(event.target.value)}
            id="Last Name"
            label="Last Name"
            variant="outlined"
          />
          <TextField
            value={address}
            onChange={(event) => setAddress(event.target.value)}
            id="Address"
            label="Address"
            variant="outlined"
          />
          <TextField
            value={ccNo}
            onChange={(event) => handleCCInput(event)}
            id="Credit Card Number"
            label="Credit Card Number"
            variant="outlined"
          />
          <div style={{ display: "flex", gap: "10px" }}>
            <TextField
              value={ccv}
              onChange={(event) => handleCCVInput(event)}
              id="CCV"
              label="CCV"
              variant="outlined"
              fullWidth
            />
            <TextField
              value={expDate}
              onChange={(event) => handleExpDateInput(event)}
              id="Expiration Date"
              label="Expiration Date"
              variant="outlined"
              fullWidth
            />
          </div>
          <SubmitButton
            variant="contained"
            disabled={!validForm()}
            onClick={handleSubmit}
          >
            REGISTER
          </SubmitButton>
          {errMessage != "" && <Error>{errMessage}</Error>}
          <Body>NOTE: You will be charged $9.99 anually </Body>
        </FormContainer>
      </Wrapper>
    </>
  );
};

export default Register;
