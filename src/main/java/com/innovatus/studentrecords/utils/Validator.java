package com.innovatus.studentrecords.utils;
import java.util.regex.Pattern;
public final class Validator { private Validator(){} private static final Pattern EMAIL=Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"); public static boolean email(String s){return s!=null&&EMAIL.matcher(s.trim()).matches();} public static boolean blank(String s){return s==null||s.trim().isEmpty();} public static boolean password(String s){return s!=null&&s.length()>=8;} public static boolean year(String s){return s!=null&&s.trim().matches("(?i).*(year|first|second|third|fourth|[1-9]).*");} }
