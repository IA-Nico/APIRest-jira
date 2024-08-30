package com.leveling.jira.demo.Service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.leveling.jira.demo.entities.Proyect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FiltradoService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Proyect> filtrarProyectosPorFecha(
            String name,
            Long statusId,
            String category,
            String iconPath,
            LocalDate startDate,
            LocalDate endDate) {

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM proyects p WHERE 1=1");

        if (name != null) {
            queryBuilder.append(" AND p.name LIKE CONCAT(:name, '%')");
        }
        if (statusId != null) {
            queryBuilder.append(" AND p.status_id = :statusId");
        }
        if (category != null) {
            queryBuilder.append(" AND p.category LIKE CONCAT(:category, '%')");
        }
        if (iconPath != null) {
            queryBuilder.append(" AND p.icon_path LIKE CONCAT(:iconPath, '%')");
        }
        if (startDate != null) {
            queryBuilder.append(" AND p.start_date = :startDate");
        }
        if (endDate != null) {
            queryBuilder.append(" AND p.end_date = :endDate");
        }

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Proyect.class);

        if (name != null) {
            query.setParameter("name", name);
        }
        if (statusId != null) {
            query.setParameter("statusId", statusId);
        }
        if (category != null) {
            query.setParameter("category", category);
        }
        if (iconPath != null) {
            query.setParameter("iconPath", iconPath);
        }
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }

        List<Proyect> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            throw new NoSuchElementException("No se encontraron proyectos con esas caracteristicas.");
        }

        return resultList;

    }

}
