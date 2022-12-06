package com.example.ensf480.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ensf480.Dao.RegisteredUserDao;
import com.example.ensf480.Model.RegisteredUser;

// Class for RegisteredUserSerfice
@Service
public class RegisteredUserService {
    private final RegisteredUserDao userDao;

		// Dependency Injection
    @Autowired
    public RegisteredUserService(@Qualifier("Postgres") RegisteredUserDao userDao) {
        this.userDao = userDao;
    }

		/**
		 * Method to register user
		 * @param person - RegisteredUser
		 * @return RegisteredUser
		 */
    public RegisteredUser registerUser(RegisteredUser person) {
        return userDao.insertPerson(person);
    }

		/**
		 * Method to log user in
		 * @param userMap - User data 
		 * @return RegisteredUser
		 */
    public RegisteredUser login(Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        return userDao.login(email, password);
    }

		/**
		 * Method to reactivate RegisteredUser
		 * @param userMap - User data
		 * @return RegisteredUser
		 */
    public RegisteredUser reactivate(Map<String, Object> userMap) {
        String id = (String) userMap.get("id");
        return userDao.reactivate(id);
    }

		/**
		 * Method to get user by email
		 * @param email - User email
		 * @return RegisteredUser
		 */
    public RegisteredUser getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
