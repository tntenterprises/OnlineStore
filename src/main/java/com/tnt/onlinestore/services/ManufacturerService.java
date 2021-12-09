package com.tnt.onlinestore.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.tnt.onlinestore.entities.ManufacturerEntity;
import com.tnt.onlinestore.repositories.ManufacturerRepository;

import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public ManufacturerEntity create(ManufacturerEntity manufacturerEntity) {
        return manufacturerRepository.save(manufacturerEntity);
    }

    public void delete(Long id) {
        ManufacturerEntity foundManufacturer = manufacturerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        manufacturerRepository.deleteById(foundManufacturer.getId());
    }

    public Optional<ManufacturerEntity> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    public Iterable<ManufacturerEntity> findAll() {
        return manufacturerRepository.findAll();
    }
}
