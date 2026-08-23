package com.innovatus.studentrecords.view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

abstract class BaseFrame extends JFrame {
    BaseFrame(String title) {
        super(title); setDefaultCloseOperation(EXIT_ON_CLOSE); setSize(820, 540); setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
    }
    JButton button(String text) { return new JButton(text); }
}
