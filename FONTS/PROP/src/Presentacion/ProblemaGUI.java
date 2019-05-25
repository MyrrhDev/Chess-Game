package Presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class ProblemaGUI {

    private JFrame gameFrame;
    //private final JPanel boardPanel;
    private final String imgPiecesPath = "./res";
    private int sourceTile = -1;
    private int destinationTile = -1;
    private JTextField textField1;
    private JButton validarYGuardarButton;
    private JButton validarButton;
    private JPanel aquiTablero;
    private JPanel problemaPanel;
    private JPanel leftPanel;
    private JButton loadButton;
    private JButton menuButton;
    private JPanel menuPanel;
    private JScrollPane problemsList;
    private JTable tableProblemas;
    private JButton borrarButton;
    private JButton guardarButton;

    public ProblemaGUI() {
        //Pending:
        gameFrame = new JFrame("Logic - A Chess Game");
        gameFrame.setContentPane(problemaPanel);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setSize(new Dimension(1040, 700));
        //frame.setResizable(false);
        //gameFrame.pack();

        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        aquiTablero = new BoardPanel();
        gameFrame.add(menuPanel, BorderLayout.NORTH);
        gameFrame.add(aquiTablero, BorderLayout.CENTER);
        gameFrame.add(leftPanel, BorderLayout.EAST);
        gameFrame.setVisible(true);


        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setVisible(false);
                new MenuPrincipalGUI();
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = tableProblemas.getSelectedRow();
                String FEN = tableProblemas.getModel().getValueAt(row, 0).toString();
                Integer N = new Integer(tableProblemas.getModel().getValueAt(row, 1).toString());

                ctrl_presentacion.getInstance().borrarProblema(FEN,N);
                DefaultTableModel model =
                        (DefaultTableModel)tableProblemas.getModel();
                model.removeRow(row);

            }
        });
        validarYGuardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Get FEN de Tablero...
                //Usuario inserta N
                //ctrl_presentacion.getInstance().validarYGuardarProblema(FEN,N);

            }
        });
        validarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get FEN de Tablero...
                //Usuario inserta N
                //ctrl_presentacion.getInstance().validarProblema(FEN,N);
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get FEN de Tablero...
                //Usuario inserta N
                //ctrl_presentacion.getInstance().guardarProblema(FEN,N);

            }
        });

        tableProblemas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(tableProblemas.getValueAt(tableProblemas.getSelectedRow(), 0).toString());
            }
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        problemsList = new JScrollPane();
        /*String[] columnNames = { "FEN", "dificultad"};
        String[][] data = {{"", ""}};
        table1 = new JTable(data, columnNames);*/
        tableProblemas = new JTable(0,3);

        JTableHeader header= tableProblemas.getTableHeader();
        TableColumnModel colMod = header.getColumnModel();
        TableColumn fenColumn = colMod.getColumn(0);
        fenColumn.setHeaderValue("FEN");
        TableColumn nColumn = colMod.getColumn(1);
        nColumn.setHeaderValue("Movimientos");

        TableColumn validColumn = colMod.getColumn(2);
        validColumn.setHeaderValue("Validado?");
        header.repaint();

        tableProblemas.getColumnModel().getColumn(0).sizeWidthToFit();
        tableProblemas.getColumnModel().getColumn(1).sizeWidthToFit();
        tableProblemas.getColumnModel().getColumn(2).sizeWidthToFit();
        //tableProblemas.setEnabled(false);

        //tableProblemas.ed
        tableProblemas.setSize(200, 300);
        tableProblemas.setRowSelectionAllowed(true);
        problemsList.add(tableProblemas);
        String[][] resultProblemas = buscarProblemas();
        addProblemasToTable(resultProblemas);

    }

    private String[][] buscarProblemas() {
        return ctrl_presentacion.getInstance().getTodosLosProblemas();
    }

    private void addProblemasToTable(String[][] resultProblemas) {
        DefaultTableModel model = (DefaultTableModel) tableProblemas.getModel();
        model.setRowCount(0);
        tableProblemas.setModel(model); //emty table with new problems
        for(int i = 0; i < resultProblemas.length; ++i) {
            model.addRow(new Object[] {resultProblemas[i][0], resultProblemas[i][1], resultProblemas[i][2]});
        }
        tableProblemas.setModel(model);
        tableProblemas.getColumnModel().getColumn(0).sizeWidthToFit();
        tableProblemas.getColumnModel().getColumn(1).sizeWidthToFit();
        tableProblemas.getColumnModel().getColumn(2).sizeWidthToFit();

        tableProblemas.setRowSelectionAllowed(true);
    }

    private class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;

        BoardPanel() {
            super(new GridLayout(8,8));
            this.boardTiles = new ArrayList<>();
            for(int i = 0; i < 64; ++i) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setSize(630,620);
            //setPreferredSize(new Dimension(400, 350));
            validate();
        }

        public void drawBoard(final char[] board) {
            validate();
            for(final TilePanel tlpan : boardTiles) {
                tlpan.drawTile(board);
            }
            validate();
            repaint();
        }
    }

    private class TilePanel extends JPanel {
        private final int tileId;
        private Color lightTileColor = Color.decode("#FFFACD");
        private Color darkTileColor = Color.decode("#593E1A");


        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(new Dimension(10, 10));
            assignTileColor();
            assignTilePiceIcon(ctrl_presentacion.getInstance().getTablero());

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if(isRightMouseButton(e)) {
                        //unselect every selection made
                        sourceTile = -1;
                        destinationTile = -1;
                    }
                    else if(isLeftMouseButton(e)) {
                        if(sourceTile == -1) {
                            sourceTile = tileId;
                            System.out.println("primer click tileId: " + tileId + " x " + tileId/8 + " y " + tileId%8 + " sourceTile: " + sourceTile);
                        } else {
                            destinationTile = tileId;
                            System.out.println("segundo click " + tileId + " x " + tileId/8 + " y " + tileId%8 + " destinationTile: " + destinationTile);
                            try {
                                //controladorDom.jugar(1,1,1,1);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    char[] tablero = {'K', '0', '0', '0', 'n', '0', 'P', '0',
                                            '0', 'Q', '0', '0', '0', '0', '0', '0',
                                            '0', '0', '0', '0', 'P', '0', 'k', '0',
                                            '0', '0', 'P', '0', '0', '0', '0', '0',
                                            '0', '0', '0', '0', '0', 'Q', '0', '0',
                                            '0', '0', '0', '0', '0', '0', '0', '0',
                                            '0', 'Q', '0', '0', 'P', '0', '0', '0',
                                            '0', '0', 'K', '0', '0', '0', 'p', 'P'};
                                    //aqui iria controladorDom.getTablero() y le pasaríamos ese tablero a la función drawBoard()
                                    boardPanel.drawBoard(tablero);
                                }
                            });
                            sourceTile = -1;
                            destinationTile = -1;
                        }
                    }
                }

                @Override
                public void mousePressed(final MouseEvent e) {
                }

                @Override
                public void mouseReleased(final MouseEvent e) {
                }

                @Override
                public void mouseEntered(final MouseEvent e) {

                }

                @Override
                public void mouseExited(final MouseEvent e) {

                }
            });

        }

        public void drawTile(final char[] board) {
            assignTileColor();
            assignTilePiceIcon(board);
            validate();
            repaint();
        }

        //@TODO: OJO NO LI PUC PASSAR UN OBJECTE DEL DOMINI
        private void assignTilePiceIcon(final char[] tablero) {
            this.removeAll();
            if(tablero[tileId] != '0') {
                try {
                    final BufferedImage im;
                    if(tablero[tileId] == 'K') {
                        im = ImageIO.read(new File("./res/King.gif"));
                    } else if(tablero[tileId] == 'B') {
                        im = ImageIO.read(new File("./res/Bishop.gif"));
                    } else if(tablero[tileId] == 'P') {
                        im = ImageIO.read(new File("./res/Pawn.gif"));
                    } else if(tablero[tileId] == 'Q') {
                        im = ImageIO.read(new File("./res/Queen.gif"));
                    } else if(tablero[tileId] == 'R') {
                        im = ImageIO.read(new File("./res/Rook.gif"));
                    } else if(tablero[tileId] == 'N') {
                        im = ImageIO.read(new File("./res/Norse.gif"));
                    } else {
                        im = ImageIO.read(new File("./res/" + tablero[tileId] + ".gif"));
                    }
                    add(new JLabel(new ImageIcon(im)));

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        private boolean isFirstRow(int tileId) {
            if(tileId/8 == 0) return true;
            return false;
        }

        private boolean isSecondRow(int tileId) {
            if(tileId/8 == 1) return true;
            return false;
        }

        private boolean isThirdRow(int tileId) {
            if(tileId/8 == 2) return true;
            return false;
        }

        private boolean isFourthRow(int tileId) {
            if(tileId/8 == 3) return true;
            return false;
        }

        private boolean isFifthRow(int tileId) {
            if(tileId/8 == 4) return true;
            return false;
        }


        private boolean isSixthRow(int tileId) {
            if(tileId/8 == 5) return true;
            return false;
        }

        private boolean isSeventhRow(int tileId) {
            if(tileId/8 == 6) return true;
            return false;
        }

        private boolean isEightRow(int tileId) {
            if(tileId/8 == 7) return true;
            return false;
        }


        private void assignTileColor() {
            if (isFirstRow(this.tileId) || isThirdRow(this.tileId) ||
                    isFifthRow(this.tileId) || isSeventhRow(this.tileId)) {
                setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
            } else if(isSecondRow(this.tileId) || isFourthRow(this.tileId) ||
                    isSixthRow(this.tileId)  || isEightRow(this.tileId)) {
                setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
            }
        }
    }

    public static void main(String[] args)
    {
        new ProblemaGUI();
    }







}
