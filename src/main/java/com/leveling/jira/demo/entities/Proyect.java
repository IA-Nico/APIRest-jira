package com.leveling.jira.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "proyects")
@Getter
@Setter
@NoArgsConstructor
public class Proyect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;
    @Column(name = "icon_path")
    private String iconPath;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    private boolean shared;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "status_id")
    // @JsonIgnore
    private Status status;

    public Proyect(String name, String description, String category, String iconPath, LocalDate startDate,
            LocalDate endDate, boolean shared) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.iconPath = iconPath;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shared = shared;
    }

}
