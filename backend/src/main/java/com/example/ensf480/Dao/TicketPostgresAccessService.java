package com.example.ensf480.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ensf480.Model.Ticket;

@Repository("postgresTicket")
public class TicketPostgresAccessService implements TicketDao {

    private final JdbcTemplate jdbcTemplate;
    private final String INSERT_QUERY = "INSERT INTO ticket (id, showtimeId, seatNo, buyerEmail, ruFlag) VALUES (?, ?, ?, ?, ?)";

    @Autowired
    public TicketPostgresAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        try {
            jdbcTemplate.update(INSERT_QUERY,
                    new Object[] { ticket.getId(), ticket.getShowtimeId(), ticket.getSeatNo(), ticket.getBuyerEmail(),
                            ticket.getRuFlag() });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public void deleteTicket(String id) {
        // TODO Auto-generated method stub
    }

}
