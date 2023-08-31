package ru.javabegin.backend.todo.repository;

import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.model.Task;
@Repository
public interface TaskRepository extends GenericRepository<Task>{
}
