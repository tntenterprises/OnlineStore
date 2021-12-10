package com.tnt.onlinestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    
    @ManyToOne
    @JsonIgnore
    private ManufacturerEntity manufacturer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StoreEntity> stores = new ArrayList<>();

}
