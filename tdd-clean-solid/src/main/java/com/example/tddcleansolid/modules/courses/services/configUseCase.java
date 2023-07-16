package com.example.tddcleansolid.modules.courses.services;

import com.example.tddcleansolid.modules.courses.repositories.ICourseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configUseCase {

    private final ICourseRepository repository;

    public configUseCase(ICourseRepository repository) {
        this.repository = repository;
    }

    @Bean
    public CreateCourseService createCourseService() {
        return new CreateCourseService(repository);
    }
}
