package com.example.ensf480.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;

// Model for User entity
public class User {
    protected final String firstName;
    protected final String lastName;
    protected final String email;
    protected final String address;
    protected final String creditCardNumber;
    protected final Integer ccv;
    protected final String expiryDate;

		/**
		 * Constructor for User object
		 * @param firstName - User's first name
		 * @param lastName - User's last name
		 * @param email - User's email
		 * @param address - User's address
		 * @param creditCardNumber - User's credit card number
		 * @param ccv - User's ccv number for credit card
		 * @param expiryDate - User's credit card expiration date
		 */
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

		/**
		 * Getter for first name
		 * @return firstName: String
		 */
    public String getFirstName() {
        return firstName;
    }

		/**
		 * Getter for last name
		 * @return lastName: String
		 */
    public String getLastName() {
        return lastName;
    }

		/**
		 * Getter for email
		 * @return email: String
		 */
    public String getEmail() {
        return email;
    }

		/**
		 * Getter for address
		 * @return address: String
		 */
    public String getAddress() {
        return address;
    }

		/**
		 * Getter for credit card number
		 * @return creditCardNumber: String
		 */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

		/**
		 * Getter for ccv number
		 * @return ccv: Integer
		 */
    public Integer getCcv() {
        return ccv;
    }

		/**
		 * Getter for expiration date of credit card
		 * @return expiryDate: String
		 */
    public String getExpiryDate() {
        return expiryDate;
    }

		/**
		 * Helper function to validate user
		 * @return boolean
		 */
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
