package Presentacion;

import Domini.ctrl_dominio;

import javax.swing.*;

public class RankingGUI {
    private JTable Ranking;
    private JPanel panel1;
    private JButton button1;
    // frame
    JFrame f;
    //test only
    private static ctrl_dominio cd;


    RankingGUI(String[][] data) {
        // Frame initiallization
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Frame Title
        f.setTitle("Ranking");

        // Column Names
        String[] columnNames = { "Posición", "Jugador", "Puntuación" };
        // Initializing the JTable
        Ranking = new JTable(data, columnNames);
        //Ranking.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Ranking.getColumnModel().getColumn(0).sizeWidthToFit();
        Ranking.getColumnModel().getColumn(1).sizeWidthToFit();
        Ranking.getColumnModel().getColumn(2).sizeWidthToFit();

        Ranking.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(Ranking);
        f.add(sp);
        // Frame Size
        f.setSize(600, 300);
        // Frame Visible = true
        f.setVisible(true);
    }

    public static void main(String[] args)
    {
        cd = ctrl_dominio.getInstance();
        new RankingGUI(cd.refrescarRanking());
    }
}