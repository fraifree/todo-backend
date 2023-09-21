package ru.javabegin.backend.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.Stat;
import ru.javabegin.backend.todo.repository.StatRepository;

@Service
@Transactional
public class StatService extends GenericService<Stat>{

    private final StatRepository statRepository;

    protected StatService(StatRepository statRepository) {
        super(statRepository);
        this.statRepository = statRepository;
    }

    public Stat findStat(String email){
        return statRepository.findByUserDataEmail(email);
    }
}
