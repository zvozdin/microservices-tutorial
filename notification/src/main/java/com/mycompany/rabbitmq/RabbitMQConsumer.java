package com.mycompany.rabbitmq;

import com.mycompany.NotificationService;
import com.mycompany.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RabbitMQConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.my.queue}")
    public void consume(NotificationRequest notificationRequest) {
        log.info("Consumed {} from queue." + notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}
