//package com.everlearn.everlearnwebapp.controller;
//
//import com.everlearn.everlearnwebapp.entity.User;
//import com.everlearn.everlearnwebapp.exception.UserNotFoundException;
//import com.everlearn.everlearnwebapp.repository.UserRepository;
//import com.everlearn.everlearnwebapp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//    //TODO - sa returneze toate ResponseEntity?
//
//    //TODO modifica exceptia
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws Exception {
//        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("NU A FOST GASIT USERUL"));
//        return ResponseEntity.ok().body(user);
//    }
//
//    //TODO implementeaza findByUsername
//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable(value = "username") String username) throws UserNotFoundException {
//        User user = userService.findByEmail(username);
//        //TODO arunca exc
//        return ResponseEntity.ok().body(user);
//    }
//
//    @PostMapping("/users")
//    public User addUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }
//
//    //TODO modifica exceptia
//    //TODO diff between ResponseEntity.ok(...) si .ok().body()
//    @PutMapping("/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userUpdates) throws Exception {
//        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("NU A FOST GASIT USERUL"));
//        user.setUsername(userUpdates.getUsername());
//        user.setPassword(userUpdates.getPassword());
//        user.setRoles(userUpdates.getRoles());
//        final User updatedUser = userRepository.save(user);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    @DeleteMapping("/users/{id}")
//    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
//        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("NU A FOST GASIT USERUL"));
//        userRepository.delete(user);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
//
//
//
//}
