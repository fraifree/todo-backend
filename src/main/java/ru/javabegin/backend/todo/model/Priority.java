package ru.javabegin.backend.todo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@SequenceGenerator(name = "default_gen", sequenceName = "users_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Priority extends GenericModel {
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "color",nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_data_id", referencedColumnName = "id") // по каким полям связывать (foreign key)
    private UserData userData;

    @OneToMany(mappedBy = "priority")
    private List<Task> taskList;

}
