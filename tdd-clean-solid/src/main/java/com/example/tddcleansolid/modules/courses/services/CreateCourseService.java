package com.example.tddcleansolid.modules.courses.services;

import com.example.tddcleansolid.modules.courses.entity.Course;
import com.example.tddcleansolid.modules.courses.repositories.ICourseRepository;
import org.springframework.stereotype.Service;

public class CreateCourseService {

    private final ICourseRepository repository;

    public CreateCourseService(ICourseRepository repository) {
        this.repository = repository;
    }

    public Course execute(Course course) {
        Course existedCourse = this.repository.findByName(course.getName());

        if (existedCourse != null) {
            throw new Error("Course already exists");
        }

        return this.repository.save(course);
    }
}
