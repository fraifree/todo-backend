package ru.javabegin.backend.todo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*

справочное значение - приоритет пользователя
может использовать для своих задач

 */


@Entity
@Table(name = "priority", schema = "public", catalog = "habrdb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")
public class Priority extends GenericModel {
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "color",nullable = false)
    private String color;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "user_data_id", foreignKey = @ForeignKey(name = "FK_PRIORITY_USER_DATA")) // по каким полям связывать (foreign key)
    private UserData userData;

    @OneToMany(mappedBy = "priority", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Task> taskList;

}
