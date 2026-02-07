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

    private final FcmTokenRepository fcmTokenRepository;
    private final UserRepository userRepository;

    public void saveToken(String token, String email) {

        System.out.println("FCM TOKEN: " + token);
        System.out.println("EMAIL: " + email);

        if (fcmTokenRepository.findByToken(token).isPresent()) return;

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FcmToken fcmToken = new FcmToken(null, user, token);
        fcmTokenRepository.save(fcmToken);
    }


    public List<String> getUserTokens(Long userId) {
        return fcmTokenRepository.findByUserId(userId)
                .stream()
                .map(FcmToken::getToken)
                .toList();
    }
}