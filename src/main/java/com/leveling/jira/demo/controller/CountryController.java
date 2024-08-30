package com.leveling.jira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leveling.jira.demo.entities.Country;
import com.leveling.jira.demo.interfaces.ICountryService;

@RestController
@RequestMapping("api/country")
public class CountryController {

    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ResponseEntity<Object> findCountries() {

        return ResponseEntity.ok().body(countryService.findAll());

    }

    @PostMapping()
    public ResponseEntity<Object> saveCountry(@RequestBody Country country) {

        return ResponseEntity.ok().body(countryService.save(country));

    }

}
