package com.tnt.onlinestore.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ManufacturerEntity {

    private @Id Long id;
    
    @Column(unique = true)
    private String name;
    
    @OneToMany
    private List<ProductEntity> products = new ArrayList<>();
    
}
