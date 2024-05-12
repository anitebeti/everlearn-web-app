package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.RoleEnum;
import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with this email: " + email);
        }
        return user.get();
    }
    public User findById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with this userId: " + userId);
        }
        return user.get();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void changeUserRoles(String email, List<RoleEnum> roles) {
        User user = findByEmail(email);
        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void editInfo(Long id, String firstName, String lastName, String email, String phoneNumber) {
        User user = findById(id);
        if (!user.getFirstName().equals(firstName)) {
            user.setFirstName(firstName);
        }
        if (!user.getLastName().equals(lastName)) {
            user.setLastName(lastName);
        }
        if (!user.getEmail().equals(email)) {
            user.setEmail(email);
        }
        if (!user.getPhoneNumber().equals(phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
        }
        userRepository.save(user);
    }

}
