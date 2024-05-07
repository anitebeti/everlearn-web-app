package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.Course;
import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.exception.AlreadySubscribedException;
import com.everlearn.everlearnwebapp.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserService userService;

    public void addCourse(String courseName, String description, String type, byte[] data, String authorEmail) {
        User author = userService.findByEmail(authorEmail);
        Course course = new Course(courseName, description, type, data, author);
        courseRepository.save(course);
    }

    @Transactional
    public Stream<Course> getAuthorCourseList(Long userId) {
        User author = userService.findById(userId);
        return courseRepository.findAllByAuthor(author).stream();
    }

    public Course getCourse(Long courseId) throws FileNotFoundException {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new FileNotFoundException();
        }
        return course.get();
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void subscribeUserToCourse(Long userId, Long courseId) throws FileNotFoundException, AlreadySubscribedException {
        User user = userService.findById(userId);
        Course course = getCourse(courseId);
        if (user.getSubscribedCourses().contains(course)) {
            throw new AlreadySubscribedException();
        }
        user.getSubscribedCourses().add(course);
        userService.addUser(user);

    }
}
