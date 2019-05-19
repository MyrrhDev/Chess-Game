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

    private static ctrl_dominio controlador_dominio;



    public VistaLogin() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean verificar = controlador_dominio.verificarJugador(textField1.getText(), textField2.getText());
                    if(verificar) {
                        JOptionPane.showMessageDialog(null, "Yay");
                        new MenuGUI();
                        loginPanel.setVisible(false);

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

    public static void main(String[] args) {
        controlador_dominio = ctrl_dominio.getInstance();

        jFrameLogin = new JFrame("Logic - A Chess Game");
        jFrameLogin.setContentPane(new VistaLogin().loginPanel);
        jFrameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameLogin.pack();
        jFrameLogin.setVisible(true);
    }
}
