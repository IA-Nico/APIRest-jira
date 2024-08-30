package com.leveling.jira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leveling.jira.demo.DTO.UserDTO;
import com.leveling.jira.demo.entities.User;
import com.leveling.jira.demo.interfaces.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @PutMapping("/updateUser/{Id}")
    public ResponseEntity<User> updateUser(@PathVariable Long Id, @RequestBody UserDTO userDTO) {

        return ResponseEntity.ok().body(userService.updateUser(Id, userDTO));

    }

}
