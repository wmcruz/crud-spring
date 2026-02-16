package com.loiane.service;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.CoursePageDTO;
import com.loiane.dto.mapper.CourseMapper;
import com.loiane.exception.RecordNotFoundException;
import com.loiane.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

    public CoursePageDTO list(final int pageNumber, final int pageSize) {
        final var page = this.courseRepository.findAll(PageRequest.of(pageNumber, pageSize));
        final var courses = page
                .get()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());

        return new CoursePageDTO(courses, page.getTotalElements(), page.getTotalPages());
    }

    public CourseDTO findById(@NotNull @Positive final Long id) {
        return this.courseRepository
                .findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull final CourseDTO courseDTO) {
        return courseMapper.toDTO(this.courseRepository.save(this.courseMapper.toEntity(courseDTO)));
    }

    public CourseDTO update(@NotNull @Positive final Long id, @Valid @NotNull final CourseDTO courseDTO) {
        return this.courseRepository
                .findById(id)
                .map(recordFound -> {
                    var course = this.courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(this.courseMapper.convertCategoryValue(courseDTO.category()));
                    recordFound.getLessons().clear();
                    course.getLessons()
                            .forEach(recordFound.getLessons()::add);
                    return this.courseMapper.toDTO(this.courseRepository.save(recordFound));
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