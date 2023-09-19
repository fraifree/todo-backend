package ru.javabegin.backend.todo.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.GenericModel;
import ru.javabegin.backend.todo.service.GenericService;

import java.util.List;

@RestController
public abstract class GenericRestController<T extends GenericModel> {
    private final GenericService<T> genericService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericRestController(GenericService<T> genericService) {
        this.genericService = genericService;
    }

    public ResponseEntity<List<T>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(genericService.getAll());
    }

    public ResponseEntity<GenericModel> getOneById(@RequestParam(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(genericService.findById(id));

    }

    public ResponseEntity<T> create(@RequestBody T entity){
        genericService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    public ResponseEntity<GenericModel> update(@RequestBody T updateEntity,
                                               @RequestParam(value = "id") Long id){
        updateEntity.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(genericService.create(updateEntity));
    }

    public void delete(@RequestParam(value = "id") Long id){
        genericService.delete(id);
    }



}
