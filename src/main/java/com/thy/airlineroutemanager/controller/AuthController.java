package com.thy.airlineroutemanager.controller;

import com.thy.airlineroutemanager.entity.User;
import com.thy.airlineroutemanager.repository.UserRepository;
import com.thy.airlineroutemanager.request.LoginRequest;
import com.thy.airlineroutemanager.request.RegisterRequest;
import com.thy.airlineroutemanager.response.LoginResponse;
import com.thy.airlineroutemanager.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        //TODO buradaki işlemleri service' e taşı.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        return new LoginResponse(tokenProvider.generateToken(user), user.getRole());
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        //TODO buradaki işlemleri service' e taşı.
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
    }
}
