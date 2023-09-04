package ru.javabegin.backend.todo.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "user_data"
        ,
        uniqueConstraints =
        {@UniqueConstraint(name = "uniqueEmail", columnNames = "email"),
                @UniqueConstraint(name = "uniqueLogin", columnNames = "name")}
)

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")
public class UserData extends GenericModel{

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_data_id"),
            foreignKey = @ForeignKey(name = "FK_USERS_ROLES"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            inverseForeignKey = @ForeignKey(name = "FK_ROLES_USERS"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "userData", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Category> categoryList;

    @OneToMany(mappedBy = "userData", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Priority> priorityList;

    @OneToMany(mappedBy = "userData", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Task> taskList;

}
