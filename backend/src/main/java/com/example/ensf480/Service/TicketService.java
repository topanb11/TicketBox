package com.example.ensf480.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ensf480.Dao.TicketDao;
import com.example.ensf480.Model.Ticket;

@Service
public class TicketService {
    private final TicketDao ticketDao;

    @Autowired
    public TicketService(@Qualifier("postgresTicket") TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketDao.createTicket(ticket);
    }

    public void deleteTicket(Map<String, Object> ticketMap) {
        String id = (String) ticketMap.get("id");
        ticketDao.deleteTicket(id);
    }
}
