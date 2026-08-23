package com.innovatus.studentrecords.view;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Minimal post-login navigation screen. */
public class DashboardFrame extends BaseFrame {
    public DashboardFrame(String username) {
        super("College Student Database - Dashboard");
        JPanel panel = new JPanel(new GridLayout(0, 1, 12, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(90, 220, 90, 220));
        JLabel welcome = new JLabel("Welcome " + username, JLabel.CENTER);
        JButton upload = button("Upload Records"), view = button("View Records"), logout = button("Logout");
        panel.add(welcome); panel.add(upload); panel.add(view); panel.add(logout); add(panel);
        upload.addActionListener(event -> { dispose(); new UploadFrame(username).setVisible(true); });
        view.addActionListener(event -> { dispose(); new ViewRecordsFrame(username).setVisible(true); });
        logout.addActionListener(event -> { dispose(); new LoginFrame().setVisible(true); });
    }
}
