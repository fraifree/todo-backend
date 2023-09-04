package ru.javabegin.backend.todo.repository;

import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.model.Category;
import ru.javabegin.backend.todo.model.Task;

import java.util.List;

@Repository
public interface CategoryRepository extends GenericRepository<Category> {

    List<Category> findByUserDataEmailOrderByTitleAsc(String email);



}
