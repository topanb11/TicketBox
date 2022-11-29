import React, { useState } from "react";
import { styled } from "@mui/system";
import { Button, Grid, TextField } from "@mui/material";

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
  const [loggedIn, setLoggedIn] = useState(false);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [ccNo, setCcNo] = useState("");
  const [ccv, setCCV] = useState("");
  const [expDate, setExpDate] = useState("");

  const count = props.seats.length;

  const validForm = () => {
    if (loggedIn) {
      return count !== 0;
    }
    return (
      firstName !== "" &&
      lastName !== "" &&
      email != "" &&
      address != "" &&
      ccNo != "" &&
      ccv != "" &&
      expDate != "" &&
      count !== 0
    );
  };

  const handleSubmit = () => {
    if (loggedIn) {
      var data = {
        total: 17.5 * count,
      };
    } else {
      var data = {
        firstName,
        lastName,
        email,
        address,
        ccNo,
        ccv,
        expDate,
        total: 17.5 * count,
      };
    }
    console.log(data);
  };

  if (loggedIn) {
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
              onChange={(event) => setCcNo(event.target.value)}
            ></TextField>
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="CCV"
              value={ccv}
              onChange={(event) => setCCV(event.target.value)}
            ></TextField>
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Expiration Date"
              value={expDate}
              onChange={(event) => setExpDate(event.target.value)}
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
