package ru.javabegin.backend.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.UserData;
import ru.javabegin.backend.todo.repository.UserDataRepository;

@Service
@Transactional
public class UserDataService extends GenericService<UserData>{

    private final UserDataRepository userDataRepository;

    public UserDataService(UserDataRepository userDataRepository) {
        super(userDataRepository);
        this.userDataRepository = userDataRepository;
    }


}
