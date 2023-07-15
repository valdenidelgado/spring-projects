package com.example.tddcleansolid.modules.courses.repositories;

import com.example.tddcleansolid.modules.courses.entity.Course;

public interface ICourseRepository {

    public Course findByName(String name);
    public Course save(Course course);
}
