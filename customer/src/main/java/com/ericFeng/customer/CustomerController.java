package com.ericFeng.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/customers")
public record  CustomerController (CustomerService customerService){

    @GetMapping
    public List<Customer> getcustomer(){
        log.info("Http request get all customer");
        return customerService.getAllCustomer();
    }


    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest){
        log.info("new customer registration {}", customerRequest);

        customerService.registerCustomer(customerRequest);
    }
}
