package Presentacion;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

public class MenuPartidaGUI {
    private JPanel menuJugarPanel;
    private JComboBox dificultadMenu;
    private JLabel dif;
    private JButton buscarProblemasButton;
    private JComboBox jugadores;
    private JLabel modojuego;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton jugarButton;
    private JButton atrasButton;
    private JScrollPane scroll;
    private JScrollPane tablePos;
    private static JFrame jframeMain;
    private String diff;
    private boolean problemasBuscados = false;
    private JFrame menuPartidaFrame;

    public MenuPartidaGUI() {
        menuPartidaFrame = new JFrame("Logic - A Chess Game");
        menuPartidaFrame.setSize(844, 650);
        menuPartidaFrame.setResizable(false);
        menuPartidaFrame.setContentPane(menuJugarPanel);
        menuPartidaFrame.setLocationRelativeTo(null);
        menuPartidaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPartidaFrame.pack();
        menuPartidaFrame.setVisible(true);
        dificultadMenu.addItem("Facil");
        dificultadMenu.addItem("Medio");
        dificultadMenu.addItem("Dificil");
        jugadores.addItem("H1 vs H2");
        jugadores.addItem("H1 vs M1");
        jugadores.addItem("H1 vs M2");
        jugadores.addItem("H2 vs H1");
        jugadores.addItem("M1 vs H1");
        jugadores.addItem("M2 vs H1");
        jugadores.addItem("M1 vs M2");
        jugadores.addItem("M2 vs M1");
        buscarProblemasButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //aqui haurem de fer la funció que busqui els problemes fàcils i els posi en la taula
                if(!problemasBuscados || (problemasBuscados && !diff.equals((String)dificultadMenu.getSelectedItem()))) {
                    problemasBuscados = true;
                    diff = String.valueOf(dificultadMenu.getSelectedItem());
                    String[][] resultProblemas = buscarProblemas(String.valueOf(dificultadMenu.getSelectedItem()));
                    //System.out.println(resultProblemas[0]); // Fen
                    //System.out.println(resultProblemas[1]); // N
                    addProblemasToTable(resultProblemas);
                }
                else if(problemasBuscados && diff.equals((String)dificultadMenu.getSelectedItem())) {
                    JFrame jferror = new JFrame("Error");
                    jferror.setPreferredSize(new Dimension(350, 100));
                    jferror.add(new JLabel("Los problemas ya se muestran en la tabla"));
                    jferror.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jferror.pack();
                    jferror.setVisible(true);
                }

            }

            private void addProblemasToTable(String[][] resultProblemas) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                table1.setModel(model); //emty table with new problems
                for(int i = 0; i < resultProblemas.length; ++i) {
                    model.addRow(new Object[] {resultProblemas[i][0], resultProblemas[i][1]});
                }
                table1.setModel(model);
                table1.getColumnModel().getColumn(0).sizeWidthToFit();
                table1.getColumnModel().getColumn(1).sizeWidthToFit();
                table1.setRowSelectionAllowed(true);
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

        jugarButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table1.getSelectedRow() == -1 || table1.getSelectedColumn() == -1) {
                    JFrame jferror = new JFrame("Error");
                    jferror.setPreferredSize(new Dimension(350, 100));
                    jferror.add(new JLabel("Selecciona un problema a jugar"));
                    jferror.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jferror.pack();
                    jferror.setVisible(true);
                }
                else {
                    //extract N value from string
                    int n = Integer.parseInt((String)table1.getValueAt(table1.getSelectedRow(), 1));
                    //extract FEN value from string
                    String FEN = (String)table1.getValueAt(table1.getSelectedRow(), 0);
                    String jugadoresSeleccionados = (String)jugadores.getSelectedItem();
                    String [] jugadoresSeleccionadosSplit = jugadoresSeleccionados.split(" ");
                    String player1 = jugadoresSeleccionadosSplit[0], player2 = jugadoresSeleccionadosSplit[2];
                    int playerId1 = 0, playerId2 = 0;
                    switch(player1) {
                        case "H1":
                            playerId1 = 1;
                            break;
                        case "H2":
                            playerId1 = 1;
                            break;
                        case "M1":
                            playerId1 = 2;
                            break;
                        case "M2":
                            playerId1 = 3;
                            break;
                    }
                    switch(player2) {
                        case "H1":
                            playerId2 = 1;
                            break;
                        case "H2":
                            playerId2 = 1;
                            break;
                        case "M1":
                            playerId2 = 2;
                            break;
                        case "M2":
                            playerId2 = 3;
                            break;
                    }
                    if(player1.equals("H2") || player2.equals("H2")) {
                        //pedimos login H2
                        new LoginH2GUI(FEN, n, playerId1, playerId2, jugadoresSeleccionadosSplit[0], jugadoresSeleccionadosSplit[2], diff);
                        menuPartidaFrame.setVisible(false);
                    }
                    else {
                        iniciarPartida(FEN, n, playerId1, playerId2, jugadoresSeleccionadosSplit[0], jugadoresSeleccionadosSplit[2]);
                    }
                }
            }

            public void iniciarPartida(final String FEN, final int n, final int playerId1, final int playerId2, final String jugadorSeleccionado1, final String jugadorSeleccionado2) {
                ctrl_presentacion.getInstance().crearPartida(FEN, n, playerId1, playerId2);
                //System.out.println(dificultadMenu.getSelectedItem() + " FEN:  " + FEN + " N: " + n + " playerId1: "+ playerId1 + " playerId2: " + playerId2 + " " + jugadores.getSelectedItem());
                JugarPartidaGUI partidaTablero = new JugarPartidaGUI(jugadorSeleccionado1, jugadorSeleccionado2, FEN, n, diff);
                menuPartidaFrame.setVisible(false);
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

        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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

        atrasButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                menuPartidaFrame.setVisible(false);
                new MenuPrincipalGUI();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private String[][] buscarProblemas(String dificultad) {
        return ctrl_presentacion.getInstance().getProblemasDificultad(diff);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        scrollPane1 = new JScrollPane();
        /*String[] columnNames = { "FEN", "dificultad"};
        String[][] data = {{"", ""}};
        table1 = new JTable(data, columnNames);*/
        table1 = new JTable(0,2);
        JTableHeader header= table1.getTableHeader();
        TableColumnModel colMod = header.getColumnModel();
        TableColumn fenColumn = colMod.getColumn(0);
        fenColumn.setHeaderValue("FEN");
        TableColumn nColumn = colMod.getColumn(1);
        nColumn.setHeaderValue("Movimientos");
        table1.getColumnModel().getColumn(0).sizeWidthToFit();
        table1.getColumnModel().getColumn(1).sizeWidthToFit();
        table1.setSize(200, 300);
        table1.setRowSelectionAllowed(true);
        scrollPane1.add(table1);
    }
}