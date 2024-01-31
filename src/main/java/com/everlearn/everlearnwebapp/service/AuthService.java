package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.Session;
import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.exception.UserNotFoundException;
import com.everlearn.everlearnwebapp.repository.SessionRepository;
import com.everlearn.everlearnwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;

    public Session signIn(Long sessionId, Long userId) throws UserNotFoundException {
        if (userExists(userId)) {
            Optional<Session> existingSession = sessionRepository.findById(sessionId);
            if (existingSession.isPresent()) {
                return addUserToSession(existingSession.get(), sessionId, userId);
            } else {
                return addUserToSession(new Session(), sessionId, userId);
            }
        } else throw new UserNotFoundException("User not found with ID: " + userId);

    }

    public boolean userExists(Long userId) {
        return userRepository.findById(userId).isPresent();
    }

    private Session addUserToSession(Session session, Long sessionId, Long userId) {
        session.setUserId(userId);
        session.setStartTime(LocalDateTime.now());
        sessionRepository.save(session);
        return session;
    }
}
