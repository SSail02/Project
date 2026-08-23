CREATE DATABASE IF NOT EXISTS student_database CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE student_database;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    roll_no VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    department VARCHAR(100) NOT NULL,
    year VARCHAR(30) NOT NULL,
    batch VARCHAR(30) NOT NULL,
    semester VARCHAR(30) NOT NULL,
    mobile VARCHAR(25) NOT NULL,
    email VARCHAR(254) NOT NULL
);

-- Default college account for first login. Change it after demonstrating the project.
INSERT INTO users (username, password) VALUES ('admin', 'admin123');
CREATE INDEX idx_students_filters ON students(department, year, roll_no, name);
