package com.example.tddcleansolid.modules.courses.controller;

import com.example.tddcleansolid.modules.courses.entity.Course;
import com.example.tddcleansolid.modules.courses.services.CreateCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CreateCourseService createCourseService;

    @PostMapping("/")
    public Course create(Course course) {
        return createCourseService.execute(course);
    }
}
