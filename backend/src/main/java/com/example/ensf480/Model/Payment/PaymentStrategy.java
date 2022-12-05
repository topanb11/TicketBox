package com.example.ensf480.Model.Payment;

// Interface for PaymentStrategy
public interface PaymentStrategy {
		/**
		 * Method to process payment
		 * @param amount - Total cost of order
		 * @return boolean
		 */
    boolean processPayment(double amount);
}
