package com.example.tddcleansolid.modules.courses.repositories;

import com.example.tddcleansolid.modules.courses.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseInMemoryRepository implements ICourseRepository {

    private List<Course> courses;

    public CourseInMemoryRepository() {
        this.courses = new ArrayList<>();
    }

    @Override
    public Course findByName(String name) {
        return this.courses.stream()
                .filter(course -> course.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Course save(Course course) {
        course.setId(java.util.UUID.randomUUID());
        this.courses.add(course);
        return course;
    }
}
