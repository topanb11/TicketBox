package com.example.ensf480.Model.Payment;

public interface PaymentStrategy {
    boolean processPayment(double amount);
    boolean refund(double amount, String email);
}
