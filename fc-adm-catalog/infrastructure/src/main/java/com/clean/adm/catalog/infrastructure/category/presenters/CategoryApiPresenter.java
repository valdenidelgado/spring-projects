package com.clean.adm.catalog.infrastructure.category.presenters;

import com.clean.adm.catalog.application.category.retrieve.get.CategoryOutput;
import com.clean.adm.catalog.application.category.retrieve.list.CategoryListOutput;
import com.clean.adm.catalog.infrastructure.category.models.CategoryListResponse;
import com.clean.adm.catalog.infrastructure.category.models.CategoryResponse;

public interface CategoryApiPresenter {

    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CategoryListResponse present(final CategoryListOutput output) {
        return new CategoryListResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
