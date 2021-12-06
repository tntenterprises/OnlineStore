package com.tnt.onlinestore.services;

import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartEntity createCart(CartEntity cartEntity) {
        return cartRepository.save(cartEntity);
    }

    public void deleteCart(Long id) {
        CartEntity foundCart = cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        cartRepository.deleteById(foundCart.getId());
    }
}
