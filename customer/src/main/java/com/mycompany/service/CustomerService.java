package com.mycompany.service;

import com.mycompany.customer.Customer;
import com.mycompany.customer.CustomerRegistrationRequest;
import com.mycompany.customer.FraudCheckResponce;
import com.mycompany.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponce fraudCheckResponce = restTemplate.getForObject(
                "http://fraud/api/v1/fraud-check/{customerId}",
                FraudCheckResponce.class,
                customer.getId());

        if (fraudCheckResponce.isFraudster()) {
            throw new RuntimeException("fraudster!!!");
        }
    }
}
