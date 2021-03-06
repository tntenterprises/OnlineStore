package com.tnt.onlinestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RoleEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UserEntity> users;

    public RoleEntity(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}
