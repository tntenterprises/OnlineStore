package com.tnt.onlinestore.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public void removeAllProducts() {
        products.removeAll(products);
    }

}
