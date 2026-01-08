package com.loiane.dto.mapper;

import com.loiane.dto.CourseDTO;
import com.loiane.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(final Course course) {
        if (course == null) return null;

        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

    public Course toEntity(final CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.id() != null) course.setId(courseDTO.id());

        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        course.setStatus("Ativo");
        return course;
    }
}