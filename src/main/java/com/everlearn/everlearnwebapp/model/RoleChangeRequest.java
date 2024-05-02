package com.everlearn.everlearnwebapp.model;

import com.everlearn.everlearnwebapp.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RoleChangeRequest {
    private String email;
    private List<RoleEnum> roles;
}
