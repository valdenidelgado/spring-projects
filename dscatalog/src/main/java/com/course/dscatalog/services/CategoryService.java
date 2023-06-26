package com.course.dscatalog.services;

import com.course.dscatalog.entities.Category;
import com.course.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;


    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category create(Category category) {
        return repository.save(category);
    }
}
