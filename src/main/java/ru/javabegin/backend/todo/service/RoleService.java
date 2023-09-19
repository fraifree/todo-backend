package ru.javabegin.backend.todo.service;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.Role;
import ru.javabegin.backend.todo.repository.RoleRepository;

@Service
public class RoleService extends GenericService<Role>{
    private final RoleRepository roleRepository;
    protected RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }
}
