package com.loiane.service;

import com.loiane.model.Course;
import com.loiane.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Optional<Course> findById(@NotNull @Positive final Long id) {
        return this.courseRepository.findById(id);
    }

    public Course create(@Valid final Course course) {
        return this.courseRepository.save(course);
    }

    public Optional<Course> update(@NotNull @Positive final Long id, @Valid final Course course) {
        return this.courseRepository
                .findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return this.courseRepository.save(recordFound);
                });
    }

    public boolean delete(@NotNull @Positive final Long id) {
        return this.courseRepository
                .findById(id)
                .map(recordFound -> {
                    this.courseRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}