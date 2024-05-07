package com.everlearn.everlearnwebapp.controller;

import com.everlearn.everlearnwebapp.entity.Course;
import com.everlearn.everlearnwebapp.exception.AlreadySubscribedException;
import com.everlearn.everlearnwebapp.model.CourseDTO;
import com.everlearn.everlearnwebapp.model.SubscribeRequest;
import com.everlearn.everlearnwebapp.model.UnsubscribedCourseDTO;
import com.everlearn.everlearnwebapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/author/addCourse", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity addCourse(
            @RequestPart("courseName") String courseName,
            @RequestPart("description") String description,
            @RequestPart("file") MultipartFile file,
            @RequestPart("authorEmail") String authorEmail) throws IOException {
        courseService.addCourse(courseName, description, file.getContentType(), file.getBytes(), authorEmail);
        return ResponseEntity.ok(courseName + " was successfully added");
    }

    @GetMapping("/author/courses")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<List<CourseDTO>> getAuthorCourseList(@RequestParam Long userId) {
        List<CourseDTO> files = courseService.getAuthorCourseList(userId).map(
                file -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/author/courses/")
                            .path(file.getId().toString())
                            .toUriString();

                    return new CourseDTO(
                            file.getCourseName(),
                            file.getDescription(),
                            fileDownloadUri,
                            file.getType(),
                            file.getData().length);
                }).collect(Collectors.toList());

        return ResponseEntity.ok(files);
    }

    @GetMapping("author/courses/{id}")
    public ResponseEntity<byte[]> getCourse(@PathVariable Long id) throws FileNotFoundException {
        Course course = courseService.getCourse(id);
        return ResponseEntity.ok()
         //       .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + course.getCourseName() + ".pdf\"")
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + course.getCourseName() + ".pdf\"")
                .header(HttpHeaders.CONTENT_TYPE, course.getType())
                .body(course.getData());
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity getAllCourses() {
        List<UnsubscribedCourseDTO> unsubscribedCourses = courseService.getAllCourses().stream().map(
                        course -> new UnsubscribedCourseDTO(course.getId(), course.getCourseName(), course.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(unsubscribedCourses);
    }

    @PostMapping("/subscribe")
    public ResponseEntity subscribeToCourse(@RequestBody SubscribeRequest request) throws AlreadySubscribedException, FileNotFoundException {
        courseService.subscribeUserToCourse(request.getUserId(), request.getCourseId());
        return ResponseEntity.ok("User successfully subscribed to course!");
    }
}
