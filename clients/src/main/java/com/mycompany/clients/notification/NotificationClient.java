package com.mycompany.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "notification")
public interface NotificationClient {

    @PostMapping(path = "/api/v1/notifications")
    void sendNotification(NotificationRequest notificationRequest);
}
