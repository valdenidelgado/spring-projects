package com.example.tddcleansolid.modules.courses.repositories;

import com.example.tddcleansolid.modules.courses.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseJPARepository extends JpaRepository<Course, UUID> {

    Course findByName(String name);
}
