package com.example.ensf480.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ensf480.Dao.TicketDao;
import com.example.ensf480.Model.RegisteredUser;
import com.example.ensf480.Model.Ticket;
import com.example.ensf480.Model.User;

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

    public String deleteTicket(Map<String, Object> ticketMap) {
        String id = (String) ticketMap.get("ticketNo");
        Boolean isRu = (Boolean) ticketMap.get("isRu");
        return ticketDao.deleteTicket(id, isRu);
    }

    public List<Integer> getSeatsByShowtime(UUID showtime_id) {
        return ticketDao.getSeatsByShowtime(showtime_id);
    }

    public List<Ticket> checkout(List<Integer> seats, String showtimeId, User user) {
        List<Ticket> ticketNos = new ArrayList<Ticket>();
        Boolean isRu = user instanceof RegisteredUser;

        for (Integer seat : seats) {
            Ticket ticket = new Ticket(showtimeId, seat, user.getEmail(), isRu);
            ticketNos.add(ticketDao.createTicket(ticket));
        }

        return ticketNos;
    }
}
