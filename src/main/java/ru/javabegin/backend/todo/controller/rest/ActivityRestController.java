package ru.javabegin.backend.todo.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.Activity;
import ru.javabegin.backend.todo.service.ActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityRestController extends GenericRestController<Activity> {

    private final ActivityService activityService;
    protected ActivityRestController(ActivityService activityService) {
        super(activityService);
        this.activityService = activityService;
    }
}
