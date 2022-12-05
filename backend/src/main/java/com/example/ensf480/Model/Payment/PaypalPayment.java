package com.example.ensf480.Model.Payment;

public class PaypalPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        // Implement logic to process a Paypal payment
        System.out.println("Processing $" + amount + " Paypal payment");
        return true;
    }
}
