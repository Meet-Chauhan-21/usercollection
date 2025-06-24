package com.userProjects.userCollection.services;

import com.userProjects.userCollection.entity.User;
import com.userProjects.userCollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            User user1 = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user1.getUsername())
                    .password(user1.getPassword())
                    .roles(user1.getRoles()) // No ROLE_ prefix expected
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}
