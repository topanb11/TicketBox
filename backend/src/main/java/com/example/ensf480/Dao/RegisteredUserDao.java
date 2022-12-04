package com.example.ensf480.Dao;

import com.example.ensf480.Model.RegisteredUser;

public interface RegisteredUserDao {
    RegisteredUser insertPerson(RegisteredUser person);

    double checkCredentials(String email, String password);

}
