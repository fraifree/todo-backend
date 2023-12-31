package ru.javabegin.backend.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.Task;
import ru.javabegin.backend.todo.repository.TaskRepository;

@Service
@Transactional
public class TaskService extends GenericService<Task>{
    private final TaskRepository taskRepository;
    protected TaskService(TaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }
}
