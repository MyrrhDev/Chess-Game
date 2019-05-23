package Presentacion;

import Domini.ctrl_dominio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegisterGUI {
    private JPanel registerPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;

    public RegisterGUI() {
        JFrame frame = new JFrame("Logic - A Chess Game");
        frame.setContentPane(registerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ctrl_presentacion.getInstance().registrarJugador(textField1.getText(), textField2.getText());
                    JOptionPane.showMessageDialog(null, "¡Ya estas registrado!");
                    new MenuGUI();
                    frame.setVisible(false);
                } catch (Exception e1) {
                    if(e1.getMessage().equals("El nombre de usario ya existe, elije otro"))
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con el miso username. Elige otro");
                    else e1.printStackTrace();
                }
            }
        });
    }
}
