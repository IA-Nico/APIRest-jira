package com.leveling.jira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leveling.jira.demo.entities.Status;
import com.leveling.jira.demo.interfaces.IStatusService;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private IStatusService statusService;

    @PostMapping()
    public ResponseEntity<Object> saveStatus(@RequestBody Status status) {

        return ResponseEntity.ok().body(statusService.save(status));

    }

}
