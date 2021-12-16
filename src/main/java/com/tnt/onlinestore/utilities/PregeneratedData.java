package com.tnt.onlinestore.utilities;

import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.entities.RoleEntity;
import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Scope("singleton")
@Configuration
public class PregeneratedData {

    //Generate data at start up
    Set<RoleEntity> adminRoles = new HashSet<>();
    Set<RoleEntity> userRole = new HashSet<>();

    private final BCryptPasswordEncoder passwordEncoder;

    public PregeneratedData(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    CommandLineRunner startData(UserRepository userRepository, CartRepository cartRepository,
                                RoleRepository roleRepository, ProductRepository productRepository,
                                ManufacturerRepository manufacturerRepository, StoreRepository storeRepository) {
        return (args -> {
            RoleEntity admin = roleRepository.save(new RoleEntity(1L,"ROLE_ADMIN"));
            RoleEntity user = roleRepository.save(new RoleEntity(2L, "ROLE_USER"));

            adminRoles.add(admin);
            adminRoles.add(user);

            userRole.add(user);


            UserEntity adminTom = userRepository.save(new UserEntity(3L, "Tom", "tom@tnt.com", passwordEncoder.encode(
                    "abc"),"Fortress of " + "Solitude", adminRoles, new CartEntity()));
            UserEntity adminTobias = userRepository.save(new UserEntity(4L,"Tobias","tobias@tnt.com",
                    passwordEncoder.encode("123"),"Tobbeland", adminRoles, new CartEntity()));


        });
    }

}
