package com.innovatus.studentrecords.config;
import java.sql.*;
public final class DBConnection { private DBConnection(){} public static Connection getConnection() throws SQLException { String url=System.getenv().getOrDefault("SRM_DB_URL", "jdbc:mysql://localhost:3306/student_record_management?useSSL=false&serverTimezone=UTC"); return DriverManager.getConnection(url, System.getenv().getOrDefault("SRM_DB_USER","root"), System.getenv().getOrDefault("SRM_DB_PASSWORD","")); } }
