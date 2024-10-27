package com.example.amazon_agency_task.conroller;

import com.example.amazon_agency_task.security.AuthRequest;
import com.example.amazon_agency_task.security.AuthResponse;
import com.example.amazon_agency_task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody AuthRequest user) {
        this.userService.register(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@RequestBody AuthRequest user) {
        return this.userService.login(user);
    }
}
