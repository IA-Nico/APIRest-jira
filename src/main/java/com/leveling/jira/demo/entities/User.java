package com.leveling.jira.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String lastname;

    @Column(name = "company_name")
    private String companyName;

    private String position;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "reset_password_Expired")

    private LocalDateTime restPasswordExpired;

    @ManyToOne
    @JoinColumn(name = "country_id")
    // @JsonIgnore
    private Country country;

    @OneToMany(mappedBy = "user")
    private Set<Proyect> proyects;

    public User(String email, String password, String name, String lastname, String companyName, String position,
            String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.companyName = companyName;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.proyects = new HashSet<>();
    }

    public void addProyect(Proyect proyect) {
        if (proyect != null) {
            this.proyects.add(proyect);
            proyect.setUser(this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of();
    }

    @Override
    public String getUsername() {

        return this.email;
    }

}
