package com.microservices.food_delivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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

    @Column(nullable = false, unique = true,length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(length = 6)
    private String otp;
    private LocalDateTime otpexpiry;

    private Integer tokenVersion = 0;

    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime create_at;

    @PrePersist
    public void onCreate() {
        this.create_at = LocalDateTime.now();
    }


}
