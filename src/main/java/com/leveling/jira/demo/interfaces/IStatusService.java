package com.leveling.jira.demo.interfaces;

import java.util.Optional;

import com.leveling.jira.demo.entities.Status;

public interface IStatusService {

    Status save(Status status);

    Optional<Status> findById(Long id);

    Iterable<Status> findAll();

    long count();
}
