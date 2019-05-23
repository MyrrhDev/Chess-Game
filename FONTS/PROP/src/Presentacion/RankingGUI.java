package Presentacion;

import Domini.ctrl_dominio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RankingGUI {
    private JTable rankingTable;
    private JPanel panel1;
    private JButton atrasButton;
    private JScrollPane scrollPane1;
    JFrame frame;

    RankingGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Logic: Entorno de resolución de problemas de ajedrez");
        frame.setContentPane(panel1);
        frame.setSize(600, 300);
        frame.setVisible(true);
        atrasButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MenuGUI();
                frame.setVisible(false);
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

    private void createUIComponents() {
        String[][] data = ctrl_presentacion.getInstance().refrescarRanking();
        String[] columnNames = { "Posición", "Jugador", "Puntuación" };
        scrollPane1 = new JScrollPane();
        rankingTable = new JTable(data, columnNames);
        rankingTable.getColumnModel().getColumn(0).sizeWidthToFit();
        rankingTable.getColumnModel().getColumn(1).sizeWidthToFit();
        rankingTable.getColumnModel().getColumn(2).sizeWidthToFit();
        scrollPane1.add(rankingTable);
    }
}
