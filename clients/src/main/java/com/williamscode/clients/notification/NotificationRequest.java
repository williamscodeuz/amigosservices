package com.williamscode.clients.notification;

public record NotificationRequest (
        Integer toCustomerId,
        String toCustomerEmail,
        String message
){
}
