package ru.javabegin.backend.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.Priority;
import ru.javabegin.backend.todo.repository.PriorityRepository;

@Service
@Transactional
public class PriorityService extends GenericService<Priority>{
    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        super(priorityRepository);
        this.priorityRepository = priorityRepository;
    }
}
