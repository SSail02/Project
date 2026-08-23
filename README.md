# College Student Record Management System

A Java 17 Swing/MVC application for department-scoped student record management. It imports `.xlsx`/`.xls` sheets, stores them in MySQL, exports filtered workbooks, and can mail OTPs and exports through SMTP.

## Prerequisites and setup

1. Install JDK 17, MySQL 8, Apache NetBeans 20+ and Maven (NetBeans can open `pom.xml` directly).
2. Run `database/student_record_management.sql` in MySQL Workbench.
3. Set `SRM_DB_URL`, `SRM_DB_USER`, and `SRM_DB_PASSWORD` (defaults are documented in `DBConnection`).
4. Set `SRM_SMTP_HOST`, `SRM_SMTP_PORT`, `SRM_SMTP_USERNAME`, and `SRM_SMTP_APP_PASSWORD` for email. Gmail requires an App Password.
5. Open this directory in NetBeans as a Maven project, then Run Project, or run `mvn clean verify exec:java`.

## Dependencies

Maven resolves MySQL Connector/J 8.4.0, Apache POI OOXML 5.2.5, and Jakarta Mail (Angus) 2.0.3. For a non-Maven NetBeans project, add the corresponding JARs and their Apache POI transitive dependencies to Libraries.

## Spreadsheet columns

The first row must contain exactly these named columns (case and surrounding whitespace are ignored): `Roll Number`, `Student Name`, `Department`, `Year`, `Batch`, `Semester`, `Mobile`, `Email`.
