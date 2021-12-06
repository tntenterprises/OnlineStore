package com.tnt.onlinestore.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class UserEntity {

    private @Id Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<RoleEntity> roles;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
    private String address;

    public void addRole(RoleEntity role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(RoleEntity role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }
}
