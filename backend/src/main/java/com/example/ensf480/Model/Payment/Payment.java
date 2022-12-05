package com.example.ensf480.Model.Payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Payment {
    private PaymentStrategy paymentStrategy;
    private String ccNo;
    private int ccv;
    private String expDate;

    public Payment(PaymentStrategy paymentStrategy, String ccNo, int ccv, String expDate) {
        this.paymentStrategy = paymentStrategy;
        this.ccNo = ccNo;
        this.ccv = ccv;
        this.expDate = expDate;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

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
