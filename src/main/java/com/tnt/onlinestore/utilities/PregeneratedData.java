package com.tnt.onlinestore.utilities;

import java.util.HashSet;
import java.util.Set;

import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.entities.RoleEntity;
import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.repositories.CartRepository;
import com.tnt.onlinestore.repositories.ManufacturerRepository;
import com.tnt.onlinestore.repositories.ProductRepository;
import com.tnt.onlinestore.repositories.RoleRepository;
import com.tnt.onlinestore.repositories.StoreRepository;
import com.tnt.onlinestore.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Scope("singleton")
@Configuration
public class PregeneratedData {

    //Generate data at start up
    Set<RoleEntity> adminRoles = new HashSet<>();

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


            UserEntity adminTom = userRepository.save(new UserEntity(1L, "admin", "admin@tnt.com", passwordEncoder.encode(
                    "tntenterprises"),"Nowhere", adminRoles, new CartEntity()));


        });
    }

}
