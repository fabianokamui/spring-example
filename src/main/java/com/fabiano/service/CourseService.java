package com.fabiano.service;

import com.fabiano.dto.CourseDTO;
import com.fabiano.dto.CoursePageDTO;
import com.fabiano.dto.mapper.CourseMapper;
import com.fabiano.exception.RecordNotFoundException;
import com.fabiano.model.Course;
import com.fabiano.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    public CoursePageDTO listPage(int page, int size){
        Page<Course>pageCourse = courseRepository.findAll(PageRequest.of(page,size));
        List<CourseDTO>courses = pageCourse.get().map(courseMapper::toDTO).collect(Collectors.toList());
        return new CoursePageDTO(courses, pageCourse.getTotalElements(),pageCourse.getTotalPages());
    }

    public List<CourseDTO> listAll(){
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO createCourse (@Valid @NotNull CourseDTO courseDTO){
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTO)));
    }

    public CourseDTO findById(@NotNull @Positive Long id){
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(()->new RecordNotFoundException(id));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    Course course = courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
                    recordFound.getLessons().clear();
                    course.getLessons().forEach(recordFound.getLessons()::add);
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(()->new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id){
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(()->new RecordNotFoundException(id)));
    }

}
