package ru.javabegin.backend.todo.controller.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.Priority;
import ru.javabegin.backend.todo.service.PriorityService;

@RestController
@RequestMapping("/priority")
public class PriorityRestController extends GenericRestController<Priority>{

    private final PriorityService priorityService;

    public PriorityRestController(PriorityService priorityService){
        super(priorityService);
        this.priorityService = priorityService;
    }
}
