package com.leveling.jira.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;

@Entity
@Table(name = "countries")
@Getter
@Setter
@EqualsAndHashCode(exclude = "users")
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private Set<User> users;

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
        this.users = new HashSet<>();
    }

    public void addUser(User user) {
        if (user != null) {
            this.users.add(user);
            user.setCountry(this);
        }
    }

}
