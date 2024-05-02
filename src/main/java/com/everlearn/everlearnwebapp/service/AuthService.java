package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.entity.RoleEnum;
import com.everlearn.everlearnwebapp.exception.UserAlreadyExistsException;
import com.everlearn.everlearnwebapp.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void signUp(String firstName, String lastName, String email, String phoneNumber, String password)
            throws UserAlreadyExistsException {

        if (userService.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Account already created for this email.");
        }

        String encodedPassword = encoder.encode(password);

        User newUser = new User(firstName, lastName, email, phoneNumber, encodedPassword);
        newUser.addRole(RoleEnum.PARTICIPANT);
        userService.addUser(newUser);
    }
}
