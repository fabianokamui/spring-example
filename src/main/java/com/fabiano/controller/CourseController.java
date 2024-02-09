package com.fabiano.controller;

import com.fabiano.dto.CourseDTO;
import com.fabiano.dto.CoursePageDTO;
import com.fabiano.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/listPage")
    public CoursePageDTO listPage(@RequestParam(defaultValue = "0") @PositiveOrZero int page, @RequestParam(defaultValue = "10") @Positive @Max(20) int size){
        return courseService.listPage(page,size);
    }
    @GetMapping("/listAll")
    public List<CourseDTO> listAll(){
        return courseService.listAll();
    }

    @PostMapping("/createCourse")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create (@RequestBody @Valid CourseDTO courseDTO){
        courseService.createCourse(courseDTO);
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id){
        return courseService.findById(id);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody  @Valid CourseDTO courseDTO){
        return courseService.update(id,courseDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull @Positive Long id){
        courseService.delete(id);
    }



}
