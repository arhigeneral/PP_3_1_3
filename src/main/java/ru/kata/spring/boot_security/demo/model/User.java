package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String lastName;
    private Integer age;
    private String name;

    @ManyToMany
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public String listRoles() {
        return roles.stream().map((role) -> role.getRole()).collect(Collectors.joining(" "));
    }

}
