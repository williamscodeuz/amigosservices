package com.williamscode.notification;

import lombok.*;
import org.springframework.cglib.core.GeneratorStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @SequenceGenerator(name = "notification_id_sequence", sequenceName = "notification_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_sequence")
    private Integer notificationId;
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;
}
