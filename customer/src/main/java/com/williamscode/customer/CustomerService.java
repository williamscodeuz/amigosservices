package com.williamscode.customer;

import com.williamscode.clients.fraud.FraudClient;
import com.williamscode.clients.fraud.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

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
        /*FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );*/
        FraudCheckResponse fraudCheckResponse = fraudClient.createFraudCheckHistoty(customer.getId());
        if (fraudCheckResponse == null || fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster customer");
        }


    }
}
