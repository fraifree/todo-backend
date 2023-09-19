package ru.javabegin.backend.todo.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.todo.model.Category;
import ru.javabegin.backend.todo.model.Task;
import ru.javabegin.backend.todo.model.UserData;
import ru.javabegin.backend.todo.search.CategorySearchValues;
import ru.javabegin.backend.todo.service.CategoryService;
import ru.javabegin.backend.todo.service.TaskService;
import ru.javabegin.backend.todo.service.UserDataService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestController extends GenericRestController<Category>{
    // доступ к данным ДБ
    private final CategoryService categoryService;
    private final UserDataService userDataService;

    private final TaskService taskService;


    public CategoryRestController(CategoryService categoryService,
                                  UserDataService userDataService,
                                  TaskService taskService) {
        super(categoryService);
        this.categoryService = categoryService;
        this.userDataService = userDataService;
        this.taskService = taskService;
    }


    @PostMapping("/title")
    public ResponseEntity<Category> findByTitle(@RequestBody String title){
        return ResponseEntity.ok(categoryService.findAllByTitle(title));
    }

    @PostMapping("/addUser")
    public ResponseEntity<Category> addUser(@RequestParam(value = "categoryId")Long categoryId,
                                            @RequestParam(value = "userDataId")Long userDataId){
        Category category = categoryService.findById(categoryId);
        UserData userData = userDataService.findById(userDataId);
        category.setUserData(userData);
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(category));
    }

    @PostMapping("/addTask")
    public ResponseEntity<Category> addTask(@RequestParam(value = "categoryId")Long categoryId,
                                            @RequestParam(value = "taskId")Long taskId){
        Category category = categoryService.findById(categoryId);
        Task task = taskService.findById(taskId);
//        category.getTaskList().add(task);
        task.setCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(category));
    }


    // поиск по любым параметрам CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {

        // проверка на обязательные параметры
        if (categorySearchValues.getEmail() == null || categorySearchValues.getEmail().trim().isEmpty()) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        // поиск категорий пользователя по названию
        List<Category> list = categoryService.findByTitle(categorySearchValues.getTitle(), categorySearchValues.getEmail());

        return ResponseEntity.ok(list);
    }

}
