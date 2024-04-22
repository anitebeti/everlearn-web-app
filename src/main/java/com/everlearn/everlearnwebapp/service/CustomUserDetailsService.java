package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.RoleEnum;
import com.everlearn.everlearnwebapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("UserNAME not found with email: " + email);
        }

        List<String> roles = user.get().getRoles()
                .stream()
                .map(RoleEnum::name)
                .collect(Collectors.toList());

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getEmail())
                .password(user.get().getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
        return userDetails;
    }

}
