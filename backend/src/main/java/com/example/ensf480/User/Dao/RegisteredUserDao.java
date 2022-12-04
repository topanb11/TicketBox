package main.java.com.example.ensf480.User.Dao;

import com.example.ensf480.User.Model.RegisteredUser;

public interface RegisteredUserDao {
    RegisteredUser insertPerson(RegisteredUser person);

    double checkCredentials(String email, String password);

}
