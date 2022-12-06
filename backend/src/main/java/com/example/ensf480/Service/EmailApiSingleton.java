package com.example.ensf480.Service;

import java.io.IOException;
import java.util.List;

import com.example.ensf480.Model.Ticket;
import com.example.ensf480.Model.Payment.Receipt;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class EmailApiSingleton {

    private static EmailApiSingleton onlyInstance = null;
    SendGrid sendGridClient;
    private static final String API_KEY = "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";
    private static final String FROM_EMAIL = "TicketBoxConfirmation@gmail.com";

    private EmailApiSingleton() {
        sendGridClient = new SendGrid(API_KEY);
    }

    public static EmailApiSingleton getOnlyInstance() {
        if (onlyInstance == null) {
            onlyInstance = new EmailApiSingleton();
        }
        return onlyInstance;
    }

    public void sendConfirmationEmail(String to, Receipt receipt) {
        Email from = new Email(FROM_EMAIL);
        Email toEmail = new Email(to);

        String subject = "Your Ticket Purchase Receipt";

        List<Ticket> tickets = receipt.getTickets();
        String serviceFee = receipt.getServiceFee();
        String GST = receipt.getGST();
        double subTotal = receipt.getSubTotal();
        double total = receipt.getTotal();

        String seatsHtmlContent = "<table><tr><th>Seat Number</th><th>Ticket Number</th></tr>";
        for (Ticket ticket : tickets) {
            seatsHtmlContent += "<tr><td>" + ticket.getSeatNo() + "</td><td>" + ticket.getId() + "</td></tr>";
        }
        seatsHtmlContent += "</table>";

        String recieptHtmlContent = "<table><tr><th>Item</th><th>Cost</th><th>Quantity</th><th>Subtotal</th><th>Service Fee</th><th>GST</th><th>Total</th></tr>";
        recieptHtmlContent += "<tr><td>Ticket</td><td>" + receipt.getCostPerTicket() + "</td><td>" + tickets.size() + "</td><td>$" + subTotal + "</td><td>" + serviceFee + "</td><td>" + GST + "</td><td>$" + total + "</td></tr>";
        recieptHtmlContent += "</table>";

        String htmlContent = "<h1>Thank you for your purchase!</h1>";
        htmlContent += "<br><h2>Here is your receipt:</h2><br>";
        htmlContent += seatsHtmlContent;
        htmlContent += recieptHtmlContent;



        Content content = new Content("text/html", htmlContent);

        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = this.sendGridClient;
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getHeaders());
            System.out.println(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRefundEmail(boolean isRu, String to, String id) {
        Email from = new Email(FROM_EMAIL);
        Email toEmail = new Email(to);

        String subject = "Your Ticket has been refunded";

        String htmlContent = "";
        if (isRu) {
            htmlContent += "<h3>You have successfully refunded ticket no: " + id + "</h3>";
        } else {
            htmlContent += "<h3>You have successfully refunded ticket no: " + id + "</h3>";
            htmlContent += "<h3>$" + 0.85 * Receipt.getCostPerTicketNumerical() + " (15%) has been withheld as a cancellation fee. </h3>";
        }

        Content content = new Content("text/html", htmlContent);

        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = this.sendGridClient;
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getHeaders());
            System.out.println(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }


       
    }
}
