package com.ericFeng.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer=Customer.builder().
                firstName(request.firstName())
                .lastName(request.LastName())
                .email(request.email()).
                build();
        //todo check if email valid
        //todo check email is not token
        //todo store customer in db
        customerRepository.saveAndFlush(customer);
//        check Fraud
        CheckFraudResponse checkFraudResponse = restTemplate.getForObject(
                "http://localhost:8081/.api/v1/fraud/{customerID}",
                CheckFraudResponse.class, customer.getId()
        );

        if (checkFraudResponse.isFraud()){
            throw new IllegalStateException("Fraud");
        }



    }
}
