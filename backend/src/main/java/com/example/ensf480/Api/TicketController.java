package com.example.ensf480.Api;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ensf480.Model.Ticket;
import com.example.ensf480.Service.TicketService;

@RequestMapping("api/v1/ticket")
@RestController
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(path = "create")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        System.out.println("Creating ticket");
        return ticketService.createTicket(ticket);
    }

    @DeleteMapping(path = "/delete")
    public String deleteTicket(@RequestBody Map<String, Object> ticketMap) {
        System.out.println("hello");
        return ticketService.deleteTicket(ticketMap);
    }

}
