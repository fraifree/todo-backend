package ru.javabegin.backend.todo.repository;

import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.model.Stat;
@Repository
public interface StatRepository extends GenericRepository<Stat>{

    Stat findByUserDataEmail(String email);
}
