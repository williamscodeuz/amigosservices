package com.williamscode.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {

}
