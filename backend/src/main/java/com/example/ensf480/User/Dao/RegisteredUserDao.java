package com.example.ensf480.User.Dao;

import com.example.ensf480.User.Model.RegisteredUser;

public interface RegisteredUserDao {
    RegisteredUser insertPerson(RegisteredUser person);

    RegisteredUser login(String email, String password);

}
