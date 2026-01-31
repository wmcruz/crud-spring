package com.loiane.dto.mapper;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.LessonDTO;
import com.loiane.enums.Category;
import com.loiane.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(final Course course) {
        if (course == null) return null;

        final List<LessonDTO> lessonDTOS = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessonDTOS);
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