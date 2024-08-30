package com.leveling.jira.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leveling.jira.demo.entities.Country;
import com.leveling.jira.demo.interfaces.ICountryService;
import com.leveling.jira.demo.repository.CountryRepository;

@Service
public class CountryService implements ICountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    @Transactional
    public Country save(Country country) {

        if (country == null)
            throw new IllegalArgumentException("El pais es nulo");

        return countryRepository.save(country);

    }

    @Transactional
    @Override
    public Optional<Country> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID nulo");

        return countryRepository.findById(id);
    }

    @Transactional
    @Override
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }

    @Transactional
    @Override
    public long count() {
        return countryRepository.count();
    }

}
