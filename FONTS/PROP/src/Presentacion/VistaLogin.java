package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Domini.ctrl_dominio;


public class VistaLogin {
    static private JFrame jFrameLogin;

    private JButton loginButton;
    private JPanel loginPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;

    public VistaLogin() {
        jFrameLogin = new JFrame("Logic - A Chess Game");
        jFrameLogin.setContentPane(loginPanel);
        jFrameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameLogin.pack();
        jFrameLogin.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean verificar = ctrl_presentacion.getInstance().verificarJugador(textField1.getText(), textField2.getText());
                    if(verificar) {
                        new MenuGUI();
                        ctrl_presentacion.getInstance().setNombreJugadorSesionH1(textField1.getText());
                        jFrameLogin.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    }
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(null, exp.getMessage());
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrameLogin.setVisible(false);
                new RegisterGUI();
            }
        });
    }
}
