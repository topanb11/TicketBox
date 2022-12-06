package com.example.ensf480.Model.Payment;

public class VisaPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        // Implement logic to process a Visa payment
        System.out.println("Processing $" + amount + " Visa payment");
        return true;
    }

    @Override
    public boolean refund(double amount, String email) {
        // Implement logic to refund a Visa payment
        System.out.println("Refunding $" + amount + " Visa payment for " + email);
        return true;
    }
}
