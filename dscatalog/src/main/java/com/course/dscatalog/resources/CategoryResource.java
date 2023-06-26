package com.course.dscatalog.resources;

import com.course.dscatalog.dto.CategoryDTO;
import com.course.dscatalog.entities.Category;
import com.course.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        category = service.create(category);
        return ResponseEntity.ok().body(category);
    }
}
