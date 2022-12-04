package com.example.ensf480.Dao;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.ensf480.Model.RegisteredUser;

@Repository("Postgres")
public class RegisteredUserPostgresAccessService implements RegisteredUserDao {

    private final JdbcTemplate jdbcTemplate;
    private final String INSERT_QUERY = "INSERT INTO ru (id, firstName, lastName, email, password, address, creditCardNumber, creditCardExpirationDate, ccv, validUntil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String CHECK_EMAIL_EXISTS = "SELECT COUNT(*) FROM ru WHERE email = ?";
    private final String GET_USER = "SELECT * FROM ru WHERE email = ? AND password = ?";
    private final String GET_USER_BY_ID = "SELECT * FROM ru WHERE id = ?";
    private final String UPDATE_USER = "UPDATE ru SET validUntil = ? WHERE id = ?";

    @Autowired
    public RegisteredUserPostgresAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RegisteredUser insertPerson(RegisteredUser person) {
        int count = jdbcTemplate.queryForObject(CHECK_EMAIL_EXISTS, Integer.class, person.getEmail());

        if (count > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Timestamp validUntilTimestamp = new Timestamp(person.getValidUntil().getTime());
        jdbcTemplate.update(INSERT_QUERY,
                new Object[] { person.getId(), person.getFirstName(), person.getLastName(), person.getEmail(),
                        person.getPassword(), person.getAddress(), person.getCreditCardNumber(), person.getExpiryDate(),
                        person.getCcv(), validUntilTimestamp });

        return person;
    }

    @Override
    public RegisteredUser login(String email, String password) {
        RegisteredUser result;
        try {
            result = jdbcTemplate.queryForObject(GET_USER, (resultSet, i) -> {
                RegisteredUser temp = new RegisteredUser(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("address"),
                        resultSet.getString("creditCardNumber"),
                        resultSet.getString("ccv"),
                        resultSet.getString("creditCardExpirationDate"));
                temp.setValidUntil(resultSet.getTimestamp("validUntil"));
                return temp;
            }, email, password);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        return result;
    }

    @Override
    public RegisteredUser reactivate(String id) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, 1);
        Date validUntil = cal.getTime();
        Object[] params = { new Timestamp(validUntil.getTime()), UUID.fromString(id) };
        int rowsAffected = jdbcTemplate.update(UPDATE_USER, params);
        RegisteredUser result;
        try {
            result = jdbcTemplate.queryForObject(GET_USER_BY_ID, (resultSet, i) -> {
                RegisteredUser temp = new RegisteredUser(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("address"),
                        resultSet.getString("creditCardNumber"),
                        resultSet.getString("ccv"),
                        resultSet.getString("creditCardExpirationDate"));
                temp.setValidUntil(resultSet.getTimestamp("validUntil"));
                return temp;
            }, UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unable to reactivate account");
        }
        return result;
    }

}
