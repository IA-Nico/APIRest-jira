package com.leveling.jira.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String companyName;
    private String position;
    private String phoneNumber;
    private Long country_id;

    public UserDTO(String email, String password, String name, String lastname, String companyName, String position,
            String phoneNumber, Long country_id) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.companyName = companyName;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.country_id = country_id;
    }

}
