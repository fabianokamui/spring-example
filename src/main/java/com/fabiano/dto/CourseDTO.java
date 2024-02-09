package com.fabiano.dto;

import com.fabiano.enums.CategoryEnum;
import com.fabiano.enums.validation.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        Long id,
        @NotBlank @NotNull @Length(min = 5, max = 100) String name,
        @NotNull @Length(min = 5, max = 10) @ValueOfEnum(enumClass = CategoryEnum.class) String category,
        @NotNull @Valid List<LessonDTO> lessons) {
}
