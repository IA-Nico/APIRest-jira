package com.leveling.jira.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.leveling.jira.demo.entities.User;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(User user, String token) {

        // puerto correspondiente -> probar con valor obtenido del properties
        String url = "http://localhost:8080/auth/reset-password?token=" + token;
        String message = "Para restablecer tu contraseña, haz clic en el siguiente enlace: " + url;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Restablecimiento de contraseña");
        email.setText(message);
        mailSender.send(email);
    }

}
