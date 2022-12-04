package com.example.ensf480.Dao;

import com.example.ensf480.Model.RegisteredUser;

public interface RegisteredUserDao {
    RegisteredUser insertPerson(RegisteredUser person);

    RegisteredUser login(String email, String password);

    RegisteredUser reactivate(String id);

}
