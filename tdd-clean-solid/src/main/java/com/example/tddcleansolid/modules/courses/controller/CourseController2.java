package com.example.tddcleansolid.modules.courses.controller;

import com.example.tddcleansolid.modules.courses.entity.Course;
import com.example.tddcleansolid.modules.courses.repositories.CourseJPARepository;
import com.example.tddcleansolid.modules.courses.repositories.ICourseRepository;
import com.example.tddcleansolid.modules.courses.services.CreateCourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/courses2")
public class CourseController2 {

    @Autowired
    public CourseJPARepository repo; //only test for findall

    @Autowired
    public ICourseRepository repository;


    @GetMapping()
    public List<Course> findall() {
        return repo.findAll();
    }

    @PostMapping()
    public Course create(@RequestBody Course course) {
        var createCourseService = new CreateCourseService(repository);

        log.info("OLHA O QUE TA VINDO {}", course.getName());
        return createCourseService.execute(course);

    }
}
