package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI {
    private JPanel registerPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;
    private JLabel backImage;
    private JButton backToLoginButton;
    private JPasswordField passwordField1;

    public RegisterGUI() {
        JFrame frame = new JFrame("Logic - A Chess Game");
        frame.setContentPane(registerPanel);
        frame.setSize(440,733);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(textField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No has introducido un usuario");
                    }
                    else if(passwordField1.getPassword().length == 0) {
                        JOptionPane.showMessageDialog(null, "No has introducido una contraseña");
                    } else {
                        ctrl_presentacion.getInstance().registrarJugador(textField1.getText(), String.valueOf(passwordField1.getPassword()));
                        ctrl_presentacion.getInstance().setNombreJugadorSesionH1(textField1.getText());
                        JOptionPane.showMessageDialog(null, "¡Ya estas registrado!");
                        frame.setVisible(false);
                        new MenuPrincipalGUI();
                    }
                } catch (Exception e1) {
                    if(e1.getMessage().equals("El nombre de usario ya existe, elije otro"))
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con el miso username. Elige otro");
                    else e1.printStackTrace();
                }
            }
        });
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new LoginGUI();
            }
        });
    }

    private void createUIComponents() {
        backImage = new JLabel(new ImageIcon("./res/BackTake2.png"));
    }
}