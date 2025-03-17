package com.zerobase.weather.utils;

import java.util.regex.Pattern;

public class DateValidator {
    private static final String DATE_REGEX = "^(?!0)\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\\d|3[01])$";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);

    public static boolean isValidDate(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }
}
