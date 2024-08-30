package com.leveling.jira.demo.interfaces;

import java.util.Optional;

import com.leveling.jira.demo.DTO.UserDTO;
import com.leveling.jira.demo.entities.User;

public interface IUserService {

    User save(UserDTO user);

    User updateUser(Long id, UserDTO user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    void delete(User user);

    Optional<User> findByEmail(String email);

    void createPasswordResetToken(String email);

    User validatePasswordResetToken(String token);

    void resetPassword(String token, String newPassword);

}
