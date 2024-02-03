package com.williamscode.notification;

import com.williamscode.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification sendNotification(NotificationRequest request){
        Notification notification = Notification.builder()
                .toCustomerId(request.toCustomerId())
                .toCustomerEmail(request.toCustomerEmail())
                .message(request.message())
                .sentAt(LocalDateTime.now())
                .sender("Williamscode")
                .build();
        return notificationRepository.save(notification);
    }
}
