package ru.javabegin.backend.todo.service;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.Stat;
import ru.javabegin.backend.todo.repository.StatRepository;

@Service
public class StatService extends GenericService<Stat>{

    private final StatRepository statRepository;
    protected StatService(StatRepository statRepository) {
        super(statRepository);
        this.statRepository = statRepository;
    }
}
