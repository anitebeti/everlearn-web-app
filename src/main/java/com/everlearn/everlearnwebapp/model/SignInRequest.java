package com.everlearn.everlearnwebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {

    private Long sessionId;
    private Long userId;
}
