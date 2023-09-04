package ru.javabegin.backend.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.todo.model.Category;
import ru.javabegin.backend.todo.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    // доступ к данным ДБ
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/id")
    public Category findById() { return categoryService.findById(1L);}

    @PostMapping("/all")
    public List<Category> findAll (@RequestBody String email) {
        return categoryService.findAll(email);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category){
        //проверка на обязательные параметры
        if (category.getId() != null && category.getId() != 0) // если id заполнено, то значит это не новая категория
        {
          //id создается автоматически в БД (autoincrement), по этому его передавать не нужно, может быть ошибка
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        // если передали пустое значение title
        if (category.getTitle() == null && category.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryService.add(category));
    }
}
