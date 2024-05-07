package com.everlearn.everlearnwebapp.model;

import com.everlearn.everlearnwebapp.entity.RoleEnum;

import java.util.Set;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        Set<RoleEnum> roles) {
}
