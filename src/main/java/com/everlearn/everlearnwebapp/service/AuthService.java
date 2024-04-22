package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.Session;
import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.entity.RoleEnum;
import com.everlearn.everlearnwebapp.exception.UserAlreadyExistsException;
import com.everlearn.everlearnwebapp.exception.UserAlreadyLoggedInException;
import com.everlearn.everlearnwebapp.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserService userService;

    public Session signIn(String email, String token) throws UserAlreadyLoggedInException {
        Optional<Session> session = sessionRepository.findByEmail(email);

        if (session.isPresent()) {
            return addUserToSession(session.get(), email, token);
        } else {
            return addUserToSession(new Session(), email, token);
        }
    }

    public void signUp(String firstName, String lastName, String email, String telephoneNumber, String password)
            throws UserAlreadyExistsException {

        if (userService.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Account already created for this email.");
        }

        User newUser = new User(firstName, lastName, email, telephoneNumber, password);
        newUser.addRole(RoleEnum.PARTICIPANT);
        userService.addUser(newUser);
    }


    private Session addUserToSession(Session session, String email, String token) {
        session.setEmail(email);
        session.setToken(token);
        session.setStartTime(LocalDateTime.now());
        sessionRepository.save(session);
        return session;
    }
}
