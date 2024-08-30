package com.leveling.jira.demo.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leveling.jira.demo.DTO.UserDTO;
import com.leveling.jira.demo.entities.Country;
import com.leveling.jira.demo.entities.User;
import com.leveling.jira.demo.interfaces.ICountryService;
import com.leveling.jira.demo.interfaces.IUserService;
import com.leveling.jira.demo.repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ICountryService countryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Transactional
    @Override
    public User save(UserDTO user) {
        if (user == null)
            throw new IllegalArgumentException("El usuario es nulo");

        Country pais = countryService.findById(user.getCountry_id())
                .orElseThrow(() -> new IllegalArgumentException("País no encontrado"));

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        User nuevoUsuario = new User(
                user.getEmail(),
                hashedPassword,
                user.getName(),
                user.getLastname(),
                user.getCompanyName(),
                user.getPosition(),
                user.getPhoneNumber());

        pais.addUser(nuevoUsuario);

        return userRepository.save(nuevoUsuario);
    }

    @Override
    @Transactional
    public User updateUser(Long id, UserDTO userDTO) {

        if (id == null) {
            throw new IllegalArgumentException("El ID del usuario es nulo");
        }

        User usuarioBD = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuarioBD.setEmail(userDTO.getEmail());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
            usuarioBD.setPassword(hashedPassword);
        }

        usuarioBD.setName(userDTO.getName());
        usuarioBD.setLastname(userDTO.getLastname());
        usuarioBD.setCompanyName(userDTO.getCompanyName());
        usuarioBD.setPosition(userDTO.getPosition());
        usuarioBD.setPhoneNumber(userDTO.getPhoneNumber());

        if (userDTO.getCountry_id() != null) {
            Country pais = countryService.findById(userDTO.getCountry_id())
                    .orElseThrow(() -> new IllegalArgumentException("País no encontrado"));
            usuarioBD.setCountry(pais);
        }

        return userRepository.save(usuarioBD);
    }

    @Transactional
    @Override
    public Optional<User> findById(Long id) {

        if (id == null)
            throw new IllegalArgumentException("El ID es nulo");

        return userRepository.findById(id);

    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        if (id == null)
            throw new IllegalArgumentException("El ID es nulo");

        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void delete(User user) {

        if (user == null)
            throw new IllegalArgumentException("El usuario es nulo");

        userRepository.delete(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {

        if (email == null)
            throw new IllegalArgumentException("El email es nulo");

        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void createPasswordResetToken(String email) {

        System.out.println("NO PASA");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado pipipi"));

        System.out.println("PASA");

        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1); // Token expira en 1 hora

        user.setResetPasswordToken(token);
        user.setRestPasswordExpired(expiryDate);

        emailService.sendPasswordResetEmail(user, token);

        userRepository.save(user);
    }

    @Override
    public User validatePasswordResetToken(String token) {

        User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido"));

        if (user.getRestPasswordExpired().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("El token ha expirado");
        }

        return user;
    }

    @Override
    public void resetPassword(String token, String newPassword) {

        User user = validatePasswordResetToken(token);

        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);

        user.setResetPasswordToken(null);
        user.setRestPasswordExpired(null);

        userRepository.save(user);
    }

}
