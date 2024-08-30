package com.leveling.jira.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import com.leveling.jira.demo.entities.Proyect;

@Repository
public interface ProyectRepository extends JpaRepository<Proyect, Long> {

        @Query(value = "SELECT * FROM proyects p WHERE " +
                        "(:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) AND " +
                        "(:statusId IS NULL OR p.status_id = :statusId) AND " +
                        "(:category IS NULL OR p.category LIKE CONCAT('%', :category, '%')) AND " +
                        "(:iconPath IS NULL OR p.icon_path LIKE CONCAT('%', :iconPath, '%')) AND " +
                        "(:startDate IS NULL OR p.start_date >= CAST(:startDate AS date)) AND " +
                        "(:endDate IS NULL OR p.end_date <= CAST(:endDate AS date))", nativeQuery = true)
        Optional<List<Proyect>> filtrarProyectos(
                        @Param("name") String name,
                        @Param("statusId") Long statusId,
                        @Param("category") String category,
                        @Param("iconPath") String iconPath,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        @Query("SELECT p FROM Proyect p WHERE " +
                        "(:startDate IS NULL OR p.startDate >= :startDate) AND " +
                        "(:endDate IS NULL OR p.endDate <= :endDate)")
        List<Proyect> filtrarProyectosPorFecha(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

}
