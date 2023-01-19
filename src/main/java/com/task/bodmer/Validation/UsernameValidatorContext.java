package com.task.bodmer.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidatorContext implements ConstraintValidator<Username, String> {
    private static final Pattern VALID_USERNAME =
            Pattern.compile("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return validate(s);
    }

    private boolean validate(String emailStr) {
        Matcher matcher = VALID_USERNAME.matcher(emailStr);
        return matcher.find();
    }
}
