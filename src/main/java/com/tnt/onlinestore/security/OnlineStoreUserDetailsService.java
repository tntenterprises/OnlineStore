package com.tnt.onlinestore.security;

import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OnlineStoreUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public OnlineStoreUserDetailsService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User with name " + username + " not found.");
        }
        return new OnlineStoreUserPrincipal(userEntity);
    }

}
