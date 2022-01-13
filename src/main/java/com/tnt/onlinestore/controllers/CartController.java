package com.tnt.onlinestore.controllers;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.entities.ProductEntity;
import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.services.CartService;
import com.tnt.onlinestore.services.ProductService;
import com.tnt.onlinestore.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("carts")
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    public CartController(CartService cartService, UserService userService, ProductService productService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<Iterable<CartEntity>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<CartEntity> getCartById(@PathVariable Long id) {
        CartEntity cart = cartService.getCartById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("addProduct/{id}")
    public ResponseEntity<CartEntity> addProduct(@PathVariable Long id, @RequestBody ProductEntity productEntity) {
        UserEntity user = userService.findUserById(id).orElseThrow(EntityNotFoundException::new);
        CartEntity cart = user.getCart();
        cart.addProduct(productEntity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("removeProduct/{id}/{id2}")
    public ResponseEntity<CartEntity> removeProduct(@PathVariable Long id, @PathVariable Long id2) {
        UserEntity user = userService.findUserById(id).orElseThrow(EntityNotFoundException::new);
        CartEntity cart = user.getCart();
        ProductEntity product = productService.findById(id2).orElseThrow(EntityNotFoundException::new);
        cart.removeProduct(product);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("clear/{id}")
    public ResponseEntity<CartEntity> clearCart(@PathVariable Long id) {
        CartEntity cart = cartService.getCartById(id);
        cartService.clearCart(id);
        return new ResponseEntity<>(cart, HttpStatus.ACCEPTED);
    }

}
