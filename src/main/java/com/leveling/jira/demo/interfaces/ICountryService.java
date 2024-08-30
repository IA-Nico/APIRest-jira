package com.leveling.jira.demo.interfaces;

import com.leveling.jira.demo.entities.Country;
import java.util.Optional;

public interface ICountryService {

    Country save(Country country);

    Optional<Country> findById(Long id);

    Iterable<Country> findAll();

    long count();

}
