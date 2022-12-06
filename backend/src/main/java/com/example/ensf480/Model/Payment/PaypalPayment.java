package com.example.ensf480.Model.Payment;

// PaypalPAyment strategy incase user pays via Paypal
public class PaypalPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        // Implement logic to process a Paypal payment
        System.out.println("Processing $" + amount + " Paypal payment");
        return true;
    }

    @Override
    public boolean refund(double amount, String email) {
        // Implement logic to refund a Paypal payment
        System.out.println("Refunding $" + amount + " Paypal payment, for " + email);
        return true;
    }

    
}
