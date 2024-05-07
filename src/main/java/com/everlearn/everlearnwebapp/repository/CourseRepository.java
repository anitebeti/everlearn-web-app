package com.everlearn.everlearnwebapp.repository;

import com.everlearn.everlearnwebapp.entity.Course;
import com.everlearn.everlearnwebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByAuthor(User author);
}
