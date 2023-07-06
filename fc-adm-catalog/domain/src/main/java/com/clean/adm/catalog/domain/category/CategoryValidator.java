package com.clean.adm.catalog.domain.category;

import com.clean.adm.catalog.domain.validation.Error;
import com.clean.adm.catalog.domain.validation.ValidationHandler;
import com.clean.adm.catalog.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        if (category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
