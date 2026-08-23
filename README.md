# College Student Database Management System

A simple Java 17 diploma project built with Java Swing, JDBC, MySQL, Apache POI, Maven, OOP, event handling, file handling, and validation. It intentionally has no email, OTP, cloud, export, download, signup, or edit feature.

## Features

- Login with a college username and password.
- Import student records from an `.xlsx` or `.xls` file into MySQL.
- Validate the required columns, empty cells, email addresses, duplicate roll numbers inside the file, and duplicate roll numbers already in the database.
- Display records in a non-editable `JTable`.
- Search by roll number or student name, and filter by department and year.
- Log out to return to the login screen.

## Setup in NetBeans / MySQL Workbench

1. Run `database/student_record_management.sql` in MySQL Workbench.
2. The script creates database `student_database` and a demonstration account: username `admin`, password `admin123`.
3. If MySQL is not on the default local root connection, configure `SRM_DB_URL`, `SRM_DB_USER`, and `SRM_DB_PASSWORD`.
4. Open `pom.xml` as a Maven project in Apache NetBeans and run it, or execute `mvn clean verify exec:java`.

## Excel format

The first row must contain the following columns in the exact order (capitalization is ignored):

| Roll No | Name | Department | Year | Batch | Semester | Mobile | Email |
| --- | --- | --- | --- | --- | --- | --- | --- |

Example year values are `First Year`, `Second Year`, and `Third Year` so they work with the supplied filter choices.

## Package structure

- `config` — JDBC database connection.
- `model` — `User` and `Student` data objects.
- `dao` — SQL login, insert, lookup, search, and filter operations.
- `service` — Apache POI Excel reading and validation.
- `view` — Swing login, dashboard, upload, and view-records screens.
- `utils` — reusable validation and dialog helpers.
