package com.example.ensf480.Model.Payment;

public class MastercardPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        // Implement logic to process a Mastrecard payment
        System.out.println("Processing $" + amount + " Mastercard payment");
        return true;
    }

    @Override
    public boolean refund(double amount, String email) {
        // Implement logic to refund a Mastercard payment
        System.out.println("Refunding $" + amount + " Mastercard payment for " + email);
        return true;
    }
}
