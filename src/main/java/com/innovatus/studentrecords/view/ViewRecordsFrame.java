package com.innovatus.studentrecords.view;

import com.innovatus.studentrecords.dao.StudentDAO;
import com.innovatus.studentrecords.model.Student;
import com.innovatus.studentrecords.utils.DialogUtil;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/** Displays MySQL student records with search, department, and year filters. */
public class ViewRecordsFrame extends BaseFrame {
    private final String username;
    private final JTextField search = new JTextField(16);
    private final JComboBox<String> department = new JComboBox<>(new String[]{"All Departments", "Computer Engineering", "Information Technology", "Mechanical Engineering", "Civil Engineering", "Electrical Engineering"});
    private final JComboBox<String> year = new JComboBox<>(new String[]{"All Years", "First Year", "Second Year", "Third Year"});
    private final DefaultTableModel model = new DefaultTableModel(new String[]{"Roll No", "Name", "Department", "Year", "Batch", "Semester", "Mobile", "Email"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    public ViewRecordsFrame(String username) {
        super("View Student Records"); this.username = username;
        JButton refresh = button("Refresh"); JButton back = button("Back to Dashboard");
        JPanel filters = new JPanel(); filters.add(new JLabel("Search roll no / name:")); filters.add(search);
        filters.add(new JLabel("Department:")); filters.add(department); filters.add(new JLabel("Year:")); filters.add(year);
        filters.add(refresh); filters.add(back); add(filters, BorderLayout.NORTH); add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        refresh.addActionListener(event -> loadStudents()); back.addActionListener(event -> { dispose(); new DashboardFrame(username).setVisible(true); });
        loadStudents();
    }
    private void loadStudents() {
        try {
            String selectedDepartment = department.getSelectedIndex() == 0 ? "" : (String) department.getSelectedItem();
            String selectedYear = year.getSelectedIndex() == 0 ? "" : (String) year.getSelectedItem();
            model.setRowCount(0);
            for (Student student : new StudentDAO().filterStudents(search.getText(), selectedDepartment, selectedYear))
                model.addRow(new Object[]{student.rollNo(), student.name(), student.department(), student.year(), student.batch(), student.semester(), student.mobile(), student.email()});
        } catch (Exception exception) { DialogUtil.error(this, "Unable to fetch records: " + exception.getMessage()); }
    }
}
