package com.williamscode.notification;

import com.williamscode.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {
        "com.williamscode.notification",
        "com.williamscode.amqp"
})
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.williamscode.clients"
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    /*@Bean
    CommandLineRunner commandLineRunner (
            RabbitMQMessageProducer producer,
            NotificationConfig notificationConfig
    ){
        return args -> {
            producer.publish("foo",
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey()
            );

        };
    }*/
}
