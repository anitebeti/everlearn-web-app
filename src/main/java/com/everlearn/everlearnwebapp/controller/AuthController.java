package com.everlearn.everlearnwebapp.controller;

import com.everlearn.everlearnwebapp.exception.UserNotFoundException;
import com.everlearn.everlearnwebapp.model.SignInRequest;
import com.everlearn.everlearnwebapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/singin")
    public ResponseEntity signIn(@RequestBody SignInRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(authService.signIn(request.getSessionId(), request.getUserId()));
    }

}
