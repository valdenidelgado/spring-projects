package com.example.tddcleansolid.modules.courses.services;

import com.example.tddcleansolid.modules.courses.entity.Course;
import com.example.tddcleansolid.modules.courses.repositories.CourseJPARepository;
import com.example.tddcleansolid.modules.courses.repositories.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseService {

    private ICourseRepository repository;

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
