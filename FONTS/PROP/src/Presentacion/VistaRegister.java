package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaRegister {
    private JPanel registerPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;

    public VistaRegister() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "¡Ya estas registrado!");

            }
        });
    }
}
