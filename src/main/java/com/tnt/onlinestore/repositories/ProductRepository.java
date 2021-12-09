package com.tnt.onlinestore.repositories;

import com.tnt.onlinestore.entities.ProductEntity;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Iterable<ProductEntity> findByName(String name);
}
