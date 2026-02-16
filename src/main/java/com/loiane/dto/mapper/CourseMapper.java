package com.loiane.dto.mapper;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.LessonDTO;
import com.loiane.enums.Category;
import com.loiane.model.Course;
import com.loiane.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(final Course course) {
        if (course == null) return null;

        final var lessonDTOS = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessonDTOS);
    }

    public Course toEntity(final CourseDTO courseDTO) {
        var course = new Course();
        if (courseDTO.id() != null) course.setId(courseDTO.id());

        course.setName(courseDTO.name());
        course.setCategory(this.convertCategoryValue(courseDTO.category()));

        final var lessons = courseDTO.lessons()
                .stream()
                .map(lessonDTO -> {
                    var lesson = new Lesson();
                    lesson.setId(lessonDTO.id());
                    lesson.setName(lessonDTO.name());
                    lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
                    lesson.setCourse(course);
                    return lesson;
                })
                .collect(Collectors.toList());

        course.setLessons(lessons);
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