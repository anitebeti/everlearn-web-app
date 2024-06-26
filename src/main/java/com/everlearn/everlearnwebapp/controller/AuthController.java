package com.everlearn.everlearnwebapp.controller;

import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.exception.UserAlreadyExistsException;
import com.everlearn.everlearnwebapp.exception.UserAlreadyLoggedInException;
import com.everlearn.everlearnwebapp.model.SignInRequest;
import com.everlearn.everlearnwebapp.model.SignInResponse;
import com.everlearn.everlearnwebapp.model.SignUpRequest;
import com.everlearn.everlearnwebapp.security.JwtUtil;
import com.everlearn.everlearnwebapp.service.AuthService;
import com.everlearn.everlearnwebapp.service.UserService;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody SignInRequest request) throws UserAlreadyLoggedInException {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);

            String email = auth.getName();
            User user = userService.findByEmail(email);

            String token = jwtUtil.createToken(user);
            SignInResponse response = new SignInResponse(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getRoles(),
                    token);
            return ResponseEntity.ok(response);
        } catch (ConstraintViolationException e){
            log.error(e.getClass().getSimpleName() + " was thrown while signing in.");
            throw e;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpRequest request) throws UserAlreadyExistsException {
        try {
            authService.signUp(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPhoneNumber(),
                    request.getPassword());
        } catch (ConstraintViolationException e){
            log.error(e.getClass().getSimpleName() + " was thrown while signing up.");
            throw e;
        }
        return ResponseEntity.ok(request.getFirstName() + " signed up successfully!");
    }

}
