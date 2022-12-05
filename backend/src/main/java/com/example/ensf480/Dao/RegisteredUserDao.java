package com.example.ensf480.Dao;

import com.example.ensf480.Model.RegisteredUser;

// Interface for RegisteredUser functions
public interface RegisteredUserDao {
		/**
		 * Function to insert user into database
		 * @param person - Registered user object
		 * @return RegisteredUser
		 */
    RegisteredUser insertPerson(RegisteredUser person);

		/**
		 * Function to log user in
		 * @param email - Registered user's email
		 * @param password - Registered user's password
		 * @return RegisteredUser
		 */
    RegisteredUser login(String email, String password);

		/**
		 * Function to reactivate RegisteredUser's account
		 * @param id - Unique id of registered user
		 * @return RegisteredUser
		 */
    RegisteredUser reactivate(String id);

		/**
		 * Function to get user based on their email
		 * @param email - RegisteredUser's email
		 * @return Registered User
		 */
    RegisteredUser getUserByEmail(String email);

}
