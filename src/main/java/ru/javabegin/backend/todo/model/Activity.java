package ru.javabegin.backend.todo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Вся активность пользователя (активация аккаунта, другие действия по необходимости)
*/
@Entity
@Table(name = "activity", schema = "public", catalog = "habrdb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Activity extends GenericModel { // название таблицы будет браться автоматически по названию класса с маленькой буквы: activity

    @Column(name = "activated")
    private Boolean activated; // становится true только после подтверждения активации пользователем (обратно false уже стать не может по логике)

    @Column(updatable = false)
    private String uuid; // создается только один раз с помощью триггера в БД

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserData userData;

}
