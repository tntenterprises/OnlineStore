package com.tnt.onlinestore.controllers;

import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@Transactional
public class SecurityController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PutMapping("/admin/makeadmin/{id}")
    public ResponseEntity<Optional<UserEntity>> makeAdmin(@PathVariable Long id) {
        userService.addAdminRole(id);
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/admin/removeAdmin/{id}")
    public ResponseEntity<Optional<UserEntity>> removeAdmin(@PathVariable Long id) {
        userService.removeAdminRole(id);
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.ACCEPTED);
    }

}
