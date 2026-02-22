package com.loiane.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CoursePageDTO(
        @JsonProperty("courses")
        List<CourseDTO> courseDTOS,
        long totalElements,
        int totalPages) {
}
