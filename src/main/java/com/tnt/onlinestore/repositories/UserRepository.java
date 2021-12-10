package com.tnt.onlinestore.repositories;

import com.tnt.onlinestore.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByName (String name);
    UserEntity findByEmail (String email);
}
