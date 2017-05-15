package io.as.todo.store.json;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * A marker interface to define a runtime exception to hold validations
 */
public interface Command
{
    default void validate()
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Command>> violations = validator.validate(this);

        if(!violations.isEmpty())
        {
            throw new ApiValidationException(violations);
        }
    }
}
