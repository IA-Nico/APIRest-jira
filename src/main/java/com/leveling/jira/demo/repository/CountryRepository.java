package com.leveling.jira.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leveling.jira.demo.entities.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

}
