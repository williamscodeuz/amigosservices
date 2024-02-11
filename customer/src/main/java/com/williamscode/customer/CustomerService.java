package com.williamscode.customer;

import com.williamscode.amqp.RabbitMQMessageProducer;
import com.williamscode.clients.fraud.FraudClient;
import com.williamscode.clients.fraud.FraudCheckResponse;
import com.williamscode.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        //todo check if firstName valid
        //todo check if lastName valid
        //todo check if email valid
        FraudCheckResponse fraudCheckResponse = fraudClient.createFraudCheckHistory(customer.getId());
        if (fraudCheckResponse == null || fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster customer");
        }
        NotificationRequest notificationRequest =
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to Williamscode...", customer.getFirstName())
                );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }
}
