package com.microservices.food_delivery.security;

import com.microservices.food_delivery.entity.User;
import com.microservices.food_delivery.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String input)
            throws UsernameNotFoundException {

        User user;

        if (input.contains("@")) {
            user = userRepository.findByEmail(input)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        } else {
            user = userRepository.findByPhoneNumber(input)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(input)   // email OR phone
                .password(user.getPassword() == null ? "" : user.getPassword())
                .roles(user.getRole() != null ? user.getRole().name() : "USER")
                .build();
    }
}
