package com.example.ensf480.Model.Payment;

// MasterCardPayment strategy incase user pays with MasterCard
public class MastercardPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        // Implement logic to process a Mastrecard payment
        System.out.println("Processing $" + amount + " Mastercard payment");
        return true;
    }
}
