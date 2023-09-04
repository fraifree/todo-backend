package ru.javabegin.backend.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.UserData;
import ru.javabegin.backend.todo.service.UserDataService;

@RestController
@RequestMapping("/user")
public class UserDataController {

    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserData> add(@RequestBody UserData userData){
        if (userData.getId() != null && userData.getId() != 0) // если id заполнено, то значит это не новая категория
        {
            //id создается автоматически в БД (autoincrement), по этому его передавать не нужно, может быть ошибка
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        // если передали пустое значение title
        if (userData.getName() == null && userData.getName().trim().length() == 0){
            return new ResponseEntity("missed param: name", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userData.getPassword() == null && userData.getPassword().trim().length() == 0) {
            return new ResponseEntity("missed param: password",HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok((UserData) userDataService.create(userData));
    }
}