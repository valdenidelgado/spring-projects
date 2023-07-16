package com.example.tddcleansolid.modules.courses.repositories;

import com.example.tddcleansolid.modules.courses.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseGateway implements ICourseRepository {

    @Autowired
    private CourseJPARepository repository;

    @Override
    public Course findByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public Course save(Course course) {
        return this.repository.save(course);
    }
}
