package com.demo_banking.service;

import com.demo_banking.models.User;
import com.demo_banking.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User customUser = userRepository.getUserByEmail(email);
        if (customUser == null)
            throw new UsernameNotFoundException(email + " not found");

        List<GrantedAuthority> roles;

        roles = Collections.emptyList();


        return new org.springframework.security.core.userdetails.User(customUser.getEmail(), customUser.getPassword(), roles);
    }
}