package com.tnt.onlinestore.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tnt.onlinestore.entities.CartEntity;
import com.tnt.onlinestore.entities.UserEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private UserEntity userEntity;
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private String address;

    private Collection<? extends GrantedAuthority> authorities;

    private CartEntity cart;

    public UserDetailsImpl(UserEntity userEntity, Long id, String email, String password, String address, List<GrantedAuthority> authorities, CartEntity cart) {
        this.userEntity = userEntity;
        this.id = id;
        this.email = email;
        this.password = password;
        this.address = address;
        this.authorities = authorities;
        this.cart = cart;
    }

    public static UserDetailsImpl build(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        return new UserDetailsImpl(userEntity, userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getAddress(), authorities, userEntity.getCart());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public CartEntity getCart() {
        return cart;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
