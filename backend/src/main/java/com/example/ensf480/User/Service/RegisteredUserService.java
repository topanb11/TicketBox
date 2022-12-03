package com.example.ensf480.User.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ensf480.User.Dao.RegisteredUserDao;
import com.example.ensf480.User.Model.RegisteredUser;

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

    public double checkCredentials(Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        return userDao.checkCredentials(email, password);
    }
}
