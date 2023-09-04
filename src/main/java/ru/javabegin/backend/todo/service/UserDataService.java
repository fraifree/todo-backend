package ru.javabegin.backend.todo.service;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.UserData;
import ru.javabegin.backend.todo.repository.UserDataRepository;

@Service
public class UserDataService extends GenericService<UserData>{

    private final UserDataRepository userDataRepository;

    public UserDataService(UserDataRepository userDataRepository) {
        super(userDataRepository);
        this.userDataRepository = userDataRepository;
    }


}
