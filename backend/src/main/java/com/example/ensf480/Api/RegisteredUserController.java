package com.example.ensf480.Api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ensf480.Model.RegisteredUser;
import com.example.ensf480.Service.RegisteredUserService;

@RequestMapping("api/v1/user")
@RestController
public class RegisteredUserController {
    private final RegisteredUserService userService;

    @Autowired
    public RegisteredUserController(RegisteredUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "create")
    public RegisteredUser registerUser(@RequestBody RegisteredUser user) {
        return userService.registerUser(user);
    }

    @PostMapping(path = "login")
    public double checkCredentials(@RequestBody Map<String, Object> userMap) {
        return userService.checkCredentials(userMap);
    }

}
