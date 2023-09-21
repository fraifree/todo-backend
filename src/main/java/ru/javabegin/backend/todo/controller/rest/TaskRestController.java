package ru.javabegin.backend.todo.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.Category;
import ru.javabegin.backend.todo.model.Priority;
import ru.javabegin.backend.todo.model.Task;
import ru.javabegin.backend.todo.model.UserData;
import ru.javabegin.backend.todo.service.CategoryService;
import ru.javabegin.backend.todo.service.PriorityService;
import ru.javabegin.backend.todo.service.TaskService;
import ru.javabegin.backend.todo.service.UserDataService;

@RestController
@RequestMapping("/task")
public class TaskRestController extends GenericRestController<Task>{
    private final TaskService taskService;
    private final PriorityService priorityService;
    private final CategoryService categoryService;

    private final UserDataService userDataService;

    public TaskRestController(TaskService taskService, PriorityService priorityService, CategoryService categoryService, UserDataService userDataService){
        super(taskService);
        this.taskService = taskService;
        this.priorityService = priorityService;
        this.categoryService = categoryService;
        this.userDataService = userDataService;
    }
    @RequestMapping("/addPriority")
    public ResponseEntity<Task> addPriority(@RequestParam(value = "priorityId")Long priorityId,
                                            @RequestParam(value = "taskId")Long taskId){
        Priority priority = priorityService.findById(priorityId);
        Task task = taskService.findById(taskId);
        task.setPriority(priority);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(task));
    }
    @RequestMapping("/addCategory")
    public ResponseEntity<Task> addCategory(@RequestParam(value = "categoryId")Long categoryId,
                                            @RequestParam(value = "taskId")Long taskId){
        Category category = categoryService.findById(categoryId);
        Task task = taskService.findById(taskId);
        task.setCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(task));
    }
    @RequestMapping("/addUser")
    public ResponseEntity<Task> addUser(@RequestParam(value = "userDataId")Long userDataId,
                                            @RequestParam(value = "taskId")Long taskId){
        UserData userData = userDataService.findById(userDataId);
        Task task = taskService.findById(taskId);
        task.setUserData(userData);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(task));
    }
}
