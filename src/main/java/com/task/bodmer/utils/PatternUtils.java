package com.task.bodmer.utils;

import java.util.regex.Pattern;

public class PatternUtils {
    public static final Pattern VALID_USERNAME =
            Pattern.compile("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_EMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
}
