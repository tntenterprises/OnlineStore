package com.tnt.onlinestore.controllers;

import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.services.CartService;
import com.tnt.onlinestore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final CartService cartService;

    public UserController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping()
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UserEntity>> findUserById(@PathVariable Long id) {
        Optional<UserEntity> foundUser = userService.findUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<UserEntity>> findAllUsers() {
        Iterable<UserEntity> allUsers = userService.findAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PatchMapping("changepassword/{id}")
    public ResponseEntity<Optional<UserEntity>> changePassword(@PathVariable Long id, @RequestBody String password) {
        userService.updateUserPassword(id, password);
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.ACCEPTED);
    }

    @PatchMapping("changename/{id}")
    public ResponseEntity<Optional<UserEntity>> changeName(@PathVariable Long id, @RequestBody String name) {
        userService.updateUserName(id, name);
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.ACCEPTED);
    }

    @PatchMapping("changeaddress/{id}")
    public ResponseEntity<Optional<UserEntity>> changeAddress(@PathVariable Long id, @RequestBody String address) {
        userService.updateUserAddress(id, address);
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.ACCEPTED);
    }

}
