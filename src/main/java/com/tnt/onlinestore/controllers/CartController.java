package com.tnt.onlinestore.controllers;

import javax.persistence.EntityNotFoundException;

import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.entities.ProductEntity;
import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.services.CartService;
import com.tnt.onlinestore.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carts")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("addProduct/{id}")
    public ResponseEntity<CartEntity> addProduct(@PathVariable Long id, @RequestBody ProductEntity productEntity) {
        UserEntity user = userService.findUserById(id).orElseThrow(EntityNotFoundException::new);
        CartEntity cart = user.getCart();
        cart.addProduct(productEntity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("removeProduct/{id}")
    public ResponseEntity<CartEntity> removeProduct(@PathVariable Long id, @RequestBody ProductEntity productEntity) {
        UserEntity user = userService.findUserById(id).orElseThrow(EntityNotFoundException::new);
        CartEntity cart = user.getCart();
        cart.removeProduct(productEntity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("clear/{id}")
    public ResponseEntity<Void> clearCart(@PathVariable Long id) {
        cartService.clearCart(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
