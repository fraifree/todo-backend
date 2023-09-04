package ru.javabegin.backend.todo.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*

общая статистика по задачам (незвисимо от категорий задач)

 */

@Entity
@Table(name = "stat", schema = "public", catalog = "habrdb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")

public class Stat extends GenericModel{ // в этой таблице всего 1 запись, которая обновляется (но никогда не удаляется)


    @Column(name = "completed_total", updatable = false)
    private Long completedTotal; // значение задается в триггере в БД

    @Column(name = "uncompleted_total", updatable = false)
    private Long uncompletedTotal; // значение задается в триггере в БД

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_data_id", referencedColumnName = "id") // по каким полям связывать (foreign key)
    private UserData userData;



}
