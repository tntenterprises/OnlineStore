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
import java.util.Collections;
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
        //RoleEntity role = roleRepository.findByRoleName("ROLE_ADMIN");
        RoleEntity role2 = roleRepository.findByRoleName("ROLE_USER");
        //userEntity.addRole(role);
        userEntity.addRole(role2);
        userEntity.setCart(new CartEntity(userEntity.getId(), userEntity, Collections.emptyList()));
        userRepository.save(userEntity);
        sender.sendCustomMessage("User created. ID: " + userEntity.getId() + " Name: " + userEntity.getName());
        return userEntity;
    }

    public void deleteUser(Long id) {
        //UserEntity foundUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        //cartService.deleteCart(foundUser.getCart().getId());
        userRepository.deleteById(id);
    }

    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserName(Long id, String name) {
        UserEntity user = findUserById(id).orElseThrow(EntityNotFoundException::new);
        user.setName(name);
    }

    public void updateUserPassword(Long id, String password) {
        UserEntity user = findUserById(id).orElseThrow(EntityNotFoundException::new);
        user.setPassword(passwordEncoder.encode(password));
    }

    public void updateUserAddress(Long id, String address) {
        UserEntity user = findUserById(id).orElseThrow(EntityNotFoundException::new);
        user.setAddress(address);
    }

    public void addAdminRole(Long id) {
        UserEntity user = findUserById(id).orElseThrow(EntityNotFoundException::new);
        user.addRole(roleRepository.findByRoleName("ADMIN"));
    }

    public void removeAdminRole(Long id) {
        UserEntity user = findUserById(id).orElseThrow(EntityNotFoundException::new);
        user.removeRole(roleRepository.findByRoleName("ADMIN"));
    }

}
