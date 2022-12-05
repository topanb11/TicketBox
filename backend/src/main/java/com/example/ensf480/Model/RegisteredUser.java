package com.example.ensf480.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisteredUser extends User {
    private final UUID id;
    private final String password;
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
                super(firstName, lastName, email, address, creditCardNumber, ccv, expiryDate);
        if (id == null) {
            this.id = UUID.randomUUID();
        } else {
            this.id = id;
        }
        this.password = password.trim();

        super.validateUser();
        this.validateRegisteredUserProperties();

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

    public String getPassword() {
        return password;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public boolean validateRegisteredUserProperties() {
        if (password == null || password.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }

        if (password.length() > 255) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be less than 255 characters");
        }

        if (expiryDate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expiry date is required");
        }

        return true;
    }
}
