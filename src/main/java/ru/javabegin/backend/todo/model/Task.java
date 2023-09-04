package ru.javabegin.backend.todo.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/*

задачи пользователя

 */

@Entity
@Table(name = "task", schema = "public", catalog = "habrdb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")

public class Task extends GenericModel{

    @Column(name = "title")
    private String title;

    @Column(name = "completed")
    private Boolean completed; // 1 = true, 0 = false

    @Column(name = "task_date") // в БД поле называется task_date, т.к. нельзя использовать системное имя date
    private Date taskDate;

    // задача может иметь только один приоритет (с обратной стороны - один и тот же приоритет может быть использоваться в множестве задач)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "priority_id", foreignKey = @ForeignKey(name = "FK_TASK_PRIORITY")) // по каким полям связывать (foreign key)
    private Priority priority;

    // задача может иметь только одну категорию (с обратной стороны - одна и та же категория может быть использоваться в множестве задач)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_TASK_CATEGORY")) // по каким полям связывать (foreign key)
    private Category category;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "user_data_id", foreignKey = @ForeignKey(name = "FK_TASK_USER_DATE")) // по каким полям связывать (foreign key)
    private UserData userData; // для какого пользователя задача


}
