package com.example.ensf480.User.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisteredUser {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String address;
    private final String creditCardNumber;
    private final Integer ccv;
    private final String expiryDate;
    private Date validUntil;

    public RegisteredUser(@JsonProperty("id") UUID id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("address") String address,
            @JsonProperty("creditCardNumber") String creditCardNumber,
            @JsonProperty("ccv") String ccv,
            @JsonProperty("expiryDate") String expiryDate) {
        if (id == null) {
            this.id = UUID.randomUUID();
        } else {
            this.id = id;
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
        this.password = password;
        this.address = address.trim();
        this.creditCardNumber = creditCardNumber.trim();
        this.expiryDate = expiryDate.trim();

        try {
            this.ccv = Integer.parseInt(ccv);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CCV must be a number");
        }

        this.validateRegisteredUser();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, 1);
        this.validUntil = cal.getTime();

    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public UUID getId() {
        return id;
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

    public String getPassword() {
        return password;
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

    public Date getValidUntil() {
        return validUntil;
    }

    public boolean validateRegisteredUser() {
        if (firstName == null || firstName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is required");
        }
        if (firstName.matches("[a-zA-Z]+") == false) {
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

        if (password == null || password.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }

        if (password.length() > 255) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be less than 255 characters");
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

        if (expiryDate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expiry date is required");
        }

        return true;
    }
}
