package com.leveling.jira.demo.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leveling.jira.demo.DTO.UserDTO;
import com.leveling.jira.demo.interfaces.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Autowired
    private IUserService userService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDTO request) {

        return ResponseEntity.ok().body(authService.register(request));

    }

    @PostMapping(value = "forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam(required = true) String email) {

        userService.createPasswordResetToken(email);

        return ResponseEntity.ok("Correo de restablecimiento enviado");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam(required = true) String token,
            @RequestBody ResetRequest resetRequest) {

        userService.resetPassword(token, resetRequest.getNewPassword());
        return ResponseEntity.ok("Contraseña actualizada");
    }

}
