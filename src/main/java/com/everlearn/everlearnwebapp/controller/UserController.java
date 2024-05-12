package com.everlearn.everlearnwebapp.controller;

import com.everlearn.everlearnwebapp.model.EditInfoRequest;
import com.everlearn.everlearnwebapp.model.RoleChangeRequest;
import com.everlearn.everlearnwebapp.model.UserDTO;
import com.everlearn.everlearnwebapp.service.CourseService;
import com.everlearn.everlearnwebapp.service.PhotoService;
import com.everlearn.everlearnwebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PhotoService photoService;

    @GetMapping("/")
    public ResponseEntity accessHomePage() {
        return ResponseEntity.ok("User accesed homepage successfully");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity accessAdminDashboard() {
        //vezi ce exc ce iti arunca, prinde-o si arunca ceva de-al tau cu global exception handler
        List<UserDTO> users = userService.getAllUsers().stream().map(user -> new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRoles())).collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PutMapping("/admin/changeRoles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity changeUserRoles(@RequestBody RoleChangeRequest request) {
        userService.changeUserRoles(request.getEmail(), request.getRoles());
        return ResponseEntity.ok(
                request.getRoles().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "))
                        + " were added to "
                        + request.getEmail());
    }

    @PutMapping("/user/editInfo")
    public ResponseEntity editInfo(@RequestBody EditInfoRequest request) {
        userService.editInfo(
                request.id(),
                request.firstName(),
                request.lastName(),
                request.email(),
                request.phoneNumber());
        return ResponseEntity.ok("Info successfully updated");
    }

    @PostMapping(value = "/user/addPhoto", consumes = "multipart/form-data")
    public ResponseEntity addPhoto(
            @RequestPart("userId") String userId,
            @RequestPart("photo") MultipartFile photo) throws IOException {
        photoService.addPhoto(Long.valueOf(userId), photo.getBytes());
        return ResponseEntity.ok("Photo was successfully added");
    }

    @GetMapping("/user/photo/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
        byte[] photo = photoService.getPhoto(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"poza.jpeg\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(photo);
    }
}
