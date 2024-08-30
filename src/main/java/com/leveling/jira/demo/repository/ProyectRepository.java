package com.leveling.jira.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.leveling.jira.demo.entities.Proyect;

@Repository
public interface ProyectRepository extends JpaRepository<Proyect, Long> {

}
