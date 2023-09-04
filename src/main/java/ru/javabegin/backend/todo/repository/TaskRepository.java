package ru.javabegin.backend.todo.repository;

import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends GenericRepository<Task>{

    List<Task> findByUserDataNameOrderByTitleAsc(String userName);
}
