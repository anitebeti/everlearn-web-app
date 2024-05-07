package com.everlearn.everlearnwebapp.model;

public record CourseDTO(
        String name,
        String description,
        String url,
        String type,
        long size
) {
}
