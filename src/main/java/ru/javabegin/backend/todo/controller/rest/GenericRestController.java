package ru.javabegin.backend.todo.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.todo.model.GenericModel;
import ru.javabegin.backend.todo.service.GenericService;

import java.util.List;

@RestController
public abstract class GenericRestController<T extends GenericModel> {
    private final GenericService<T> genericService;

//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericRestController(GenericService<T> genericService) {
        this.genericService = genericService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(genericService.getAll());
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getOneById(@RequestParam(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(genericService.findById(id));

    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(@RequestBody T entity){
        genericService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @RequestMapping(value = "/update" , method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@RequestBody T updateEntity,
                                               @RequestParam(value = "id") Long id){
        updateEntity.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(genericService.create(updateEntity));
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam(value = "id") Long id){
        genericService.delete(id);
    }



}
