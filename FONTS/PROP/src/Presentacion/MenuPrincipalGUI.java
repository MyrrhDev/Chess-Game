package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalGUI {
    private JPanel menuPanel;
    private JButton rankingButton;
    static private JFrame mainMenuFrame;
    private JButton jugarButton;
    private JPanel tabImg;
    private JButton newProblemaButton;
    private JPanel jpButtons;
    private JLabel img;
    private JLabel menuLabel;

    public MenuPrincipalGUI() {
        mainMenuFrame = new JFrame("Logic - A Chess Game");
        //mainMenuFrame.setPreferredSize(new Dimension(844, 650));
        mainMenuFrame.setContentPane(menuPanel);
        mainMenuFrame.setSize(844, 650);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
        img.setIcon(new ImageIcon("./res/tableroMenu.png"));
        menuLabel.setFont(new Font(menuLabel.getName(), Font.PLAIN, 30));
        newProblemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProblemaGUI();
                mainMenuFrame.setVisible(false);
            }
        });
        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RankingGUI();
                mainMenuFrame.setVisible(false);
            }
        });
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPartidaGUI();
                mainMenuFrame.setVisible(false);
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuPrincipalGUI();
            }
        });
    }
}