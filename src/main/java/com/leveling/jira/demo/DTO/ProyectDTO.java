package com.leveling.jira.demo.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ProyectDTO {

    private Long id;

    private String name;
    private String description;
    private String category;
    private String iconPath;

    private LocalDate startDate;
    private LocalDate endDate;

    private boolean shared;
    private Long user_id;
    private Long status_id;

    public ProyectDTO(String name, String description, String category, String iconPath, LocalDate startDate,
            LocalDate endDate, boolean shared, Long user_id, Long status_id) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.iconPath = iconPath;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shared = shared;
        this.user_id = user_id;
        this.status_id = status_id;
    }

    public boolean getShared() {
        return this.shared;
    }

}
