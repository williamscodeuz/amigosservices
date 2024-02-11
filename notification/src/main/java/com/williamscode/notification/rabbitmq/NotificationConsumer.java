package com.williamscode.notification.rabbitmq;

import com.williamscode.clients.notification.NotificationRequest;
import com.williamscode.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consumed {} from queue", notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}
