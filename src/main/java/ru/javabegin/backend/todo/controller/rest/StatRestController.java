package ru.javabegin.backend.todo.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.Stat;
import ru.javabegin.backend.todo.service.StatService;

@RestController
@RequestMapping("/stat")
public class StatRestController extends GenericRestController<Stat>{
    private final StatService statService;

    public StatRestController(StatService statService){
        super(statService);
        this.statService = statService;
    }

    @RequestMapping("/byEmail")
    public ResponseEntity<Stat> findByEmail(@RequestBody String email){
        return ResponseEntity.ok(statService.findStat(email));
    }
}
