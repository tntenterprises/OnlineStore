package com.tnt.onlinestore.controllers;

import com.tnt.onlinestore.entities.ProductEntity;
import com.tnt.onlinestore.entities.StoreEntity;
import com.tnt.onlinestore.services.ProductService;
import com.tnt.onlinestore.services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;
    private final StoreService storeService;

    public ProductController(ProductService productService, StoreService storeService) {
        this.productService = productService;
        this.storeService = storeService;
    }

    @PostMapping("create/{storeIds}")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product, @PathVariable(required = false) List<Long> storeIds) {
        ProductEntity createdProduct = productService.create(product);
        storeIds.forEach(id -> {
            try {
                StoreEntity store = storeService.findById(id).get();
                createdProduct.getStores().add(store);
            } catch (NoSuchElementException ignored) {}

        });
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ProductEntity>> findProductById(@PathVariable Long id) {
        Optional<ProductEntity> foundProduct = productService.findById(id);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<ProductEntity>> findAllProducts() {
        Iterable<ProductEntity> allProducts = productService.findAll();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
}
