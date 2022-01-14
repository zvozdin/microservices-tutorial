package com.mycompany.service;

import com.mycompany.customer.Customer;
import com.mycompany.customer.CustomerRegistrationRequest;
import com.mycompany.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.save(customer);
    }
}
