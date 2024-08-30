package com.leveling.jira.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "proyects")
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status_name;

    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private Set<Proyect> proyects;

    public Status(String status_name) {
        this.status_name = status_name;
        this.proyects = new HashSet<>();
    }

    public void addProyect(Proyect proyect) {
        if (proyect != null) {
            this.proyects.add(proyect);
            proyect.setStatus(this);
        }
    }

}
