package ru.javabegin.backend.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.model.Role;
import ru.javabegin.backend.todo.repository.RoleRepository;

@Service
@Transactional
public class RoleService extends GenericService<Role>{
    private final RoleRepository roleRepository;
    protected RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }
}
