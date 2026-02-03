package com.microservices.food_delivery.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final FcmTokenService fcmTokenService;

    public void notifyUser(Long userId, String title, String body) {

        List<String> tokens = fcmTokenService.getUserTokens(userId);

        for (String token : tokens) {
            send(token, title, body);
        }
    }

    private void send(String token, String title, String body) {

        Message message = Message.builder()
                .setToken(token)
                .setNotification(
                        Notification.builder()
                                .setTitle(title) .setBody(body) .build() )
                .build();

        try { String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Sent message: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}

