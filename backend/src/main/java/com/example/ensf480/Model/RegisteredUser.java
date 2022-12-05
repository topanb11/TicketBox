package com.example.ensf480.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;

// Model for registered user entity
public class RegisteredUser extends User {
    private final UUID id;
    private final String password;
    private Date validUntil;

		/**
		 * Constructor for RegisteredUser object
		 * @param id - Unique identifier
		 * @param firstName - Registered user's first name
		 * @param lastName - Registered user's last name
		 * @param email - Registered user's email
		 * @param password - Registered user's password
		 * @param address - Registered user's address
		 * @param creditCardNumber - Registered user's credit card number
		 * @param ccv - Registered user's ccv number 
		 * @param expiryDate - Registered user's credit card expiration date
		 */
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

		/**
		 * Setter for validUntil timestamp
		 * @param validUntil
		 */
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

		/**
		 * Getter for id
		 * @return id: UUID
		 */
    public UUID getId() {
        return id;
    }

		/**
		 * Getter for password
		 * @return password: String
		 */
    public String getPassword() {
        return password;
    }

		/**
		 * Getter for validUntil timestamp
		 * @return validUntil: Date
		 */
    public Date getValidUntil() {
        return validUntil;
    }

		/**
		 * Helper function to check if registered user is valid
		 * @return boolean
		 */
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
