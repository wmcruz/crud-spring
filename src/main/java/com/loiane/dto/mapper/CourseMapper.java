package com.loiane.dto.mapper;

import com.loiane.dto.CourseDTO;
import com.loiane.enums.Category;
import com.loiane.model.Course;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CourseMapper {

    public CourseDTO toDTO(final Course course) {
        if (course == null) return null;

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(final CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.id() != null) course.setId(courseDTO.id());

        course.setName(courseDTO.name());
        course.setCategory(this.convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(final String value) {
        if (Objects.isNull(value)) return null;

        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };
    }
}