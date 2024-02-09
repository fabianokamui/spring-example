package com.fabiano.dto.mapper;

import com.fabiano.dto.CourseDTO;
import com.fabiano.dto.LessonDTO;
import com.fabiano.enums.CategoryEnum;
import com.fabiano.model.Course;
import com.fabiano.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        if(course == null){
            return null;
        }
        List<LessonDTO>lessonDTOList = course.getLessons().stream()
                .map(lesson->new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());
        return new CourseDTO(course.getId(),course.getName(),course.getCategory().getValue(),lessonDTOList);
    }

    public Course toEntity(CourseDTO courseDTO){

        if(courseDTO == null){
            return null;
        }

        Course course = new Course();
        if(courseDTO.id() != null){
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));

        List<Lesson> lessons = courseDTO.lessons().stream().map(lessonDTO -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
            lesson.setCourse(course);
            return lesson;
        }).collect(Collectors.toList());
        course.setLessons(lessons);
        return course;
    }

    public CategoryEnum convertCategoryValue(String value) {
        if(value==null){
            return null;
        }
        return switch (value){
            case "back-end" -> CategoryEnum.BACK_END;
            case "front-end" -> CategoryEnum.FRONT_END;
            default -> throw new RuntimeException("valor invalido:" + value);
        };
    }
}
