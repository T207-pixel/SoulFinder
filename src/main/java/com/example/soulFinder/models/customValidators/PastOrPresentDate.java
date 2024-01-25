package com.example.soulFinder.models.customValidators;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = PastOrPresentDateValidator.class)
public @interface PastOrPresentDate {
    String message() default "Дата еще не наступила";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

