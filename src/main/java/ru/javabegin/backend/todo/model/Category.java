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
справочное значение - категория пользователя
может использовать для своих задач
содержит статистику по каждой категории
 */

@Entity
@Table(name = "category", schema = "public", catalog = "habrdb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SequenceGenerator(name = "default_gen", sequenceName = "users_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Category extends GenericModel {

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "completed_count", updatable = false) // т.к. это поле высчитывается автоматически в триггерах - в ручную его не обновляем (updatable = false)
    private Long completedCount;

    @Column(name = "uncompleted_count", updatable = false) // т.к. это поле высчитывается автоматически в триггерах - в ручную его не обновляем (updatable = false)
    private Long uncompletedCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_data_id", nullable = false,
    foreignKey = @ForeignKey(name = "FK_CATEGORY_USER_DATE")) // по каким полям связаны эти 2 объекта (foreign key)
    private UserData userData;

    @OneToMany(mappedBy = "category")
    private List<Task> taskList;


}
