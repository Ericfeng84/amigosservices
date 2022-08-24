package com.ericFeng.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer=Customer.builder().
                firstName(request.firstName())
                .lastName(request.LastName())
                .email(request.email()).
                build();
        //todo check if email valid
        //todo check email is not token
        //todo store customer in db
        customerRepository.save(customer);

    }
}
