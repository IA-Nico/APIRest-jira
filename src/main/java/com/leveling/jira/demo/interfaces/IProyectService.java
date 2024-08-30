package com.leveling.jira.demo.interfaces;

import java.util.Optional;

import com.leveling.jira.demo.DTO.ProyectDTO;
import com.leveling.jira.demo.entities.Proyect;

public interface IProyectService {

    Proyect save(ProyectDTO proyect);

    Proyect updateProyect(Long id, ProyectDTO proyectDTO);

    Optional<Proyect> findById(Long id);

    Iterable<Proyect> findAll();

    void deleteById(Long id);

    void delete(Proyect proyect);

    long count();

}
