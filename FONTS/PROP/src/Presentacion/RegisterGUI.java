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

    private static ctrl_dominio controlador_dominio;


    public RegisterGUI() {
        JFrame frame = new JFrame("Logic - A Chess Game");
        //frame.setPreferredSize(new Dimension(900, 750));

        frame.setContentPane(registerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        controlador_dominio = ctrl_dominio.getInstance();

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador_dominio.registrarJugador(textField1.getText(), textField2.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "¡Ya estas registrado!");
                new MenuGUI();
                frame.setVisible(false);
            }
        });
    }

    public static void main(String[] args)
    {
        controlador_dominio = ctrl_dominio.getInstance();


    }
}
