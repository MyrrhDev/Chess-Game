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
        mainMenuFrame.setContentPane(menuPanel);
        mainMenuFrame.setSize(844, 650);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
        img.setIcon(new ImageIcon(this.getClass().getResource("/res/tableroMenu.png")));
        menuLabel.setFont(new Font(menuLabel.getName(), Font.PLAIN, 30));
        newProblemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(false);
                new ProblemaGUI();
            }
        });
        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(false);
                new RankingGUI();
            }
        });
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(false);
                new MenuPartidaGUI();
            }
        });

    }
}