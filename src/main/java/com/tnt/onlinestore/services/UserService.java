package com.tnt.onlinestore.services;

import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.entities.RoleEntity;
import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.jms.sender.Sender;
import com.tnt.onlinestore.repositories.RoleRepository;
import com.tnt.onlinestore.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CartService cartService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Sender sender;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       CartService cartService, BCryptPasswordEncoder bCryptPasswordEncoder, Sender sender) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cartService = cartService;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.sender = sender;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity role = roleRepository.findByRoleName("ROLE_USER");
        userEntity.addRole(role);
        userEntity.setCart(new CartEntity());
        sender.sendCustomMessage("User created. ID: " + userEntity.getId() + " Name: " + userEntity.getName());
        return userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        CartEntity foundCart = foundUser.getCart();
        cartService.deleteCart(foundCart.getId());
        userRepository.deleteById(foundUser.getId());
    }

    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
