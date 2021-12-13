package com.tnt.onlinestore.controllers;

import com.tnt.onlinestore.entities.ManufacturerEntity;
import com.tnt.onlinestore.services.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping("create")
    public ResponseEntity<ManufacturerEntity> createManufacturer(@RequestBody ManufacturerEntity manufacturer) {
        ManufacturerEntity createdManufacturer = manufacturerService.create(manufacturer);
        return new ResponseEntity<>(createdManufacturer, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        manufacturerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ManufacturerEntity>> findManufacturerById(@PathVariable Long id) {
        Optional<ManufacturerEntity> foundManufacturer = manufacturerService.findById(id);
        return new ResponseEntity<>(foundManufacturer, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<ManufacturerEntity>> findAllManufacturers() {
        Iterable<ManufacturerEntity> allManufacturers = manufacturerService.findAll();
        return new ResponseEntity<>(allManufacturers, HttpStatus.OK);
    }
}
