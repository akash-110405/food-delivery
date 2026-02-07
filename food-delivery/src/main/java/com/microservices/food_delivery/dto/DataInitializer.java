package com.microservices.food_delivery.dto;

import com.microservices.food_delivery.entity.Role;
import com.microservices.food_delivery.entity.User;
import com.microservices.food_delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer  implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // ADMIN
        if (!userRepository.existsByEmail("admin@gmail.com")) {
            User admin = new User();
            admin.setName("LUFFY");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("Admin@123"));
            admin.setPhoneNumber("3214569873");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }

        // CHEF
        if (!userRepository.existsByEmail("chef@gmail.com")) {
            User chef = new User();
            chef.setName("SANJI");
            chef.setEmail("chef@gmail.com");
            chef.setPassword(passwordEncoder.encode("Chef@123"));
            chef.setPhoneNumber("3698521473");
            chef.setRole(Role.CHEF);
            userRepository.save(chef);
        }
    }
}
