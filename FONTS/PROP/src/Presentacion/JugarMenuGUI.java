package Presentacion;

import Domini.ctrl_dominio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class JugarMenuGUI {
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

    public JugarMenuGUI() {
        JFrame jf;
        jf = new JFrame("Logic: Entorno de resolución de problemas de ajedrez");
        jf.setSize(600, 600);
        jf.setContentPane(menuJugarPanel);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
        dificultadMenu.addItem("Facil");
        dificultadMenu.addItem("Medio");
        dificultadMenu.addItem("Dificil");
        jugadores.addItem("H1 vs H2");
        jugadores.addItem("M1 vs M2");
        jugadores.addItem("H1 vs M1");
        jugadores.addItem("H2 vs H1");
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
                else System.out.println(dificultadMenu.getSelectedItem() + " " + table1.getValueAt(table1.getSelectedRow(), table1.getSelectedColumn()) + " " + jugadores.getSelectedItem());
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
                jf.setVisible(false);
                new MenuGUI();
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

    private String[][] buscarProblemas(String dificultad) {
        ctrl_dominio dom = ctrl_dominio.getInstance();
        return dom.getProblemasDificultad(diff);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JugarMenuGUI();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        scrollPane1 = new JScrollPane();
        /*String[] columnNames = { "FEN", "dificultad"};
        String[][] data = {{"", ""}};
        table1 = new JTable(data, columnNames);*/
        table1 = new JTable(0,2);
        table1.getColumnModel().getColumn(0).sizeWidthToFit();
        table1.getColumnModel().getColumn(1).sizeWidthToFit();
        table1.setSize(200, 300);
        scrollPane1.add(table1);
    }
}