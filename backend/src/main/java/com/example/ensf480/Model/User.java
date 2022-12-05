package com.example.ensf480.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    protected final String firstName;
    protected final String lastName;
    protected final String email;
    protected final String address;
    protected final String creditCardNumber;
    protected final Integer ccv;
    protected final String expiryDate;

    public User(@JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("creditCardNumber") String creditCardNumber,
            @JsonProperty("ccv") String ccv,
            @JsonProperty("expiryDate") String expiryDate) {
                this.firstName = firstName.trim();
                this.lastName = lastName.trim();
                this.address = address.trim();
                this.creditCardNumber = creditCardNumber.trim();
                this.expiryDate = expiryDate.trim();
                this.email = email.trim();

                try {
                    this.ccv = Integer.parseInt(ccv);
                } catch (NumberFormatException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CCV must be a number");
                }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public Integer getCcv() {
        return ccv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public boolean validateUser() {
        if (firstName == null || firstName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is required");
        }
        if (firstName.matches("[a-zA-Z]+") == false) {
            System.out.println(this.firstName);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name must only contain letters");
        }

        if (firstName.length() > 255) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name must be less than 255 characters");
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name is required");
        }

        if (lastName.matches("[a-zA-Z]+") == false) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name must only contain letters");
        }

        if (lastName.length() > 255) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name must be less than 255 characters");
        }

        if (email == null || email.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }

        if (email.length() > 255) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email must be less than 255 characters");
        }

        if (address == null || address.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address is required");
        }

        if (address.length() > 255) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address must be less than 255 characters");
        }

        if (creditCardNumber == null || creditCardNumber.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit card number is required");
        }

        if (creditCardNumber.length() > 255) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Credit card number must be less than 255 characters");
        }

        if (ccv == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CCV is required");
        }

        if (ccv.toString().length() != 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CCV must be 3 digits");
        }

        return true;
    }
}
