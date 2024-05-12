package com.everlearn.everlearnwebapp.model;

import java.util.List;

public record GetAuthorCourseListResponse(
        CourseDTO course,
        List<Long> userIds
) {
}
