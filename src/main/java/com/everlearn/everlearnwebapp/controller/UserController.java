package com.everlearn.everlearnwebapp.controller;

import com.everlearn.everlearnwebapp.model.RoleChangeRequest;
import com.everlearn.everlearnwebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity accessHomePage() {
        return ResponseEntity.ok("User accesed homepage successfully");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity accessAdminDashboard() {
        //vezi ce exc ce iti arunca, prinde-o si arunca ceva de-al tau cu global exception handler
        return ResponseEntity.ok("Admin accessed admin dashboard successfully");
    }

//    @PutMapping("/admin/addRole")
//    public ResponseEntity addRolesToUser(@RequestBody RoleChangeRequest request) {
//        userService.addRolesToUser(request.getEmail(), request.getRoles());
//        return ResponseEntity.ok(
//                request.getRoles().stream()
//                        .map(Object::toString)
//                        .collect(Collectors.joining(", "))
//                        + " were added to "
//                        + request.getEmail());
//    }
}
