package com.tnt.onlinestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RoleEntity {

    private @Id Long id;
    private String roleName;
    @ManyToMany
    private Set<UserEntity> users;

    public RoleEntity(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}
