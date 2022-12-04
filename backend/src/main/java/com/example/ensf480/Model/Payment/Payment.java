package com.example.ensf480.Model.Payment;

import java.util.Calendar;

public class Payment {
    private PaymentStrategy paymentStrategy;
    private String ccNo;
    private int ccv;
    private int expDate;

    public Payment(PaymentStrategy paymentStrategy, String ccNo, int ccv, int expDate) {
        this.paymentStrategy = paymentStrategy;
        this.ccNo = ccNo;
        this.ccv = ccv;
        this.expDate = expDate;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean validExpDate(int expDate) {
        Calendar currentDate = Calendar.getInstance();

        Calendar expiry = Calendar.getInstance();
        int currentYear = expiry.get(Calendar.YEAR);
        currentYear /= 100;
        currentYear *= 100;
        int expiryYear = currentYear + expDate % 100;
        int expiryMonth = expDate / 100;
        if (expiryMonth > 12 || expiryMonth < 1) {
            return false;
        }
        expiry.set(Calendar.YEAR, expiryYear);
        expiry.set(Calendar.MONTH, expiryMonth - 1);
        System.out.println(currentDate.getTime());
        System.out.println(expiry.getTime());
        if ((currentDate.compareTo(expiry)) > 0) {
            return false;
        }

        return true;
    }

    public String processPayment(double amount) {
        if (!validExpDate(expDate)) {
            return "Invalid Expiry Date";
        }
        if (!paymentStrategy.processPayment(amount)) {
            return "Failed to process payment. Please check credit card details and try again.";
        }
        return "Success";
    }
}
