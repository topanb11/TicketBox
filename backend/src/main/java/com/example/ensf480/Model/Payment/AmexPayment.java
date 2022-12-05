package com.example.ensf480.Model.Payment;

// AmexPayment strategy incase user pays via Amex
public class AmexPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        // Implement logic to process an American Express payment
        System.out.println("Processing $" + amount + " American Express payment");
        return true;
    }
}
