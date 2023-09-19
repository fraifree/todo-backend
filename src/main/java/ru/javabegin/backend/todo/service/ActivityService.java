package ru.javabegin.backend.todo.service;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.Activity;
import ru.javabegin.backend.todo.repository.ActivityRepository;
@Service
public class ActivityService extends GenericService<Activity> {

    private final ActivityRepository activityRepository;
    protected ActivityService(ActivityRepository activityRepository) {
        super(activityRepository);
        this.activityRepository = activityRepository;
    }
}
