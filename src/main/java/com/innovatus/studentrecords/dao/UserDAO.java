package com.innovatus.studentrecords.dao;

import com.innovatus.studentrecords.config.DBConnection;
import com.innovatus.studentrecords.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/** Database operations for the simple college login. */
public class UserDAO {
    public Optional<User> login(String username, String password) throws SQLException {
        String sql = "SELECT user_id, username, password FROM users WHERE username = ? AND password = ?";
        try (var connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username.trim());
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                return result.next()
                        ? Optional.of(new User(result.getInt("user_id"), result.getString("username"),
                                result.getString("password")))
                        : Optional.empty();
            }
        }
    }
}
