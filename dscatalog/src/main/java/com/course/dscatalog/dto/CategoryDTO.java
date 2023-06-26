package com.course.dscatalog.dto;


import com.course.dscatalog.entities.Category;

import java.time.Instant;
import java.util.Date;

public class CategoryDTO {

    private Long id;
    private String name;

    private Date createdAt;

    public CategoryDTO() {
    }

    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.createdAt = entity.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }
}