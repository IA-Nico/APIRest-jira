package com.leveling.jira.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leveling.jira.demo.entities.Status;
import com.leveling.jira.demo.interfaces.IStatusService;
import com.leveling.jira.demo.repository.StatusRepository;

@Service
public class StatusService implements IStatusService {

    @Autowired
    StatusRepository statusRepository;

    @Transactional
    @Override
    public Status save(Status status) {

        if (status == null)
            throw new IllegalArgumentException("Status nulo");

        return statusRepository.save(status);
    }

    @Transactional
    @Override
    public Optional<Status> findById(Long id) {

        if (id == null)
            throw new IllegalArgumentException("ID nulo");

        return statusRepository.findById(id);
    }

    @Transactional
    @Override
    public Iterable<Status> findAll() {
        return statusRepository.findAll();
    }

    @Transactional
    @Override
    public long count() {
        return statusRepository.count();
    }

}
