package com.tnt.onlinestore.repositories;

import com.tnt.onlinestore.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findByRoleName(String roleName);
}
