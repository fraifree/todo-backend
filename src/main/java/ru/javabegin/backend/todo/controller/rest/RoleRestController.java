package ru.javabegin.backend.todo.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.Role;
import ru.javabegin.backend.todo.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleRestController extends GenericRestController<Role>{

    private final RoleService roleService;

    public RoleRestController(RoleService roleService){
        super(roleService);
        this.roleService = roleService;
    }
}
