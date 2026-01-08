package com.loiane.service;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.mapper.CourseMapper;
import com.loiane.exception.RecordNotFoundException;
import com.loiane.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(final CourseRepository courseRepository, final CourseMapper courseMapper) {
        this.courseRepository = Objects.requireNonNull(courseRepository);
        this.courseMapper = Objects.requireNonNull(courseMapper);
    }

    public List<CourseDTO> list() {
        return this.courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@NotNull @Positive final Long id) {
        return this.courseRepository
                .findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull final CourseDTO course) {
        return courseMapper.toDTO(this.courseRepository.save(this.courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive final Long id, @Valid final CourseDTO course) {
        return this.courseRepository
                .findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(course.category());
                    return this.courseRepository.save(recordFound);
                })
                .map(courseMapper::toDTO)
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