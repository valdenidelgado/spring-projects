package com.clean.adm.catalog.application.category.update;
import com.clean.adm.catalog.domain.category.Category;
import com.clean.adm.catalog.domain.category.CategoryGateway;
import com.clean.adm.catalog.domain.category.CategoryID;
import com.clean.adm.catalog.domain.exceptions.DomainException;
import com.clean.adm.catalog.domain.exceptions.NotFoundException;
import com.clean.adm.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {
        final var anId = CategoryID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = this.categoryGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        aCategory
                .update(aName, aDescription, isActive)
                .validate(notification);

        return notification.hasError() ? Left(notification) : update(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> update(final Category aCategory) {
        return Try(() -> this.categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> NotFoundException.with(Category.class, anId);
    }
}
