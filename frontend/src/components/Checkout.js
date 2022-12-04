import React, { useState, useContext } from "react";
import { styled } from "@mui/system";
import { Button, Grid, TextField } from "@mui/material";
import { UserContext } from "../context/UserContext";

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

  const count = props.seats.length;

  const validForm = () => {
    const emailRgx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
    if (user) {
      return count !== 0;
    }
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

  const handleSubmit = () => {
    if (user) {
      var data = {
        total: 17.5 * count,
        selectedSeats: props.seats,
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
        selectedSeats: props.seats,
      };
    }
    console.log(data);
  };

  const handleCCInput = (event) => {
    const val = event.target.value;
    if (val === "" || (val.length < 17 && /^-?\d+$/.test(val))) {
      setCCNo(val.trim());
    }
  };

  const handleCCVInput = (event) => {
    const val = event.target.value;
    if (val === "" || (val.length < 4 && /^-?\d+$/.test(val))) {
      setCCV(val.trim());
    }
  };

  const handleExpDateInput = (event) => {
    const val = event.target.value;
    if (val === "" || (val.length < 5 && /^-?\d+$/.test(val))) {
      setExpDate(val.trim());
    }
  };

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
