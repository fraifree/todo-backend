package ru.javabegin.backend.todo.service;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.GenericModel;
import ru.javabegin.backend.todo.repository.GenericRepository;

import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericService <T extends GenericModel>{
    private final GenericRepository<T> genericRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericService(GenericRepository<T> genericRepository) {
        this.genericRepository = genericRepository;
    }

    //Получить информацию по ID
    public GenericModel findById(Long id){
        return genericRepository.getReferenceById(id);
    }

    public List<T> getAll(){
        return genericRepository.findAll();
    }

    public GenericModel create(T newEntity){
        return genericRepository.save(newEntity);
    }

    public void update(T entity){
        genericRepository.save(entity);
    }

    public void delete(Long id){
        genericRepository.deleteById(id);
    }



}
