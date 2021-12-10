package com.tnt.onlinestore.services;

import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.entities.RoleEntity;
import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.repositories.CartRepository;
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
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       CartService cartService, BCryptPasswordEncoder bCryptPasswordEncoder, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cartService = cartService;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.cartRepository = cartRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity role = roleRepository.findByRoleName("ROLE_USER");
        userEntity.addRole(role);
        userEntity.setCart(new CartEntity());
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
