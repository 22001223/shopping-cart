package org.example.controller;

import org.example.config.JwtUtil;
import org.example.model.AuthRequest;
import org.example.model.AuthResponse;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String token) {
        return jwtUtil.isValidToken(token);
    }
}