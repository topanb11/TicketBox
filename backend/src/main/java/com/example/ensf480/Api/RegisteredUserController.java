package com.example.ensf480.Api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ensf480.Model.RegisteredUser;
import com.example.ensf480.Service.RegisteredUserService;

// API routes for Registered user entity
@RequestMapping("api/v1/user")
@RestController
@CrossOrigin
public class RegisteredUserController {
		// Instance of UserService to access methods
    private final RegisteredUserService userService;

		// Dependency injection
    @Autowired
    public RegisteredUserController(RegisteredUserService userService) {
        this.userService = userService;
    }

		// API route to instantiate a new RegisteredUser entity
    @PostMapping(path = "create")
    public RegisteredUser registerUser(@RequestBody RegisteredUser user) {
        return userService.registerUser(user);
    }

		// API route to validate RegisteredUser info
    @PostMapping(path = "login")
    public RegisteredUser checkCredentials(@RequestBody Map<String, Object> userMap) {
        return userService.login(userMap);
    }

		// API route to instantiate a renew a RegisteredUsers account
    @PostMapping(path = "reactivate")
    public RegisteredUser reactivate(@RequestBody Map<String, Object> userMap) {
        return userService.reactivate(userMap);
    }

}
