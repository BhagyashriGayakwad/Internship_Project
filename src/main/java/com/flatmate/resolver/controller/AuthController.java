package com.flatmate.resolver.controller;

import com.flatmate.resolver.dto.LoginRequest;
import com.flatmate.resolver.dto.RegisterRequest;
import com.flatmate.resolver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.loginUser(request);
    }
}
