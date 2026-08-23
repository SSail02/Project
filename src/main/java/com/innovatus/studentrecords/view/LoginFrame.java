package com.innovatus.studentrecords.view;

import com.innovatus.studentrecords.dao.UserDAO;
import com.innovatus.studentrecords.utils.DialogUtil;
import com.innovatus.studentrecords.utils.Validator;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** First screen: validates a college username and password. */
public class LoginFrame extends BaseFrame {
    public LoginFrame() {
        super("College Student Database - Login");
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(110, 190, 110, 190));
        JTextField username = new JTextField(); JPasswordField password = new JPasswordField();
        JButton login = button("Login");
        panel.add(new JLabel("Username:")); panel.add(username);
        panel.add(new JLabel("Password:")); panel.add(password);
        panel.add(new JLabel()); panel.add(login); add(panel);
        login.addActionListener(event -> login(username.getText(), new String(password.getPassword())));
    }
    private void login(String username, String password) {
        if (Validator.isBlank(username) || Validator.isBlank(password)) {
            DialogUtil.error(this, "Username and password cannot be empty."); return;
        }
        try {
            if (new UserDAO().login(username, password).isEmpty()) {
                DialogUtil.error(this, "Invalid username or password."); return;
            }
            dispose(); new DashboardFrame(username.trim()).setVisible(true);
        } catch (Exception exception) { DialogUtil.error(this, "Unable to login: " + exception.getMessage()); }
    }
}
