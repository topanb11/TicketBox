package com.example.ensf480.Dao;

import com.example.ensf480.Model.Ticket;

public interface TicketDao {
    Ticket createTicket(Ticket ticket);

    void deleteTicket(String id);

}
