package com.clean.adm.catalog.application.category.update;
import com.clean.adm.catalog.application.UseCase;
import com.clean.adm.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
