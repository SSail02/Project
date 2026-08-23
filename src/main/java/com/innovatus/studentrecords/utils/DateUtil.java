package com.innovatus.studentrecords.utils;
import java.time.*; import java.time.format.*;
public final class DateUtil { private DateUtil(){} public static String format(LocalDateTime d){return d==null?"No uploads":d.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));} }
