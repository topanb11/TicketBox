package com.example.ensf480.Model.Payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// Concrete payment class that uses PaymentStrategy interface
public class Payment {
    // Instance of PaymentStrategy
    private PaymentStrategy paymentStrategy;
    // Credit card number
    private String ccNo;
    // CCV number
    private int ccv;
    // Expiration date
    private String expDate;

    /**
     * Constructo for Payment object
     * 
     * @param paymentStrategy - Instance of payment strategy depending on how user
     *                        pays for order
     * @param ccNo            - Credit card number
     * @param ccv             - CCV number
     * @param expDate         - Expiration Date
     */
    public Payment(PaymentStrategy paymentStrategy, String ccNo, int ccv, String expDate) {
        this.paymentStrategy = paymentStrategy;
        this.ccNo = ccNo;
        this.ccv = ccv;
        this.expDate = expDate;
    }

    /**
     * Setter to change payment strategy
     * 
     * @param paymentStrategy - Instance of PaymentStrategy
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * Checks if expiration date is valid
     * 
     * @param expDate - Expiration date
     * @return boolean
     */
    public boolean validExpDate(String expDate) {
        String monthStr = expDate.substring(0, 2);
        int month = Integer.parseInt(monthStr);
        if (month < 1 || month > 12) {
            return false;
        }
        try {
            Date expirationDate = new SimpleDateFormat("MMyy").parse(expDate);
            System.out.println("expirationDate: " + expirationDate.getTime());
            Date currentDate = new Date();
            if (expirationDate.after(currentDate)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    public Boolean processRefund(double amount, String email) {
        return paymentStrategy.refund(amount, email);
    }

    public String processPayment(double amount) {
        if (!validExpDate(expDate)) {
            return "Invalid Expiry Date";
        }
        if (!paymentStrategy.processPayment(amount)) {
            return "Could not process payment. Please check card details and try again.";
        }
        return "Success";
    }
}
