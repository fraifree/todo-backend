package ru.javabegin.backend.todo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_data", schema = "public", catalog = "habrdb")
public class UserData extends GenericModel{

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_data_id"),
            foreignKey = @ForeignKey(name = "FK_USERS_ROLES"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            inverseForeignKey = @ForeignKey(name = "FK_ROLES_USERS"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "userData")
    private List<Category> categoryList;

    @OneToMany(mappedBy = "userData")
    private List<Priority> priorityList;

    @OneToMany(mappedBy = "userData")
    private List<Task> taskList;

}
