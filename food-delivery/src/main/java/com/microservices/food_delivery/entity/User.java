package com.microservices.food_delivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.microservices.food_delivery.entity.Role;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;

    @Column(nullable = false)
    private String password;
    private String otp;

    private Integer tokenVersion = 0;

    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime create_at;

    @PrePersist
    public void onCreate() {
        this.create_at = LocalDateTime.now();
    }

}
