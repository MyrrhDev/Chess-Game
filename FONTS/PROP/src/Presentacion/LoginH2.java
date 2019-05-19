package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Domini.ctrl_dominio;


public class LoginH2 {
    static private JFrame jFrameLoginH2;

    private JButton loginButton;
    private JPanel loginH2Panel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;

    private static ctrl_dominio controlador_dominio;



    public LoginH2() {
        jFrameLoginH2 = new JFrame("Logic - A Chess Game");
        //frame.setPreferredSize(new Dimension(900, 750));

        jFrameLoginH2.setContentPane(loginH2Panel);
        jFrameLoginH2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameLoginH2.pack();
        jFrameLoginH2.setVisible(true);
        controlador_dominio = ctrl_dominio.getInstance();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean verificar = controlador_dominio.verificarJugador(textField1.getText(), textField2.getText());
                    if(verificar) {
                        JOptionPane.showMessageDialog(null, "Yay");
                        new MenuGUI();
                        loginH2Panel.setVisible(false);

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
                jFrameLoginH2.setVisible(false);

                new RegisterGUI();

            }
        });
    }

    public static void main(String[] args) {
        controlador_dominio = ctrl_dominio.getInstance();


    }
}
