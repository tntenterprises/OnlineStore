package com.tnt.onlinestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }

    //Generate RoleEntities at startup

    /*@Bean
    public CommandLineRunner setUpRoles(RoleRepository roleRepository) {
        return (args -> {
            roleRepository.save(new RoleEntity(1L,"ROLE_ADMIN"));
            roleRepository.save(new RoleEntity(2L,"ROLE_USER"));
        });
    }*/

}
