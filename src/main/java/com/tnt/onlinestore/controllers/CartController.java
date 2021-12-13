package com.tnt.onlinestore.controllers;

import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.entities.ProductEntity;
import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.services.CartService;
import com.tnt.onlinestore.services.ProductService;
import com.tnt.onlinestore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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

    @GetMapping
    public ResponseEntity<Iterable<CartEntity>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<CartEntity> getCartById(@PathVariable Long id) {
        CartEntity cart = cartService.getCartById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("addProduct/{id}")
    public ResponseEntity<CartEntity> addProduct(@PathVariable Long id, @RequestBody ProductEntity productEntity) {
        UserEntity user = userService.findUserById(id).orElseThrow(EntityNotFoundException::new);
        CartEntity cart = user.getCart();
        cart.addProduct(productEntity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("removeProduct/{id}/{id2}")
    public ResponseEntity<CartEntity> removeProduct(@PathVariable Long id, @PathVariable Long id2) {
        UserEntity user = userService.findUserById(id).orElseThrow(EntityNotFoundException::new);
        CartEntity cart = user.getCart();
        ProductEntity product = productService.findById(id2).orElseThrow(EntityNotFoundException::new);
        cart.removeProduct(product);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("clear/{id}")
    public ResponseEntity<Void> clearCart(@PathVariable Long id) {
        cartService.clearCart(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
