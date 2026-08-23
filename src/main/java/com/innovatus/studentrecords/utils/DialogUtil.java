package com.innovatus.studentrecords.utils;
import javax.swing.*; import java.awt.*;
public final class DialogUtil { private DialogUtil(){} public static void info(Component p,String s){JOptionPane.showMessageDialog(p,s,"Student Records",JOptionPane.INFORMATION_MESSAGE);} public static void error(Component p,String s){JOptionPane.showMessageDialog(p,s,"Student Records",JOptionPane.ERROR_MESSAGE);} }
