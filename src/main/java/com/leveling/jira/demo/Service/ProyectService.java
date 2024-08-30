package com.leveling.jira.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leveling.jira.demo.DTO.ProyectDTO;
import com.leveling.jira.demo.entities.Proyect;
import com.leveling.jira.demo.entities.Status;
import com.leveling.jira.demo.entities.User;
import com.leveling.jira.demo.interfaces.IProyectService;
import com.leveling.jira.demo.interfaces.IStatusService;
import com.leveling.jira.demo.interfaces.IUserService;
import com.leveling.jira.demo.repository.ProyectRepository;

@Service
public class ProyectService implements IProyectService {

    @Autowired
    ProyectRepository proyectRepository;

    @Autowired
    IUserService userService;

    @Autowired
    IStatusService statusService;

    /*
     * public List<Proyect> filtrarProyectosPorFecha(LocalDate startDate, LocalDate
     * endDate) {
     * // Llama al método del repositorio y maneja el resultado
     * return proyectRepository.filtrarProyectosPorFecha(startDate, endDate);
     * 
     * // Si no se encuentran resultados, lanzar una excepción o manejar el caso
     * según
     * // sea necesario
     * 
     * }
     * 
     * @Transactional
     * public List<Proyect> filtrarProyectos(String name, Long statusId, String
     * category, String iconPath,
     * LocalDate startDate, LocalDate endDate) {
     * // Aquí se llama al repositorio con los parámetros de filtrado
     * return proyectRepository.filtrarProyectos(name, statusId, category, iconPath,
     * startDate, endDate)
     * .orElseThrow();
     * }
     */

    @Transactional
    @Override
    public Proyect save(ProyectDTO proyect) {
        if (proyect == null)
            throw new IllegalArgumentException("El proyecto es nulo");

        User usuario = userService.findById(proyect.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Status estado = statusService.findById(proyect.getStatus_id())
                .orElseThrow(() -> new IllegalArgumentException("Status no encontrado"));

        Proyect nuevoProyecto = new Proyect(
                proyect.getName(),
                proyect.getDescription(),
                proyect.getCategory(),
                proyect.getIconPath(),
                proyect.getStartDate(),
                proyect.getEndDate(),
                proyect.getShared());

        // cambiar
        usuario.addProyect(nuevoProyecto);
        estado.addProyect(nuevoProyecto);

        return proyectRepository.save(nuevoProyecto);

    }

    @Transactional
    public Proyect updateProyect(Long id, ProyectDTO proyectDTO) {

        if (id == null) {
            throw new IllegalArgumentException("El ID del proyecto es nulo");
        }

        // verifificar linea de seucencia
        Proyect proyectBD = proyectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));

        proyectBD.setName(proyectDTO.getName());
        proyectBD.setDescription(proyectDTO.getDescription());
        proyectBD.setCategory(proyectDTO.getCategory());
        proyectBD.setIconPath(proyectDTO.getIconPath());
        proyectBD.setStartDate(proyectDTO.getStartDate());
        proyectBD.setEndDate(proyectDTO.getEndDate());
        proyectBD.setShared(proyectDTO.getShared());

        if (proyectDTO.getUser_id() != null) {
            User usuario = userService.findById(proyectDTO.getUser_id())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            proyectBD.setUser(usuario);
        }

        if (proyectDTO.getStatus_id() != null) {
            Status estado = statusService.findById(proyectDTO.getStatus_id())
                    .orElseThrow(() -> new IllegalArgumentException("Status no encontrado"));
            proyectBD.setStatus(estado);
        }

        // AGREGAR QUITAR AL USUARIO DE LA LISTA DE USUARIOS DEL PAIS DE SER NECESARIO
        // -> VERIFICAR

        return proyectRepository.save(proyectBD);
    }

    @Transactional
    @Override
    public Optional<Proyect> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID nulo");
        return proyectRepository.findById(id);
    }

    @Transactional
    @Override
    public Iterable<Proyect> findAll() {
        return proyectRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        proyectRepository.deleteById(id);
    }

    @Override
    public void delete(Proyect proyect) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

}
