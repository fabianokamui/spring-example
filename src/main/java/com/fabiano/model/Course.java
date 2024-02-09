package com.fabiano.model;

import com.fabiano.enums.CategoryEnum;
import com.fabiano.enums.StatusEnum;
import com.fabiano.enums.converters.CategoryEnumConverter;
import com.fabiano.enums.converters.StatusEnumConverter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'inativo' WHERE id = ?")
@Where(clause = "status = 'ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 20, nullable = false)
    @Convert(converter = CategoryEnumConverter.class)
    private CategoryEnum category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusEnumConverter.class)
    private StatusEnum status = StatusEnum.ATIVO;

    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<Lesson>lessons = new ArrayList<>();

}
