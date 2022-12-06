package com.example.ensf480.Model.Payment;

import java.util.List;

import com.example.ensf480.Model.Ticket;

// Receipt Object to send to users
public class Receipt {
		// List of Tickets purchased
    private List<Ticket> tickets;
		// Subtotal cost
    private double subTotal;
		// Total cost after tax
    private double total;
		// Service fee
    private static final double SERVICE_FEE = 0.05;
		// General Sales Tax
    private static final double GST = 0.05;
		// Cost of each ticket
    private static final double costPerTicket = 17.50;

		/**
		 * Constructor for Receipt
		 * @param tickets - List of tickets in receipt
		 */
    public Receipt(List<Ticket> tickets) {
        this.tickets = tickets;
        this.subTotal = calculateSubTotal();
        this.total = calculateTotal();
    }

		/**
		 * Helper to calculate the subtotal of purchase
		 * @return double
		 */
    private double calculateSubTotal() {
        double total = tickets.size() * costPerTicket;
        return total;
    }

		/**
		 * Helper to calculate total of purchase
		 * @return
		 */
    private double calculateTotal() {
        double total = subTotal + (subTotal * SERVICE_FEE) + (subTotal * GST);
        return total;
    }

		/**
		 * Getter to fetch all Tickets
		 * @return List<Ticket>
		 */
    public List<Ticket> getTickets() {
        return tickets;
    }

		/**
		 * Getter to fetch total cost
		 * @return double
		 */
    public double getTotal() {
        return total;
    }

		/**
		 * Getter to fetch subtotal
		 * @return double
		 */
    public double getSubTotal() {
        return subTotal;
    }

		/**
		 * Getter to fetch serviceFee
		 * @return String
		 */
    public String getServiceFee() {
        return String.valueOf(SERVICE_FEE * 100) + "%";
    }

		/**
		 * Getter to fetch GST 
		 * @return String
		 */
    public String getGST() {
        return String.valueOf(GST * 100) + "%";
    }

		/**
		 * Getter to fetch cost per Ticket
		 * @return String
		 */
    public String getCostPerTicket() {
        return "$" + String.valueOf(costPerTicket);
    }

    public static Double getCostPerTicketNumerical() {
        return costPerTicket;
    }

}
