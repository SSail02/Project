package com.innovatus.studentrecords.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Creates JDBC connections using environment variables or local MySQL defaults. */
public final class DBConnection {
    private DBConnection() { }

    public static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault("SRM_DB_URL",
                "jdbc:mysql://localhost:3306/student_database?useSSL=false&serverTimezone=UTC");
        return DriverManager.getConnection(url, System.getenv().getOrDefault("SRM_DB_USER", "root"),
                System.getenv().getOrDefault("SRM_DB_PASSWORD", ""));
    }
}
