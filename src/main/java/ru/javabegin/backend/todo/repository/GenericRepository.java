package ru.javabegin.backend.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.javabegin.backend.todo.model.GenericModel;

@NoRepositoryBean
public interface GenericRepository<T extends GenericModel > extends JpaRepository<T,Long> {
}
