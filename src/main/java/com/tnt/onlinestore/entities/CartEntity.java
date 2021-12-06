package com.tnt.onlinestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CartEntity {

    private @Id Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    public void addProduct(ProductEntity product) {
        products.add(product);
    }

    public void removeProduct(ProductEntity product) {
        products.remove(product);
    }

}