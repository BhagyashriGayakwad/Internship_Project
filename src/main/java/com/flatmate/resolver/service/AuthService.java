package com.flatmate.resolver.service;

import com.flatmate.resolver.model.*;
import com.flatmate.resolver.repository.*;
import com.flatmate.resolver.security.JwtUtils;
import com.flatmate.resolver.dto.LoginRequest;
import com.flatmate.resolver.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public String registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already taken!");
        }

        Flat flat = flatRepository.findByFlatCode(request.getFlatCode())
                .orElseGet(() -> {
                    Flat newFlat = new Flat();
                    newFlat.setFlatCode(request.getFlatCode());
                    return flatRepository.save(newFlat);
                });

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFlat(flat);
        user.setRoles(Collections.singleton(roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role not found"))));

        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return jwtUtils.generateJwtToken(authentication);
    }
}
