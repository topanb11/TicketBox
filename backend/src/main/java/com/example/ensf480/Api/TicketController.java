package com.example.ensf480.Api;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ensf480.Model.RegisteredUser;
import com.example.ensf480.Model.Ticket;
import com.example.ensf480.Model.User;
import com.example.ensf480.Service.RegisteredUserService;
import com.example.ensf480.Service.TicketService;

// API route for Ticket entity
@RequestMapping("api/v1/ticket")
@RestController
@CrossOrigin
public class TicketController {
		// Instances of TicketService and RegisteredUserService to gain access to methods
    private final TicketService ticketService;
    private final RegisteredUserService registeredUserService;

		// Dependency injeciton
    @Autowired
    public TicketController(TicketService ticketService, RegisteredUserService userService) {
        this.ticketService = ticketService;
        this.registeredUserService = userService;
    }

		// API route to create a instantiate a new Ticket object
    @PostMapping(path = "create")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

		// API route to delete a Ticket object
    @DeleteMapping(path = "/delete")
    public String deleteTicket(@RequestBody Map<String, Object> ticketMap) {
        return ticketService.deleteTicket(ticketMap);
    }

		// API route to fetch all Tickets based on showtime
    @GetMapping(path = "/seats/by/{showtime_id}")
    public List<Integer> getSeatsByShowtime(@PathVariable("showtime_id") String showtime_id) {
        return ticketService.getSeatsByShowtime(UUID.fromString(showtime_id));
    }

		// API route to process payment for registered users
    @PostMapping(path = "/checkout/ru")
    public List<Ticket> checkout(@RequestBody Map<String, Object> ticketMap) {
        String showtimeId = (String) ticketMap.get("showtimeId");
        String buyerEmail = (String) ticketMap.get("buyerEmail");
        List<Integer> seats = (List<Integer>) ticketMap.get("seats");

        RegisteredUser ru = registeredUserService.getUserByEmail(buyerEmail);
        if (ru != null) {
            return ticketService.checkout(seats, showtimeId, ru);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

		// API route to process payment for guest users
    @PostMapping(path = "/checkout/guest")
    public List<Ticket> checkoutGuest(@RequestBody Map<String, Object> ticketMap) {
        String showtimeId = (String) ticketMap.get("showtimeId");
        String buyerEmail = (String) ticketMap.get("buyerEmail");
        List<Integer> seats = (List<Integer>) ticketMap.get("seats");

        String firstName = (String) ticketMap.get("firstName");
        String lastName = (String) ticketMap.get("lastName");
        String ccv = (String) ticketMap.get("ccv");
        String cardNumber = (String) ticketMap.get("cardNumber");
        String expiryDate = (String) ticketMap.get("expiryDate");
        String address = (String) ticketMap.get("address");

        User user = new User(firstName, lastName, buyerEmail, address, cardNumber, ccv, expiryDate);

        List<Ticket> ticketNos = ticketService.checkout(seats, showtimeId, user);
        return ticketNos;
    }

}
