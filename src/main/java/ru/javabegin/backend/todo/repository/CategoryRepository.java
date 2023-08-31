package ru.javabegin.backend.todo.repository;

import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.model.Category;

@Repository
public interface CategoryRepository extends GenericRepository<Category> {
}
