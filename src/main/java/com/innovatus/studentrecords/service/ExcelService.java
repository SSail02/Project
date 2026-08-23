package com.innovatus.studentrecords.service;

import com.innovatus.studentrecords.model.Student;
import com.innovatus.studentrecords.utils.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/** Reads and validates the required eight-column student Excel sheet. */
public class ExcelService {
    private static final List<String> HEADERS = List.of("Roll No", "Name", "Department", "Year", "Batch", "Semester", "Mobile", "Email");

    public List<Student> readExcel(File file) throws Exception {
        if (file == null || !file.getName().matches("(?i).*\\.(xlsx|xls)$"))
            throw new IllegalArgumentException("Please select an Excel (.xlsx or .xls) file.");
        try (FileInputStream input = new FileInputStream(file); Workbook workbook = WorkbookFactory.create(input)) {
            Sheet sheet = workbook.getSheetAt(0); DataFormatter formatter = new DataFormatter();
            validateHeaders(sheet.getRow(0), formatter);
            List<Student> students = new ArrayList<>(); Set<String> rollNumbers = new HashSet<>();
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                String[] values = new String[8];
                for (int column = 0; column < values.length; column++) values[column] = formatter.formatCellValue(row.getCell(column)).trim();
                if (allBlank(values)) continue;
                if (hasBlank(values)) throw new IllegalArgumentException("Empty cell found in row " + (rowIndex + 1) + ".");
                if (!Validator.isValidEmail(values[7])) throw new IllegalArgumentException("Invalid email in row " + (rowIndex + 1) + ".");
                if (!rollNumbers.add(values[0])) throw new IllegalArgumentException("Duplicate roll number in Excel: " + values[0]);
                students.add(new Student(0, values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]));
            }
            if (students.isEmpty()) throw new IllegalArgumentException("The Excel sheet has no student records.");
            return students;
        }
    }

    private void validateHeaders(Row header, DataFormatter formatter) {
        if (header == null) throw new IllegalArgumentException("Excel header row is missing.");
        for (int column = 0; column < HEADERS.size(); column++) {
            String actual = formatter.formatCellValue(header.getCell(column)).trim();
            if (!HEADERS.get(column).equalsIgnoreCase(actual))
                throw new IllegalArgumentException("Column " + (column + 1) + " must be \"" + HEADERS.get(column) + "\".");
        }
    }
    private boolean allBlank(String[] values) { for (String value : values) if (!value.isEmpty()) return false; return true; }
    private boolean hasBlank(String[] values) { for (String value : values) if (Validator.isBlank(value)) return true; return false; }
}
