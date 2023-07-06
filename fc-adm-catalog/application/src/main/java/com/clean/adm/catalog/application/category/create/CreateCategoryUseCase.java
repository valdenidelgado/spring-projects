package com.clean.adm.catalog.application.category.create;

import com.clean.adm.catalog.application.UseCase;
import com.clean.adm.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
        extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
