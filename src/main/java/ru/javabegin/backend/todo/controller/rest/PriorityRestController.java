package ru.javabegin.backend.todo.controller.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.Priority;
import ru.javabegin.backend.todo.model.Task;
import ru.javabegin.backend.todo.model.UserData;
import ru.javabegin.backend.todo.service.PriorityService;
import ru.javabegin.backend.todo.service.TaskService;
import ru.javabegin.backend.todo.service.UserDataService;

@RestController
@RequestMapping("/priority")
public class PriorityRestController extends GenericRestController<Priority>{

    private final PriorityService priorityService;
    private final UserDataService userDataService;
    private final TaskService taskService;

    public PriorityRestController(PriorityService priorityService, UserDataService userDataService, TaskService taskService){
        super(priorityService);
        this.priorityService = priorityService;
        this.userDataService = userDataService;
        this.taskService = taskService;
    }

    @RequestMapping("/addUser")
    public ResponseEntity<Priority> addUser(@RequestParam(value = "userDataId")Long userDataId,
                                            @RequestParam(value = "priorityId")Long priorityId){
        UserData userData = userDataService.findById(userDataId);
        Priority priority = priorityService.findById(priorityId);
        priority.setUserData(userData);
        return ResponseEntity.status(HttpStatus.OK).body(priorityService.update(priority));
    }

    @RequestMapping("/addTask")
    public ResponseEntity<Priority> addTask(@RequestParam(value = "taskId")Long taskId,
                                            @RequestParam(value = "priorityId")Long priorityId){
        Priority priority = priorityService.findById(priorityId);
        Task task = taskService.findById(taskId);
        task.setPriority(priority);
        return ResponseEntity.status(HttpStatus.OK).body(priorityService.update(priority));
    }
}
