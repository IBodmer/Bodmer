package com.task.bodmer.Validation;

import com.task.bodmer.utils.PatternUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;

public class UsernameValidatorContext implements ConstraintValidator<Username, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return validate(s);
    }

    private boolean validate(String emailStr) {
        Matcher matcher = PatternUtils.VALID_USERNAME.matcher(emailStr);
        return matcher.find();
    }
}
