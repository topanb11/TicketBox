package com.example.ensf480.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import com.example.ensf480.Model.Ticket;

@Repository("postgresTicket")
public class TicketPostgresAccessService implements TicketDao {

    private final JdbcTemplate jdbcTemplate;
    private final String INSERT_QUERY = "INSERT INTO ticket (id, showtimeId, seatNo, buyerEmail, ruFlag) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE_QUERY = "DELETE FROM ticket WHERE id = ?";
    private final String GET_SEATS_BY_SHOWTIME = "SELECT seatNo FROM ticket WHERE showtimeId = ?";
    private final String CHECK_IF_SHOWTIME_EXISTS = "SELECT EXISTS(SELECT 1 FROM showtime WHERE id = ?)";


    @Autowired
    public TicketPostgresAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        // UUID showTimeId = UUID.nameUUIDFromBytes(ticket.getShowtimeId().getBytes());        
        // jdbcTemplate.query(CHECK_IF_SHOWTIME_EXISTS, (rs, rowNum) -> {
        //     if (rs.getBoolean(1) == false) {
        //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Showtime does not exist");
        //     }
        //     return null;
        // }, showTimeId);

        List<Integer> result = jdbcTemplate.queryForList(GET_SEATS_BY_SHOWTIME, Integer.class, ticket.getShowtimeId());

        if (result.contains(ticket.getSeatNo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Seat already taken");
        }

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
    public String deleteTicket(String id, Boolean isRu) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            return "Please enter a valid ticket number.";
        }
        Object[] args = new Object[] { uuid };
        int res = jdbcTemplate.update(DELETE_QUERY, args);
        if (res == 1) {
            if (isRu) {
                return "Successfuly refunded ticket with no fee!";
            }
            return "Successfuly refunded ticket with a 15% refund fee.";
        }
        return "Could not find ticket.";
    }

    @Override
    public List<Integer> getSeatsByShowtime(UUID showtime_id) {
        List<Integer> result = jdbcTemplate.queryForList(GET_SEATS_BY_SHOWTIME, Integer.class, showtime_id.toString());
        if (result.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tickets found for showtime.");
        }
        return result;
    }
}
