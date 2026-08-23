package com.innovatus.studentrecords.utils;
import java.nio.charset.StandardCharsets; import java.security.*;
public final class PasswordUtil { private PasswordUtil(){} public static String hash(String value){ try { byte[] b=MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8)); StringBuilder s=new StringBuilder(); for(byte x:b)s.append(String.format("%02x",x)); return s.toString(); } catch(NoSuchAlgorithmException e){throw new IllegalStateException(e);} } }
