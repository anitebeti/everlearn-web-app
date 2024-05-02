package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.RoleEnum;
import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.repository.SessionRepository;
import com.everlearn.everlearnwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void addRolesToUser(String email, List<RoleEnum> roles) {
        Optional<User> user = findByEmail(email);
        if (user.isEmpty()) {
            new UsernameNotFoundException("User not found with email: " + email);
        }
        roles.forEach(role -> user.get().addRole(role));
        userRepository.save(user.get());
    }


}
