package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.repository.SessionRepository;
import com.everlearn.everlearnwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }


}
