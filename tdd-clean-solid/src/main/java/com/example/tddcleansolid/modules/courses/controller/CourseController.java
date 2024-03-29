package com.example.tddcleansolid.modules.courses.controller;

import com.example.tddcleansolid.modules.courses.entity.Course;
import com.example.tddcleansolid.modules.courses.repositories.CourseGateway;
import com.example.tddcleansolid.modules.courses.repositories.CourseJPARepository;
import com.example.tddcleansolid.modules.courses.repositories.ICourseRepository;
import com.example.tddcleansolid.modules.courses.services.CreateCourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    public CourseJPARepository repo;//only test for findall

    private final CreateCourseService createCourseService;

    public CourseController(CreateCourseService createCourseService) {
        this.createCourseService = createCourseService;
    }

    @GetMapping()
    public List<Course> findall() {
        return repo.findAll();
    }

    @PostMapping()
    public Course create(@RequestBody Course course) {
        return createCourseService.execute(course);
    }
}
