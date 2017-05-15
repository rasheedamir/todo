package io.as.todo.store.json;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public final class ApiValidationException extends RuntimeException
{
    private transient Set<ConstraintViolation<Command>> violations;

    public ApiValidationException(Set<ConstraintViolation<Command>> violations)
    {
        this.violations = violations;
    }
}
