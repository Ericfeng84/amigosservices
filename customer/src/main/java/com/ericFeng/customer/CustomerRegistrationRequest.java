package com.ericFeng.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String LastName,
        String email) {

}
