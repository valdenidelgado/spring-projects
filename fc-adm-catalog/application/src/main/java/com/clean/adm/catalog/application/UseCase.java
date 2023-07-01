package com.clean.adm.catalog.application;

import com.clean.adm.catalog.domain.category.Category;

public class UseCase {
    public Category execute() {
        return new Category();
    }
}