package com.example.ensf480.Api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
        return ticketService.createTicket(ticket);
    }

    @PostMapping(path = "delete")
    public void deleteTicket(@RequestBody Map<String, Object> ticketMap) {
        ticketService.deleteTicket(ticketMap);
    }

}
