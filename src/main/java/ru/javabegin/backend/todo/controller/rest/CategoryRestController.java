package ru.javabegin.backend.todo.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.todo.model.Category;
import ru.javabegin.backend.todo.search.CategorySearchValues;
import ru.javabegin.backend.todo.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestController extends GenericRestController<Category>{
    // доступ к данным ДБ
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }

//    @GetMapping("/id")
//    public Category findById() { return categoryService.findById(4L);}

//    @PostMapping("/all")
//    public List<Category> findAll () {
//        return categoryService.findAll();
//    }
    @PostMapping("/title")
    public ResponseEntity<Category> findByTitle(@RequestBody String title){
        return ResponseEntity.ok(categoryService.findAllByTitle(title));
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

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {

        // проверка на обязательные параметры
        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        // save работает как на добавление, так и на обновление
        categoryService.update(category);

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity delete(@PathVariable("id") Long id){
//        //можно обойтись и без try/catch, тогда будет возвращаться полная ошибка(stacktrace)
//        //тут показан пример, как можно обрабатывать исключение и отправить свой текст/статус
//        try {
//            categoryService.delete(id);
//        } catch (EmptyResultDataAccessException e) {
//            e.printStackTrace();
//            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
//        }
//        return new ResponseEntity(HttpStatus.OK);
//    }

    // поиск по любым параметрам CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {

        // проверка на обязательные параметры
        if (categorySearchValues.getEmail() == null || categorySearchValues.getEmail().trim().length() == 0) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        // поиск категорий пользователя по названию
        List<Category> list = categoryService.findByTitle(categorySearchValues.getTitle(), categorySearchValues.getEmail());

        return ResponseEntity.ok(list);
    }

//    @PostMapping("/id")
//    public ResponseEntity<Category> findById(@RequestBody Long id) {
//        Category category = null;
//        try {
//            category = categoryService.findById(id);
//        } catch (NoSuchElementException e) {//если объект не будет найден
//            e.printStackTrace();
//            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
//        }
//        return ResponseEntity.ok(category);
//    }
}
