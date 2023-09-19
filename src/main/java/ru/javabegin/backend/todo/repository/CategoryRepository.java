package ru.javabegin.backend.todo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends GenericRepository<Category> {

//    List<Category> findByUserDataEmailOrderByTitleAsc(String email);
    Category findAllByTitle(String title);


    //JPQL
    // поиск значений по названию для конкретного пользователя
    @Query("SELECT c FROM Category c where " +
            "(:title is null or :title='' " + // если передадим параметр title пустым, то выберутся все записи (сработает именно это условие)
            " or lower(c.title) like lower(concat('%', :title,'%'))) " + // если параметр title не пустой, то выполнится уже это условие
            " and c.userData.email=:email  " + // фильтрация для конкретного пользователя
            " order by c.title asc") // сортировка по названию
    List<Category> findByTitle(@Param("title") String title, @Param("email") String email);


}
