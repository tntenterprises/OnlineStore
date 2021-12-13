package com.tnt.onlinestore.controllers;

import com.tnt.onlinestore.entities.StoreEntity;
import com.tnt.onlinestore.services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("create")
    public ResponseEntity<StoreEntity> createStore(@RequestBody StoreEntity store) {
        StoreEntity createdStore = storeService.create(store);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<StoreEntity>> findStoreById(@PathVariable Long id) {
        Optional<StoreEntity> foundStore = storeService.findById(id);
        return new ResponseEntity<>(foundStore, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<StoreEntity>> findAllStores() {
        Iterable<StoreEntity> allStores = storeService.findAll();
        return new ResponseEntity<>(allStores, HttpStatus.OK);
    }
}
