package com.everlearn.everlearnwebapp.model;

import com.everlearn.everlearnwebapp.entity.RoleEnum;

import java.util.Set;

public record SignInResponse (String firstName, String lastName,
                              String phoneNumber, String email,
                              Set<RoleEnum> roles, String token) { }
