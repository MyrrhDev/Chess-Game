package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin {
    private JButton loginButton;
    private JPanel loginPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;

    public VistaLogin() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Welcome!");

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ChessGame");
        frame.setContentPane(new VistaLogin().loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
