package ru.javabegin.backend.todo.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.todo.model.*;
import ru.javabegin.backend.todo.service.*;

@RestController
@RequestMapping("/user")
public class UserDataRestController extends GenericRestController<UserData>{

    private final UserDataService userDataService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final PriorityService priorityService;
    private final TaskService taskService;

    public UserDataRestController(UserDataService userDataService,
                                  RoleService roleService,
                                  CategoryService categoryService,
                                  PriorityService priorityService,
                                  TaskService taskService) {
        super(userDataService);
        this.userDataService = userDataService;
        this.roleService = roleService;
        this.categoryService = categoryService;
        this.priorityService = priorityService;
        this.taskService = taskService;
    }

//    @PostMapping("/add")
//    public ResponseEntity<UserData> add(@RequestBody UserData userData){
//        if (userData.getId() != null && userData.getId() != 0) // если id заполнено, то значит это не новая категория
//        {
//            //id создается автоматически в БД (autoincrement), по этому его передавать не нужно, может быть ошибка
//            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
//        }
//        // если передали пустое значение title
//        if (userData.getName() == null && userData.getName().trim().length() == 0){
//            return new ResponseEntity("missed param: name", HttpStatus.NOT_ACCEPTABLE);
//        }
//        if (userData.getPassword() == null && userData.getPassword().trim().length() == 0) {
//            return new ResponseEntity("missed param: password",HttpStatus.NOT_ACCEPTABLE);
//        }
//        return ResponseEntity.ok((UserData) userDataService.create(userData));
//    }

//    @GetMapping("/id")
//    public Optional<UserData> findById(Long id){
//        return userDataService.getById(id);
//    }
    @RequestMapping("/addTask")
    public ResponseEntity<UserData> addTask(@RequestParam(value = "userDataId")Long userDataId,
                                            @RequestParam(value = "taskId")Long taskId){
        UserData userData = userDataService.findById(userDataId);
        Task task = taskService.findById(taskId);
        task.setUserData(userData);
        return ResponseEntity.status(HttpStatus.OK).body(userDataService.update(userData));
    }

    @RequestMapping("/addPriority")
    public ResponseEntity<UserData> addPriority(@RequestParam(value = "userDataId")Long userDataId,
                                            @RequestParam(value = "priorityId")Long priorityId){
        UserData userData = userDataService.findById(userDataId);
        Priority priority = priorityService.findById(priorityId);
        priority.setUserData(userData);
        return ResponseEntity.status(HttpStatus.OK).body(userDataService.update(userData));
    }

    @RequestMapping("/addCategory")
    public ResponseEntity<UserData> addCategory(@RequestParam(value = "userDataId")Long userDataId,
                                            @RequestParam(value = "categoryId")Long categoryId){
        UserData userData = userDataService.findById(userDataId);
        Category category = categoryService.findById(categoryId);
        category.setUserData(userData);
        return ResponseEntity.status(HttpStatus.OK).body(userDataService.update(userData));
    }

    @RequestMapping("/addRole")
    public ResponseEntity<UserData> addRole(@RequestParam(value = "userDataId")Long userDataId,
                                            @RequestParam(value = "roleId")Long roleId){
        UserData userData = userDataService.findById(userDataId);
        Role role = roleService.findById(roleId);
        role.getUsers().add(userData);
        return ResponseEntity.status(HttpStatus.OK).body(userDataService.update(userData));
    }
}
