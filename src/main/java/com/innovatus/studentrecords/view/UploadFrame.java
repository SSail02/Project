package com.innovatus.studentrecords.view;

import com.innovatus.studentrecords.dao.StudentDAO;
import com.innovatus.studentrecords.model.Student;
import com.innovatus.studentrecords.service.ExcelService;
import com.innovatus.studentrecords.utils.DialogUtil;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Imports a validated Excel file into MySQL. */
public class UploadFrame extends BaseFrame {
    private final String username;
    public UploadFrame(String username) {
        super("Upload Student Records"); this.username = username;
        JButton choose = button("Select Excel File and Import"); JButton back = button("Back to Dashboard");
        JPanel panel = new JPanel(); panel.add(new JLabel("Required columns: Roll No, Name, Department, Year, Batch, Semester, Mobile, Email"));
        panel.add(choose); panel.add(back); add(panel, BorderLayout.CENTER);
        choose.addActionListener(event -> selectAndImport());
        back.addActionListener(event -> { dispose(); new DashboardFrame(username).setVisible(true); });
    }
    private void selectAndImport() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
        File file = chooser.getSelectedFile();
        try {
            List<Student> students = new ExcelService().readExcel(file); StudentDAO dao = new StudentDAO();
            for (Student student : students) if (dao.isRollNumberExists(student.rollNo()))
                throw new IllegalArgumentException("Roll number already exists in database: " + student.rollNo());
            for (Student student : students) dao.insertStudent(student);
            DialogUtil.info(this, students.size() + " student records imported successfully.");
        } catch (Exception exception) { DialogUtil.error(this, exception.getMessage()); }
    }
}
