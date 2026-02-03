package com.microservices.food_delivery.service;

import com.microservices.food_delivery.entity.FcmToken;
import com.microservices.food_delivery.entity.User;
import com.microservices.food_delivery.repository.FcmTokenRepository;
import com.microservices.food_delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FcmTokenService {

    private final FcmTokenRepository repository;
    private final UserRepository userRepository;

    public void saveToken(String token, Long userId) {

        if (repository.findByToken(token).isPresent()) return;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FcmToken fcmToken = new FcmToken(null, user, token);
        repository.save(fcmToken);
    }

    public List<String> getUserTokens(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(FcmToken::getToken)
                .toList();
    }
}

