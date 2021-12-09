package com.tnt.onlinestore.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductEntity {
    
    private @Id Long id;
    private String name;
    private double price;
    
    @ManyToOne
    private ManufacturerEntity manufacturer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StoreEntity> stores = new ArrayList<>();

}
