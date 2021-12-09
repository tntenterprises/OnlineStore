package com.tnt.onlinestore.repositories;

import com.tnt.onlinestore.entities.StoreEntity;

import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<StoreEntity, Long> {
    Iterable<StoreEntity> findByCity(String name);
}
