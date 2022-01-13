package com.tnt.onlinestore.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.tnt.onlinestore.entities.ProductEntity;
import com.tnt.onlinestore.repositories.ProductRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity create(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public void delete(Long id) {
        ProductEntity foundProduct = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productRepository.deleteById(foundProduct.getId());
    }

    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<ProductEntity> findAll() {
        return productRepository.findAll();
    }
}
