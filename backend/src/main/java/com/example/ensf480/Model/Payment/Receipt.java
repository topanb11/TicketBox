package com.example.ensf480.Model.Payment;

import java.util.List;

import com.example.ensf480.Model.Ticket;

public class Receipt {
    private List<Ticket> tickets;
    private double subTotal;
    private double total;
    private static final double SERVICE_FEE = 0.05;
    private static final double GST = 0.05;
    private static final double costPerTicket = 17.50;

    public Receipt(List<Ticket> tickets) {
        this.tickets = tickets;
        this.subTotal = calculateSubTotal();
        this.total = calculateTotal();
    }

    private double calculateSubTotal() {
        double total = tickets.size() * costPerTicket;
        return total;
    }

    private double calculateTotal() {
        double total = subTotal + (subTotal * SERVICE_FEE) + (subTotal * GST);
        return total;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public double getTotal() {
        return total;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public String getServiceFee() {
        return String.valueOf(SERVICE_FEE * 100) + "%";
    }

    public String getGST() {
        return String.valueOf(GST * 100) + "%";
    }

    public String getCostPerTicket() {
        return "$" + String.valueOf(costPerTicket);
    }

}
