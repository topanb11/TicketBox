package com.example.ensf480.Model.Payment;

// VisaPayment strategy incase user pays via Visa
public class VisaPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        // Implement logic to process a Visa payment
        System.out.println("Processing $" + amount + " Visa payment");
        return true;
    }
}
