package ru.javabegin.backend.todo.repository;

import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.model.Priority;

@Repository
public interface PriorityRepository extends GenericRepository<Priority> {
}
