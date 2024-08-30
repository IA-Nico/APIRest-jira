package com.leveling.jira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leveling.jira.demo.DTO.ProyectDTO;
import com.leveling.jira.demo.Service.FiltradoService;
import com.leveling.jira.demo.entities.Proyect;
import com.leveling.jira.demo.interfaces.IProyectService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/proyect")
public class ProyectRestController {

    @Autowired
    IProyectService proyectService;

    @Autowired
    FiltradoService filtradoService;

    @GetMapping("/filtro")
    public List<Proyect> getProyectosPorFecha(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "statusId", required = false) Long statusId,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "iconPath", required = false) String iconPath,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return filtradoService.filtrarProyectosPorFecha(name, statusId, category, iconPath, startDate, endDate);
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {

        return ResponseEntity.ok().body(proyectService.findAll());

    }

    @PostMapping
    public ResponseEntity<Object> saveProyect(@RequestBody ProyectDTO proyectDTO) {

        return ResponseEntity.ok().body(proyectService.save(proyectDTO));

    }

    @PutMapping("/updateProyect/{Id}")
    public ResponseEntity<Object> update(@PathVariable Long Id, @RequestBody ProyectDTO proyectDTO) {

        return ResponseEntity.ok().body(proyectService.updateProyect(Id, proyectDTO));

    }

}
