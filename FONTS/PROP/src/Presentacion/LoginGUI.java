package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginGUI {
    static public JFrame jFrameLogin;

    private JButton loginButton;
    private JPanel loginPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;
    private JLabel BackImage;
    private JPasswordField passwordField1;
    public ImageIcon icon;

    public LoginGUI() {
        jFrameLogin = new JFrame("Logic - A Chess Game");
        jFrameLogin.setSize(440,733);
        jFrameLogin.setResizable(false);
        jFrameLogin.setContentPane(loginPanel);
        jFrameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameLogin.setLocationRelativeTo(null);
        jFrameLogin.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!String.valueOf(textField1.getText()).equals("") && !String.valueOf(passwordField1.getPassword()).equals("")) {
                    try {
                        boolean verificar = ctrl_presentacion.getInstance().verificarJugador(textField1.getText(), String.valueOf(passwordField1.getPassword()));
                        if (verificar) {
                            new MenuPrincipalGUI();
                            ctrl_presentacion.getInstance().setNombreJugadorSesionH1(textField1.getText());
                            jFrameLogin.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                        }
                    } catch (Exception exp) {
                        JOptionPane.showMessageDialog(null, exp.getMessage());
                    }
                }
                else {
                    if (String.valueOf(textField1.getText()).equals(""))
                        JOptionPane.showMessageDialog(null, "Introduce un nombre de usuario");
                    else JOptionPane.showMessageDialog(null, "Introduce una contraseña");
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

    private void createUIComponents() {
        BackImage = new JLabel(new ImageIcon("./res/BackTake2.png"));
    }


//
//    class BackgroundImageJFrame extends JFrame
//    {
//
//        public BackgroundImageJFrame()
//        {
//            setTitle("Background Color for JFrame");
//            //setSize(400,400);
//            //setLocationRelativeTo(null);
//            //setDefaultCloseOperation(EXIT_ON_CLOSE);
//            setVisible(true);
//
//            // Another way
//            setLayout(new BorderLayout());
//            setContentPane(new JLabel(new ImageIcon("./res/LoginBackground.png")));
//            setLayout(new FlowLayout());
//
//
//            //ImageIcon im = ImageIO.read(new File("./res/LoginBackground.png"));
//
////
////            l1=new JLabel("Here is a button");
////            b1=new JButton("I am a button");
////            add(l1);
////            add(b1);
////            // Just for refresh :) Not optional!
////            setSize(399,399);
////            setSize(400,400);
//        }
//
//        /*public static void main(String args[])
//        {
//            new BackgroundImageJFrame();
//        }*/
//    }

}