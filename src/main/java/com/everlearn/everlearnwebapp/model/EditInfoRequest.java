package com.everlearn.everlearnwebapp.model;

public record EditInfoRequest (
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {

}
