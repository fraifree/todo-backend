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
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")
public class Category extends GenericModel {

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "completed_count", updatable = false) // т.к. это поле высчитывается автоматически в триггерах - в ручную его не обновляем (updatable = false)
    private Long completedCount;

    @Column(name = "uncompleted_count", updatable = false) // т.к. это поле высчитывается автоматически в триггерах - в ручную его не обновляем (updatable = false)
    private Long uncompletedCount;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonProperty(required = true,access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "user_data_id",
    foreignKey = @ForeignKey(name = "FK_CATEGORY_USER_DATE")) // по каким полям связаны эти 2 объекта (foreign key)
    private UserData userData;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Task> taskList;

}
