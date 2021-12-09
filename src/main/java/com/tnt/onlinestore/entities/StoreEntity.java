package com.tnt.onlinestore.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StoreEntity {

    private @Id Long id;
    private String city;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductEntity> products = new ArrayList<>();

}
