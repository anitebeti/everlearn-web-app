package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.repository.SessionRepository;
import com.everlearn.everlearnwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
