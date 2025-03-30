package org.example.service;

import org.example.model.AuthRequest;
import org.example.model.AuthResponse;
import org.example.model.User;
import org.example.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    private final List<User> users = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("juan-fer");
        user1.setPassword(passwordEncoder.encode("juan-fer-password"));
        user1.setRoles(List.of("ADMIN"));
        users.add(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("no-auth");
        user2.setPassword(passwordEncoder.encode("no-auth-password"));
        user2.setRoles(List.of("USER"));
        users.add(user2);
    }

    public AuthResponse login(AuthRequest authRequest) {
        User user = users.stream()
                .filter(u -> u.getUsername().equals(authRequest.getUsername()))
                .findFirst()
                .orElse(null);

        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user);
            return new AuthResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }

    public AuthResponse register(AuthRequest authRequest) {
        User user = new User();
        user.setId((long) (users.size() + 1));
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        users.add(user);
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }
}