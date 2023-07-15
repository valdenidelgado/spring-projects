package com.example.tddcleansolid.services;

import com.example.tddcleansolid.modules.courses.entity.Course;
import com.example.tddcleansolid.modules.courses.repositories.CourseInMemoryRepository;
import com.example.tddcleansolid.modules.courses.services.CreateCourseService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateCourseServiceTest {

    @Test
    public void should_be_able_to_create_a_new_course() {
        Course course = new Course();
        course.setDescription("Aprenda sobre Clean Architecture com Uncle Bob");
        course.setName("Clean Architecture");
        course.setWorkload(100);

        CourseInMemoryRepository repository = new CourseInMemoryRepository();

        CreateCourseService createCourseService = new CreateCourseService(repository);
        Course createdCourse = createCourseService.execute(course);

        assertNotNull(createdCourse.getId());
        assertEquals(course.getDescription(), createdCourse.getDescription());
        assertEquals(course.getName(), createdCourse.getName());
        assertEquals(course.getWorkload(), createdCourse.getWorkload());
    }
}
