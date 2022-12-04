package com.example.ensf480.Dao;

import java.sql.Timestamp;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.ensf480.Model.RegisteredUser;


    


    private final JdbcTemplate jdbcTemplate;
    private final String INSERT_QUERY = "INSERT INTO ru (id, firstName, lastName, email, password, address, creditCardNumber, creditCardExpirationDate, ccv, validUntil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String CHECK_EMAIL_EXISTS = "SELECT COUNT(*) FROM ru WHERE email = ?";
    private final String GET_USER = "SELECT * FROM ru WHERE email = ? AND password = ?";


        this.jdbcTemplate = jdbcTemplate;
    }
        

        rride
    public RegisteredUser insertPerson(RegisteredUser person) {
        i
         
        if (count > 0) {
                 
                        
                         

        }

        Timestamp validUntilTimestamp = new Timestamp(person.getValidUntil().getTime());
        jdbcTemplate.update(INSERT_QUERY, new Object[] {person.getId(), person.getFirstName(), person.getLastName(), person.getEmail(), person.getPassword(), person.getAddress(), person.getCreditCardNumber(), person.getExpiryDate(), person.getCcv(), validUntilTimestamp}); 
       
        return person;
    }

    @Override
                        dUser login(String email, String password) 
                        er result;
                        
                         jdbcTemplate.queryForObject(
                        steredUser temp = new Registered
                        UUID.fromString(resultSet.getSt
                        resultSet.getString("firstName"),
                        resultSet.getString("lastNa
                        resultSet.getString("email"),    resultSet.getString("password"),
                    resultSet.getString("address"),
                    resultSet.getString("creditCardNumber"),
                    resultSet.getString("ccv"),
                    resultSet.getString("creditCardExpirationDate")
                );
         

            }, email, password);
     

 
