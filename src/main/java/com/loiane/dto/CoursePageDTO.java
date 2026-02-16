package com.loiane.dto;

import java.util.List;

public record CoursePageDTO(
        List<CourseDTO> courseDTOS,
        long totalElements,
        int totalPages) {
}
