package com.everlearn.everlearnwebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubscribeRequest {
    Long userId;
    Long courseId;
}
