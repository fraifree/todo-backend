package ru.javabegin.backend.todo.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.Task;
import ru.javabegin.backend.todo.service.TaskService;
@RestController
@RequestMapping("/task")
public class TaskRestController extends GenericRestController<Task>{
    private final TaskService taskService;

    public TaskRestController(TaskService taskService){
        super(taskService);
        this.taskService = taskService;
    }
}
