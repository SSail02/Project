package com.innovatus.studentrecords.dao;

import com.innovatus.studentrecords.config.DBConnection;
import com.innovatus.studentrecords.model.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** JDBC CRUD-read operations for student records. */
public class StudentDAO {
    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (roll_no, name, department, year, batch, semester, mobile, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (var connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setStudentValues(statement, student);
            statement.executeUpdate();
        }
    }

    public boolean isRollNumberExists(String rollNo) throws SQLException {
        try (var connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM students WHERE roll_no = ?")) {
            statement.setString(1, rollNo);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        return filterStudents("", "", "");
    }

    public List<Student> searchStudents(String searchText) throws SQLException {
        return filterStudents(searchText, "", "");
    }

    public List<Student> filterStudents(String searchText, String department, String year) throws SQLException {
        String sql = "SELECT * FROM students WHERE (? = '' OR roll_no LIKE ? OR name LIKE ?) "
                + "AND (? = '' OR department = ?) AND (? = '' OR year = ?) ORDER BY name, roll_no";
        try (var connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            String search = searchText == null ? "" : searchText.trim();
            String like = "%" + search + "%";
            statement.setString(1, search);
            statement.setString(2, like);
            statement.setString(3, like);
            statement.setString(4, department == null ? "" : department);
            statement.setString(5, department == null ? "" : department);
            statement.setString(6, year == null ? "" : year);
            statement.setString(7, year == null ? "" : year);
            try (ResultSet result = statement.executeQuery()) {
                List<Student> students = new ArrayList<>();
                while (result.next()) students.add(mapStudent(result));
                return students;
            }
        }
    }

    private void setStudentValues(PreparedStatement statement, Student student) throws SQLException {
        statement.setString(1, student.rollNo()); statement.setString(2, student.name());
        statement.setString(3, student.department()); statement.setString(4, student.year());
        statement.setString(5, student.batch()); statement.setString(6, student.semester());
        statement.setString(7, student.mobile()); statement.setString(8, student.email());
    }

    private Student mapStudent(ResultSet result) throws SQLException {
        return new Student(result.getInt("student_id"), result.getString("roll_no"), result.getString("name"),
                result.getString("department"), result.getString("year"), result.getString("batch"),
                result.getString("semester"), result.getString("mobile"), result.getString("email"));
    }
}
