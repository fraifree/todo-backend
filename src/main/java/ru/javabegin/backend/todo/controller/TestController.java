package ru.javabegin.backend.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// controller - это специальный bin, который обрабатывает запросы
@RestController
public class TestController {

    @GetMapping("/all")
    public String findAll(){
        return "Yo mate";
    }
}
