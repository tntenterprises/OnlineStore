package com.tnt.onlinestore.repositories;

import com.tnt.onlinestore.entities.CartEntity;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<CartEntity, Long> {
}
