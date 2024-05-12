package com.everlearn.everlearnwebapp.model;

public record CourseDTO(
        Long id,
        String name,
        String description,
        String url,
        String type,
        long size
) {
}
