import React, { useState, useContext } from "react";
import { styled } from "@mui/system";
import { Button, Grid, TextField } from "@mui/material";
import { UserContext } from "../context/UserContext";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

const SubTitle = styled("div")({
  fontFamily: "Roboto, sans-serif",
  color: "#0F1020",
  fontWeight: "800",
  fontSize: "24px",
  paddingBottom: "2%",
});

const RUPurchaseButton = styled(Button)({
  borderRadius: "0px",
  fontFamily: "Roboto, sans-serif",
  boxShadow: "none",
  height: "45px",
});

const PurchaseButton = styled(Button)({
  borderRadius: "0px",
  fontFamily: "Roboto, sans-serif",
  boxShadow: "none",
  height: "45px",
  width: "100%",
});

const Wrapper = styled("div")({
  display: "flex",
  padding: "0 15%",
});

const Checkout = (props) => {
  const { user, setUser } = useContext(UserContext);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [ccNo, setCCNo] = useState("");
  const [ccv, setCCV] = useState("");
  const [expDate, setExpDate] = useState("");
  const navigate = useNavigate();
  const count = props.seats.length;
  const showtimeId = props.showtimeId;

  // check input form validity before processing checkout
  const validForm = () => {
    const emailRgx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i; // regex to chekc for valid email
    if (user) {
      return count !== 0; // if user logged in, only need to make sure they have selected a seat
    }
    // if guest checkout, need to validate each input field
    return (
      firstName !== "" &&
      lastName !== "" &&
      emailRgx.test(email) &&
      address != "" &&
      ccNo.length === 16 &&
      ccv.length === 3 &&
      expDate.length === 4 &&
      count !== 0
    );
  };

  // handle checkout, display errror/success message and navigate back to home apge on success
  const handleSubmit = () => {
    // check if user is logged in and call correct api
    if (user) {
      axios
        .post("http://localhost:8080/api/v1/ticket/checkout/ru", {
          showtimeId: showtimeId,
          buyerEmail: user.email,
          seats: props.seats,
        })
        .then(() => {
          alert("Tickets successfuly purchased!");
          navigate("/");
        })
        .catch((response) => console.log(response));
    } else {
      axios
        .post("http://localhost:8080/api/v1/ticket/checkout/guest", {
          showtimeId: showtimeId,
          buyerEmail: email,
          seats: props.seats,
          firstName: firstName,
          lastName: lastName,
          ccv: ccv,
          cardNumber: ccNo,
          expiryDate: expDate,
          address: address,
          selectedSeats: props.seats,
        })
        .then(() => {
          alert("Ticket successfuly purchased!");
          navigate("/");
        })
        .catch((response) => {
          alert(response.response.data.message);
          console.log(response);
        });
    }
  };

  // make sure only numbers are entered and credit card number is not more tahn 16 digits
  const handleCCInput = (event) => {
    const val = event.target.value;
    if (val === "" || (val.length < 17 && /^-?\d+$/.test(val))) {
      setCCNo(val.trim());
    }
  };

  // make sure only numbers are entered and ccv is not more tahn 3 digits
  const handleCCVInput = (event) => {
    const val = event.target.value;
    if (val === "" || (val.length < 4 && /^-?\d+$/.test(val))) {
      setCCV(val.trim());
    }
  };

  // make sure only numbers are entered and expirty date is not more tahn 4 digits
  const handleExpDateInput = (event) => {
    const val = event.target.value;
    if (val === "" || (val.length < 5 && /^-?\d+$/.test(val))) {
      setExpDate(val.trim());
    }
  };

  // if user is logged in display just purchase button
  if (user) {
    return (
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
        }}
      >
        <SubTitle>
          PURCHASE {count ? count : ""} TICKET
          {props.count != 1 ? "S" : ""}
        </SubTitle>
        <RUPurchaseButton
          variant="contained"
          disabled={!validForm()}
          onClick={handleSubmit}
        >
          {count === 0 ? "PAY NOW" : `PAY $${(count * 17.5).toFixed(2)} NOW`}
        </RUPurchaseButton>
      </div>
    );
  }
  // if guest checkout, display checkout form
  return (
    <>
      <SubTitle>
        PURCHASE {count ? count : ""} TICKET
        {count != 1 ? "S" : ""}
      </SubTitle>
      <Wrapper>
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="First Name"
              value={firstName}
              onChange={(event) => setFirstName(event.target.value)}
            ></TextField>
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Last Name"
              value={lastName}
              onChange={(event) => setLastName(event.target.value)}
            ></TextField>
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
            ></TextField>
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Address"
              value={address}
              onChange={(event) => setAddress(event.target.value)}
            ></TextField>
          </Grid>
          <Grid item xs={12}>
            <TextField
              fullWidth
              label="Credit Card Number"
              value={ccNo}
              onChange={handleCCInput}
            ></TextField>
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="CCV"
              value={ccv}
              onChange={handleCCVInput}
            ></TextField>
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Expiration Date"
              value={expDate}
              onChange={handleExpDateInput}
            ></TextField>
          </Grid>
          <Grid item xs={12}>
            <PurchaseButton
              variant="contained"
              disabled={!validForm()}
              onClick={handleSubmit}
            >
              {count === 0
                ? "PAY NOW"
                : `PAY $${(count * 17.5).toFixed(2)} NOW`}
            </PurchaseButton>
          </Grid>
        </Grid>
      </Wrapper>
    </>
  );
};

export default Checkout;
