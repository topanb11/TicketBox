package com.example.ensf480.Model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisteredUser {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String address;
    private final String creditCardNumber;
    private final String ccv;
    private final String expiryDate;
    private final String validUntil;

    public RegisteredUser(@JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("address") String address,
            @JsonProperty("creditCardNumber") String creditCardNumber,
            @JsonProperty("ccv") String ccv,
            @JsonProperty("expiryDate") String expiryDate,
            @JsonProperty("validUntil") String validUntil) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.ccv = ccv;
        this.expiryDate = expiryDate;
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

    public String getCcv() {
        return ccv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getValidUntil() {
        return validUntil;
    }
}
