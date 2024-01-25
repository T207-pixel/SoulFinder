package com.example.soulFinder.models.customValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PastOrPresentDateValidator implements ConstraintValidator<PastOrPresentDate, Date> {
    @Override
    public void initialize(PastOrPresentDate pastOrPresentDate) {}

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return date == null || LocalDate.now().isEqual(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) || LocalDate.now().isAfter(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
