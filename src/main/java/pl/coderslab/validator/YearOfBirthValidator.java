package pl.coderslab.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class YearOfBirthValidator implements ConstraintValidator<YearOfBirth,String> {
    @Override
    public void initialize(YearOfBirth yearOfBirth) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
