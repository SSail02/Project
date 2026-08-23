package com.innovatus.studentrecords;
import com.innovatus.studentrecords.view.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
public final class App { private App(){} public static void main(String[] args){ SwingUtilities.invokeLater(() -> { try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {} new LoginFrame().setVisible(true); }); } }
