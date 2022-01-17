package com.mycompany.service;

import com.mycompany.clients.fraud.FraudCheckResponce;
import com.mycompany.clients.fraud.FraudClient;
import com.mycompany.customer.Customer;
import com.mycompany.customer.CustomerRegistrationRequest;
import com.mycompany.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponce fraudCheckResponce = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponce.isFraudster()) {
            throw new RuntimeException("fraudster!!!");
        }
    }
}
