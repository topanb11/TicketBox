package com.example.ensf480.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.swing.tree.RowMapper;

import com.example.ensf480.Model.Ticket;

// Implementation of functions from Ticket interface
@Repository("postgresTicket")
public class TicketPostgresAccessService implements TicketDao {

    // Instance of JdbcTemplate to access database
    private final JdbcTemplate jdbcTemplate;
    // SQL queries to fetch data
    private final String INSERT_QUERY = "INSERT INTO ticket (id, showtimeId, seatNo, buyerEmail, ruFlag) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE_QUERY = "DELETE FROM ticket WHERE id = ?";
    private final String GET_SEATS_BY_SHOWTIME = "SELECT seatNo FROM ticket WHERE showtimeId = ?";
    private final String GET_SHOWTIME = "SELECT showtime FROM showtime WHERE id = ?";
    private final String GET_TICKET = "SELECT * FROM ticket WHERE id = ?";
    private final String GET_SHOWTIME_COUNT = "SELECT COUNT(*) FROM showtime WHERE id = ?";

    // Depdency Injection
    @Autowired
    public TicketPostgresAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Function to add Ticket to database table
    @Override
    public Ticket createTicket(Ticket ticket) {
        UUID showTimeId = UUID.fromString(ticket.getShowtimeId());

        Integer count = jdbcTemplate.queryForObject(GET_SHOWTIME_COUNT, Integer.class, showTimeId);

        if (count == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Showtime does not exist");
        }

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

    // Function to remove a Ticket from the database table
    @Override
    public String deleteTicket(String id, Boolean isRu) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            return "Please enter a valid ticket number.";
        }
        // get ticket object
        Ticket result;
        try {
            result = jdbcTemplate.queryForObject(GET_TICKET, (resultSet, i) -> {
                Ticket temp = new Ticket(
                        resultSet.getString("showtimeId"),
                        resultSet.getInt("seatNo"),
                        resultSet.getString("buyerEmail"),
                        resultSet.getBoolean("ruFlag"));
                return temp;
            }, UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Could not find ticket");
        }
        // get movie time
        String time;
        Object[] params = { UUID.fromString(result.getShowtimeId()) };
        try {
            time = (String) jdbcTemplate.queryForObject(
                    GET_SHOWTIME, String.class, params);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Could not find ticket");
        }
        System.out.println(time);
        Calendar future = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            future.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        future.add(Calendar.HOUR_OF_DAY, -72);
        System.out.println(future.getTime());
        Calendar cur = Calendar.getInstance();
        if (cur.compareTo(future) > 0) {
            return "Can't cancel tickets for movies that are playing in less than 72 hours.";
        }
        Object[] args = new Object[] { uuid };
        int res = jdbcTemplate.update(DELETE_QUERY, args);
        if (res == 1) {
            if (isRu) {
                return "Successfuly refunded ticket with no fee!";
            }
            return "You have successfuly received a refund of $14.87";
        }
        return "Could not find ticket.";
    }

    // Function to get seats based on showtime
    @Override
    public List<Integer> getSeatsByShowtime(UUID showtime_id) {
        List<Integer> result = jdbcTemplate.queryForList(GET_SEATS_BY_SHOWTIME, Integer.class, showtime_id.toString());
        return result;
    }

    // Function to cancel a pending ticket
    @Override
    public void cancelPendingTicket(String id) {
        Object[] args = new Object[] { UUID.fromString(id) };
        jdbcTemplate.update(DELETE_QUERY, args);
    }

}
