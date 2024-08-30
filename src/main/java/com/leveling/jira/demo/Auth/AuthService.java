package com.leveling.jira.demo.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.leveling.jira.demo.DTO.UserDTO;
import com.leveling.jira.demo.entities.User;
import com.leveling.jira.demo.interfaces.IUserService;
import com.leveling.jira.demo.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private IUserService userService;

    private final JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails usuario = userService.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(usuario);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(UserDTO request) {

        User usuario = userService.save(request);

        return AuthResponse.builder().token(jwtService.getToken(usuario)).build();

    }

}
