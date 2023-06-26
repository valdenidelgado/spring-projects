package com.course.dscatalog.services;

import com.course.dscatalog.dto.CategoryDTO;
import com.course.dscatalog.entities.Category;
import com.course.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;


    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }
}
