package com.tnt.onlinestore.controllers;

import java.util.Optional;

import javax.transaction.Transactional;

import com.tnt.onlinestore.entities.StoreEntity;
import com.tnt.onlinestore.services.StoreService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("create")
    public ResponseEntity<StoreEntity> createStore(@RequestBody StoreEntity store) {
        StoreEntity createdStore = storeService.create(store);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<Optional<StoreEntity>> findStoreById(@PathVariable Long id) {
        Optional<StoreEntity> foundStore = storeService.findById(id);
        return new ResponseEntity<>(foundStore, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public ResponseEntity<Iterable<StoreEntity>> findAllStores() {
        Iterable<StoreEntity> allStores = storeService.findAll();
        return new ResponseEntity<>(allStores, HttpStatus.OK);
    }
}
