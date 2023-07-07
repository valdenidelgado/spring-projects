package com.clean.adm.catalog.application.category.retrieve.list;

import com.clean.adm.catalog.application.UseCase;
import com.clean.adm.catalog.domain.pagination.Pagination;
import com.clean.adm.catalog.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
