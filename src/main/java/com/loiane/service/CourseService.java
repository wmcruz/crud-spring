package com.loiane.service;

import com.loiane.exception.RecordNotFoundException;
import com.loiane.model.Course;
import com.loiane.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(final CourseRepository courseRepository) {
        this.courseRepository = Objects.requireNonNull(courseRepository);
    }

    public List<Course> list() {
        return this.courseRepository.findAll();
    }

    public Course findById(@NotNull @Positive final Long id) {
        return this.courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create(@Valid final Course course) {
        return this.courseRepository.save(course);
    }

    public Course update(@NotNull @Positive final Long id, @Valid final Course course) {
        return this.courseRepository
                .findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return this.courseRepository.save(recordFound);
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive final Long id) {
        this.courseRepository.delete(
                this.courseRepository
                        .findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }
}