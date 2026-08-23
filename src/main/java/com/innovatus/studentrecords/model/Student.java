package com.innovatus.studentrecords.model;

/** One row from the student database or imported spreadsheet. */
public record Student(int id, String rollNo, String name, String department, String year,
                      String batch, String semester, String mobile, String email) { }
