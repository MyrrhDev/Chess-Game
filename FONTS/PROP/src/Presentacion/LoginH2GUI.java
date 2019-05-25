package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class LoginH2GUI {
    static private JFrame jFrameLoginH2;

    private JButton loginButton;
    private JPanel loginH2Panel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton atrasButton;
    private JLabel backImage;
    private JButton registerButton;


    public LoginH2GUI(final String FEN, final int n, final int playerId1, final int playerId2, final String jugadorSeleccionado1, final String jugadorSeleccionado2) {
        jFrameLoginH2 = new JFrame("Logic - A Chess Game");
        jFrameLoginH2.setSize(460,740);
        jFrameLoginH2.setContentPane(loginH2Panel);
        jFrameLoginH2.setLocationRelativeTo(null);
        jFrameLoginH2.setResizable(false);

        jFrameLoginH2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //jFrameLoginH2.pack();
        jFrameLoginH2.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!textField1.getText().equals(ctrl_presentacion.getInstance().getNombreJugadorSesionH1())) {
                        boolean verificar = ctrl_presentacion.getInstance().verificarJugador(textField1.getText(), String.valueOf(passwordField1.getPassword()));
                        if (verificar) {
                            ctrl_presentacion.getInstance().setNombreJugadorSesionH2(textField1.getText());
                            iniciarPartida(FEN, n, playerId1, playerId2, jugadorSeleccionado1, jugadorSeleccionado2);
                            loginH2Panel.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No puedes jugar una partida contra ti mismo");
                    }
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(null, exp.getMessage());
                }
            }
        });

        atrasButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MenuPartidaGUI();
                jFrameLoginH2.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void iniciarPartida(final String FEN, final int n, final int playerId1, final int playerId2, final String jugadorSeleccionado1, final String jugadorSeleccionado2) {
        //creamos una partida con los datos seleccionados //@TODO: Arreglar la crida al controlador de domini, s'ha de cridar desde el controlador de presentació
        ctrl_presentacion.getInstance().crearPartida(FEN, n, playerId1, playerId2);
        //System.out.println(dificultadMenu.getSelectedItem() + " FEN:  " + FEN + " N: " + n + " playerId1: "+ playerId1 + " playerId2: " + playerId2 + " " + jugadores.getSelectedItem());
        JugarPartidaGUI partidaTablero = new JugarPartidaGUI(jugadorSeleccionado1, jugadorSeleccionado2, n);
        jFrameLoginH2.setVisible(false);
        System.out.println("fuera del set visible");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        backImage = new JLabel(new ImageIcon("./res/BackTake2.png"));
    }
}