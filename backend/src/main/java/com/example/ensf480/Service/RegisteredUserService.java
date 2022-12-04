package com.example.ensf480.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ensf480.Dao.RegisteredUserDao;
import com.example.ensf480.Model.RegisteredUser;

@Service
public class RegisteredUserService {
    private final RegisteredUserDao userDao;

    @Autowired
    public RegisteredUserService(@Qualifier("Postgres") RegisteredUserDao userDao) {
        this.userDao = userDao;
    }

    public RegisteredUser registerUser(RegisteredUser person) {
        return userDao.insertPerson(person);
    }

    public RegisteredUser login(Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        return userDao.login(email, password);
    }
}
