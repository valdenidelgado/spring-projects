package com.clean.adm.catalog.application;

import com.clean.adm.catalog.domain.category.Category;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}