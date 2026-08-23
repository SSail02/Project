package com.innovatus.studentrecords.utils;

import java.util.regex.Pattern;

/** Small reusable validation methods for Swing forms and Excel rows. */
public final class Validator {
    private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private Validator() { }
    public static boolean isBlank(String value) { return value == null || value.trim().isEmpty(); }
    public static boolean isValidEmail(String value) { return !isBlank(value) && EMAIL.matcher(value.trim()).matches(); }
}
