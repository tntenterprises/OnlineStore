package com.tnt.onlinestore.controllers;

import java.util.Collections;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.payloads.request.LoginRequest;
import com.tnt.onlinestore.payloads.response.JwtResponse;
import com.tnt.onlinestore.payloads.response.MessageResponse;
import com.tnt.onlinestore.repositories.UserRepository;
import com.tnt.onlinestore.security.UserDetailsImpl;
import com.tnt.onlinestore.security.jwt.JwtUtils;
import com.tnt.onlinestore.services.CartService;
import com.tnt.onlinestore.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;

@Controller
@Transactional
@AllArgsConstructor
public class SecurityController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserEntity user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        
        return ResponseEntity.ok(new JwtResponse(jwt, user));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already exists"));
        }
        
        //Create user from provided body. The cart will be initialised as null by this point.
        UserEntity createdUser = userService.createUser(user);

        //Create a new cart with using the user's id so that they are shared for simplicity. Assign the new user to it.
        CartEntity cart = cartService.createCart(new CartEntity(createdUser.getId(), createdUser, Collections.emptyList()));

        //Set the userCart to the one just created.
        createdUser.setCart(cart);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/makeadmin/{id}")
    public ResponseEntity<Optional<UserEntity>> makeAdmin(@PathVariable Long id) {
        userService.addAdminRole(id);
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/removeadmin/{id}")
    public ResponseEntity<Optional<UserEntity>> removeAdmin(@PathVariable Long id) {
        userService.removeAdminRole(id);
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.ACCEPTED);
    }

}
