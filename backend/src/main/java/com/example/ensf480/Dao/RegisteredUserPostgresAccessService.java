package com.example.ensf480.Dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ensf480.Model.RegisteredUser;

@Repository("Postgres")
public class RegisteredUserPostgresAccessService implements RegisteredUserDao {

    private final JdbcTemplate jdbcTemplate;
    private final String INSERT_QUERY = "INSERT INTO ru (id, firstName, lastName, email, password, address, creditCardNumber, creditCardExpirationDate, ccv, validUntil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Autowired
    public RegisteredUserPostgresAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RegisteredUser insertPerson(RegisteredUser person) {
        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(person.getValidUntil());
            Timestamp validUntilTimestamp = new Timestamp(date.getTime());
            jdbcTemplate.update(INSERT_QUERY,
                    new Object[] { person.getId(), person.getFirstName(), person.getLastName(), person.getEmail(),
                            person.getPassword(), person.getAddress(), person.getCreditCardNumber(),
                            person.getExpiryDate(), person.getCcv(), validUntilTimestamp });
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public double checkCredentials(String email, String password) {
        // TODO Auto-generated method stub
        return 0;
    }

}
