package com.tnt.onlinestore.services;

import com.tnt.onlinestore.entities.RoleEntity;
import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.jms.sender.Sender;
import com.tnt.onlinestore.repositories.CartRepository;
import com.tnt.onlinestore.repositories.RoleRepository;
import com.tnt.onlinestore.repositories.UserRepository;
import com.tnt.onlinestore.validators.UserValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Sender sender;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       CartService cartService, CartRepository cartRepository, BCryptPasswordEncoder bCryptPasswordEncoder, Sender sender, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.sender = sender;
        this.userValidator = userValidator;
    }

    public UserEntity createUser(UserEntity userEntity) {
        //Use validator class to check whether fields are not null and of valid length
        userValidator.validateUserName(userEntity);
        userValidator.validateUserEmail(userEntity);
        userValidator.validateUserPassword(userEntity);
        userValidator.validateUserAddress(userEntity);

        //Encode password at point of creation
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        //Add role(s) to new userEntity
        RoleEntity role = roleRepository.findByRoleName("ROLE_ADMIN");
        RoleEntity role2 = roleRepository.findByRoleName("ROLE_USER");
        userEntity.addRole(role);
        userEntity.addRole(role2);

        //Cart initialises here as null. Methods to create cart/ set user's cart are called in the UserController.
        userEntity.setCart(null);

        //Save the new UserEntity and send custom message via JMS
        userRepository.save(userEntity);
        sender.sendCustomMessage("User created. ID: " + userEntity.getId() + " Name: " + userEntity.getName());
        return userEntity;
    }

    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        RoleEntity admin = roleRepository.findByRoleName("ROLE_ADMIN");
        RoleEntity userRole = roleRepository.findByRoleName("ROLE_USER");

        if (userRepository.findById(id).isEmpty()) {
            throw new com.tnt.onlinestore.exceptions.responseEntityExceptions.EntityNotFoundException("No user with " +
                    "id "+ id);
        } else

        user.removeRole(admin);
        user.removeRole(userRole);
        userRepository.deleteById(user.getId());
    }

    public Optional<UserEntity> findUserById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new com.tnt.onlinestore.exceptions.responseEntityExceptions.EntityNotFoundException("User with id " + id + " not found");
        } else
        return userRepository.findById(id);
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserName(Long id, String name) {
        userValidator.validateNameString(name);
        UserEntity user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setName(name);
    }

    public void updateUserPassword(Long id, String password) {
        userValidator.validatePasswordString(password);
        UserEntity user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setPassword(passwordEncoder.encode(password));
    }

    public void updateUserAddress(Long id, String address) {
        userValidator.validateAddressString(address);
        UserEntity user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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
