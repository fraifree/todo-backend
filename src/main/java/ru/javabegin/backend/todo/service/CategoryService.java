package ru.javabegin.backend.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.Category;
import ru.javabegin.backend.todo.repository.CategoryRepository;

import java.util.List;

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это можно все реализовать сразу в контроллере
// такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service
// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе выполняются несколько SQL запросов и возникают исключения - то все выполненные операции
// откатятся (Roll back)
@Transactional
public class CategoryService {
    // работает встроенный механизм DI из Spring, который при старте приложения подставит в эту переменную нужные классы
    private final CategoryRepository categoryRepository; // сервис имеет право обращаться к репозиторию(БД)

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> findAll(String email){
        return categoryRepository.findByUserDataEmailOrderByTitleAsc(email);
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }
}
