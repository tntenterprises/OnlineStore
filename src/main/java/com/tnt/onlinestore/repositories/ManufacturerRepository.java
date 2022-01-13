package com.tnt.onlinestore.repositories;

import com.tnt.onlinestore.entities.ManufacturerEntity;

import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepository extends CrudRepository<ManufacturerEntity, Long> {
    ManufacturerEntity findByName(String name);
}
