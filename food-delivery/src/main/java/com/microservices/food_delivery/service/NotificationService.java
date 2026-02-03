package com.microservices.food_delivery.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notifyUser(String token, String title, String body) {
        send(token, title, body);
    }

    private void send(String token, String title, String body) {

        Message message = Message.builder()
                .setToken(token)
                .setNotification(
                        Notification.builder()
                                .setTitle(title)
                                .setBody(body)
                                .build()
                )
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
            System.out.println("✅ Notification sent successfully");
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}

