package com.leveling.jira.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leveling.jira.demo.entities.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {

}
