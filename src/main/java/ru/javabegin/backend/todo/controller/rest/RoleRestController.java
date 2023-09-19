package ru.javabegin.backend.todo.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.todo.model.Role;
import ru.javabegin.backend.todo.model.UserData;
import ru.javabegin.backend.todo.service.RoleService;
import ru.javabegin.backend.todo.service.UserDataService;

@RestController
@RequestMapping("/role")
public class RoleRestController extends GenericRestController<Role>{

    private final RoleService roleService;
    private final UserDataService userDataService;

    public RoleRestController(RoleService roleService, UserDataService userDataService){
        super(roleService);
        this.roleService = roleService;
        this.userDataService = userDataService;
    }

    @RequestMapping("/addUser")
    public ResponseEntity<Role> addUser(@RequestParam(value = "roleId") Long roleId,
                                        @RequestParam(value = "userDataId") Long userDataId){
        Role role = roleService.findById(roleId);
        UserData userData = userDataService.findById(userDataId);
        role.getUsers().add(userData);
        return ResponseEntity.status(HttpStatus.OK).body(roleService.update(role));

    }
}
