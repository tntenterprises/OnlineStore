package com.tnt.onlinestore.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.tnt.onlinestore.entities.StoreEntity;
import com.tnt.onlinestore.repositories.StoreRepository;

import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreEntity create(StoreEntity storeEntity) {
        return storeRepository.save(storeEntity);
    }

    public void delete(Long id) {
        StoreEntity foundStore = storeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        storeRepository.deleteById(foundStore.getId());
    }

    public Optional<StoreEntity> findById(Long id) {
        return storeRepository.findById(id);
    }

    public Iterable<StoreEntity> findAll() {
        return storeRepository.findAll();
    }
}
