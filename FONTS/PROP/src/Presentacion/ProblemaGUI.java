package Presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import static java.util.Arrays.sort;

public class ProblemaGUI {

    private JFrame gameFrame;
    private JTextField fenString;
    private JButton validarYGuardarButton;
    private JButton validarButton;
    private BoardPanel aquiTablero;
    private JPanel tableroPanel;
    private JPanel problemaPanel;
    private JPanel leftPanel;
    private JButton loadButton;
    private JButton menuButton;
    private JPanel menuPanel;
    private JScrollPane problemsList;
    private JTable tableProblemas;
    private JButton borrarButton;
    private JButton guardarButton;
    private JPanel blackPieces;
    private JPanel supremePanel;
    private JPanel whitePieces;
    private JButton limpiarTableroButton;
    private SetPieces aquiBlackPieces;
    private SetPieces aquiWhitePieces;
    private JSpinner numMovimientos;
    private JRadioButton negrasRadioButton;
    private JRadioButton blancasRadioButton;
    private JPanel TableroOptions;


    private final String imageKing = "./res/King.gif";
    private final String imageBishop = "./res/Bishop.gif";
    private final String imagePawn = "./res/Pawn.gif";
    private final String imageQueen = "./res/Queen.gif";
    private final String imageRook = "./res/Rook.gif";
    private final String imageNorse = "./res/Norse.gif";


    private final String imageK = "./res/k.gif";
    private final String imageB = "./res/b.gif";
    private final String imageP = "./res/p.gif";
    private final String imageQ = "./res/q.gif";
    private final String imageR = "./res/r.gif";
    private final String imageN = "./res/n.gif";


    public ProblemaGUI() {
        gameFrame = new JFrame("Logic - A Chess Game");
        gameFrame.setContentPane(problemaPanel);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setSize(new Dimension(1300, 900)); //1130
        gameFrame.setResizable(false);
        //gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        supremePanel = new JPanel();
        supremePanel.setSize(640, 1028);
        supremePanel.setLayout(new BorderLayout());


        blackPieces = new JPanel();
        blackPieces.setSize(635, 200);
        aquiBlackPieces = new SetPieces(false);
        blackPieces.add(aquiBlackPieces);

        whitePieces = new JPanel();
        whitePieces.setSize(635, 200);
        aquiWhitePieces = new SetPieces(true);
        whitePieces.add(aquiWhitePieces);

        tableroPanel = new JPanel();
        tableroPanel.setSize(635, 625);
        aquiTablero = new BoardPanel();
        tableroPanel.add(aquiTablero);

        supremePanel.add(blackPieces, BorderLayout.NORTH);
        supremePanel.add(tableroPanel, BorderLayout.WEST);
        supremePanel.add(whitePieces, BorderLayout.SOUTH);


        gameFrame.add(menuPanel, BorderLayout.NORTH);
        gameFrame.add(supremePanel, BorderLayout.WEST);
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

                ctrl_presentacion.getInstance().borrarProblema(FEN, N);

                tableProblemas.setRowSelectionInterval(0, 0);
                DefaultTableModel model = (DefaultTableModel) tableProblemas.getModel();
                model.removeRow(row);
            }
        });
        validarYGuardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String FEN = getFENfromTablero();
                int N = (int) numMovimientos.getValue();
                System.out.println(N);
                boolean blancas = true;

                if(negrasRadioButton.isSelected()) {
                    blancas = false;
                }
                boolean success = ctrl_presentacion.getInstance().validarProblema(FEN,N,blancas);

                if(success) {
                    boolean guardado = ctrl_presentacion.getInstance().guardarProblema(FEN,N, blancas);
                    if(guardado) {
                        JOptionPane.showMessageDialog(null, "Se ha guardado el problema validado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ese problema con ese numero de movimientos ya existe");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El problema no ha podido ser validado");
                }
            }
        });
        validarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String FEN = getFENfromTablero();
                int N = (int) numMovimientos.getValue();
                System.out.println(N);
                boolean blancas = true;
                if(negrasRadioButton.isSelected()) {
                    blancas = false;
                }
                boolean success = ctrl_presentacion.getInstance().validarProblema(FEN,N,blancas);
                if(success) {
                    JOptionPane.showMessageDialog(null, "Se ha validado el problema!");
                } else {
                    JOptionPane.showMessageDialog(null, "El problema no ha podido ser validado");
                }
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String FEN = getFENfromTablero();
                int N = (int) numMovimientos.getValue();
                System.out.println(N);
                boolean blancas = true;
                if(negrasRadioButton.isSelected()) {
                    blancas = false;
                }
                boolean guardado = ctrl_presentacion.getInstance().guardarProblema(FEN,N, blancas);
                if(guardado) {
                    JOptionPane.showMessageDialog(null, "Se ha guardado el problema no validado");
                } else {
                    JOptionPane.showMessageDialog(null, "El problema ya existe con ese numero de movimientos");
                }
            }
        });

        tableProblemas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int row = tableProblemas.getSelectedRow();
                String FEN = tableProblemas.getModel().getValueAt(row, 0).toString();
                Integer N = new Integer(tableProblemas.getModel().getValueAt(row, 1).toString());
                ctrl_presentacion.getInstance().crearPartidaProblema(FEN, N, 2, 2);
                fenString.setText("");

                aquiTablero.drawBoard(ctrl_presentacion.getInstance().getTablero());
                aquiBlackPieces.drawPiecesSideBoards(ctrl_presentacion.getInstance().getTablero(),false);
                aquiWhitePieces.drawPiecesSideBoards(ctrl_presentacion.getInstance().getTablero(),true);

            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String FEN = fenString.getText();
                ctrl_presentacion.getInstance().crearPartidaProblema(FEN, 2, 2, 2);
                aquiTablero.drawBoard(ctrl_presentacion.getInstance().getTablero());

                //aquiBlackPieces.drawPiecesSideBoards(ctrl_presentacion.getInstance().getTablero(), false);
                //aquiWhitePieces.drawPiecesSideBoards(ctrl_presentacion.getInstance().getTablero(), true);

            }
        });

        limpiarTableroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aquiTablero.drawBoard(ctrl_presentacion.getInstance().getEmptyTablero());
                aquiBlackPieces.drawPiecesSideBoards(ctrl_presentacion.getInstance().getEmptyTablero(), false);
                aquiWhitePieces.drawPiecesSideBoards(ctrl_presentacion.getInstance().getEmptyTablero(), true);
            }
        });
        blancasRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(blancasRadioButton.isSelected()) negrasRadioButton.setSelected(false);

            }
        });
        negrasRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(negrasRadioButton.isSelected()) blancasRadioButton.setSelected(false);

            }
        });
    }

    private String getFENfromTablero() {
        StringBuilder createdFEN = new StringBuilder();
        int tiles = 0;
        int spaces = 0;

        for (int i = 0; i < aquiTablero.boardTiles.size(); i++) {
            TilePanel tile = aquiTablero.boardTiles.get(i);
            if(tile.getComponents().length != 0) {
                if(spaces != 0) {
                    createdFEN.append((char)(spaces+'0'));
                    spaces = 0;
                }
                JLabel label = (JLabel) tile.getComponent(0);
                if (label.getIcon().toString() == imageKing) {
                    createdFEN.append('K');
                } else if (label.getIcon().toString() == imageBishop) {
                    createdFEN.append('B');
                } else if (label.getIcon().toString() == imagePawn) {
                    createdFEN.append('P');
                } else if (label.getIcon().toString() == imageQueen) {
                    createdFEN.append('Q');
                } else if (label.getIcon().toString() == imageRook) {
                    createdFEN.append('R');
                } else if (label.getIcon().toString() == imageNorse) {
                    createdFEN.append('N');
                } else if (label.getIcon().toString() == imageK) {
                    createdFEN.append('k');
                } else if (label.getIcon().toString() == imageB) {
                    createdFEN.append('b');
                } else if (label.getIcon().toString() == imageP) {
                    createdFEN.append('p');
                } else if (label.getIcon().toString() == imageQ) {
                    createdFEN.append('q');
                } else if (label.getIcon().toString() == imageR) {
                    createdFEN.append('r');
                } else if (label.getIcon().toString() == imageN) {
                    createdFEN.append('n');
                }
            } else {
                ++spaces;
            }
            ++tiles;

            if (tiles == 8) {
                if(spaces != 0) {
                    createdFEN.append((char)(spaces+'0'));
                    spaces = 0;
                }
                if(i != aquiTablero.boardTiles.size()-1) {
                    createdFEN.append('/');
                }
                tiles = 0;
                spaces = 0;
            }
        }
        String FEN = createdFEN.toString();
        System.out.println(FEN);

        return FEN;
    }

    private void createUIComponents() {
        /*problemsList = new JScrollPane();
        tableProblemas = new JTable();*/
        SpinnerNumberModel model1 = new SpinnerNumberModel(2, 0, 15, 1);
        numMovimientos = new JSpinner(model1);
        problemsList = new JScrollPane();
        tableProblemas = new JTable(0, 3);
        JTableHeader header = tableProblemas.getTableHeader();
        TableColumnModel colMod = header.getColumnModel();
        TableColumn fenColumn = colMod.getColumn(0);
        fenColumn.setHeaderValue("FEN");
        TableColumn nColumn = colMod.getColumn(1);
        nColumn.setHeaderValue("Movimientos");
        TableColumn validColumn = colMod.getColumn(2);
        validColumn.setHeaderValue("Validado?");
        header.repaint();
        tableProblemas.setRowSelectionAllowed(true);
        String[][] resultProblemas = buscarProblemas();
        addProblemasToTable(resultProblemas);
        problemsList.add(tableProblemas);
        /*tableProblemas.getColumnModel().getColumn(0).sizeWidthToFit();
        tableProblemas.getColumnModel().getColumn(1).sizeWidthToFit();
        tableProblemas.getColumnModel().getColumn(2).sizeWidthToFit();
        tableProblemas.setSize(200, 300);*/
    }

    private String[][] buscarProblemas() {
        return ctrl_presentacion.getInstance().getTodosLosProblemas();
    }

    private void addProblemasToTable(String[][] resultProblemas) {
        DefaultTableModel model = (DefaultTableModel) tableProblemas.getModel();
        model.setRowCount(0);
        tableProblemas.setModel(model); //emty table with new problems
        for (int i = 0; i < resultProblemas.length; ++i) {
            model.addRow(new Object[]{resultProblemas[i][0], resultProblemas[i][1], resultProblemas[i][2]});
        }
        tableProblemas.setModel(model);
        tableProblemas.getColumnModel().getColumn(0).sizeWidthToFit();
        tableProblemas.getColumnModel().getColumn(1).sizeWidthToFit();
        tableProblemas.getColumnModel().getColumn(2).sizeWidthToFit();
        if (tableProblemas != null) {
            //tableProblemas.setRowSelectionInterval(0, 0);

            //String FEN = tableProblemas.getModel().getValueAt(0, 0).toString();
            //Integer N = new Integer(tableProblemas.getModel().getValueAt(0, 1).toString());
            //ctrl_presentacion.getInstance().crearPartidaProblema(FEN, N, 2, 2);
        }
        tableProblemas.setRowSelectionAllowed(true);
    }


    private class SetPieces extends JPanel {
        final List<PiecesTiles> pieceTiles;

        SetPieces(boolean white) {
            super(new GridLayout(2, 8));
            this.pieceTiles = new ArrayList<>();
            for (int i = 0; i < 16; ++i) {
                final PiecesTiles tilePanel = new PiecesTiles(white, i);

                new MyDropTargetListener(tilePanel);
                MyDragGestureListener dlistener = new MyDragGestureListener();
                DragSource ds1 = new DragSource();
                ds1.createDefaultDragGestureRecognizer(tilePanel, DnDConstants.ACTION_COPY, dlistener);

                this.pieceTiles.add(tilePanel);
                add(tilePanel);
                //setSize(630,160);
                setPreferredSize(new Dimension(630, 100));
                validate();
            }

        }

        public void drawPiecesSideBoards(final char[] tablero, boolean white) {
            validate();
            char[] piecesLeft = remakeTablero(tablero, white);

            for (final PiecesTiles tlpan : pieceTiles) {
                tlpan.drawPieceTile(piecesLeft, white);
            }
            validate();
            repaint();
        }

        private char[] remakeTablero(char[] tablero, boolean white) {
            if (!white) {
                char[] piecesTablero = new char[]{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'};
                sort(piecesTablero);
                for (int i = 0; i < tablero.length; ++i) {
                    if (tablero[i] != '0') {
                        boolean found = false;
                        for (int j = 0; j < piecesTablero.length; ++j) {
                            if (!found) {
                                if (piecesTablero[j] == tablero[i]) {
                                    piecesTablero[j] = '0';
                                    found = true;
                                }
                            }
                        }
                    }
                }
                return piecesTablero;
            } else {
                char[] piecesWhite = new char[]{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'R', 'N', 'B', 'K', 'Q', 'B', 'N', 'R'};
                for (int i = 0; i < tablero.length; ++i) {
                    if (tablero[i] != '0') {
                        boolean found = false;
                        for (int j = 0; j < piecesWhite.length; ++j) {
                            if (!found) {
                                if (piecesWhite[j] == tablero[i]) {
                                    piecesWhite[j] = '0';
                                    found = true;
                                }
                            }
                        }
                    }
                }
                return piecesWhite;
            }
        }
    }

    private class PiecesTiles extends JPanel {

        private final int tileId;
        private Color lightTileColor = Color.decode("#654321");
        private Color darkTileColor = Color.decode("#593E1A");

        private BufferedImage im;
        private Point imgPoint = new Point(20, 20);

        public PiecesTiles(final boolean white, final int tileId) {
            this.tileId = tileId;
            setPreferredSize(new Dimension(10, 10));
            assignTileColor();
            assignInitialTilePieceIcon(white, tileId);
        }


        public void drawPieceTile(final char[] piecesLeft, boolean white) {
            assignTileColor();
            assignTilePieceIcon(piecesLeft);
            validate();
            repaint();
        }

        private void assignTilePieceIcon(final char[] tablero) {
            //System.out.println(tablero);

            this.removeAll();
            if (tablero[tileId] != '0') {
//                try {
//                    //                   final BufferedImage im;
//                    String searchableKey = new String();
//                    if (tablero[tileId] == 'K') {
//                        im = ImageIO.read(new File(imageKing));
//                        //im = Image.getInstance(this.getClass().getResource("res/k.gif"));
//                        searchableKey = imageKing;
//                    } else if (tablero[tileId] == 'B') {
//                        im = ImageIO.read(new File(imageBishop));
//                        searchableKey = imageBishop;
//                    } else if (tablero[tileId] == 'P') {
//                        im = ImageIO.read(new File(imagePawn));
//                        searchableKey = imagePawn;
//                    } else if (tablero[tileId] == 'Q') {
//                        im = ImageIO.read(new File(imageQueen));
//                        searchableKey = imageQueen;
//                    } else if (tablero[tileId] == 'R') {
//                        im = ImageIO.read(new File(imageRook));
//                        searchableKey = imageRook;
//                    } else if (tablero[tileId] == 'N') {
//                        im = ImageIO.read(new File(imageNorse));
//                        searchableKey = imageNorse;
//                    } else if (tablero[tileId] == 'k') {
//                        im = ImageIO.read(new File(imageK));
//                        searchableKey = imageK;
//                    } else if (tablero[tileId] == 'b') {
//                        im = ImageIO.read(new File(imageB));
//                        searchableKey = imageB;
//                    } else if (tablero[tileId] == 'p') {
//                        im = ImageIO.read(new File(imageP));
//                        searchableKey = imageP;
//                    } else if (tablero[tileId] == 'q') {
//                        im = ImageIO.read(new File(imageQ));
//                        searchableKey = imageQ;
//                    } else if (tablero[tileId] == 'r') {
//                        im = ImageIO.read(new File(imageR));
//                        searchableKey = imageR;
//                    } else if (tablero[tileId] == 'n') {
//                        im = ImageIO.read(new File(imageN));
//                        searchableKey = imageN;
//                    }
//                    JLabel label1 = new JLabel(new ImageIcon(im, searchableKey));
//                    add(label1);
//                    //add(new JLabel(new ImageIcon(im, searchableKey)));
//                    MyDragGestureListener dlistener = new MyDragGestureListener();
//                    DragSource ds1 = new DragSource();
//                    ds1.createDefaultDragGestureRecognizer(label1, DnDConstants.ACTION_COPY, dlistener);
//
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
                    try {
                        //                   final BufferedImage im;
                        String searchableKey = new String();
                        ImageIcon imageIc = null;
                        if (tablero[tileId] == 'K') {
                            //im = ImageIO.read(new File(imageKing));
                            searchableKey = imageKing;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/King.gif"), searchableKey);
                        } else if (tablero[tileId] == 'B') {
                            //im = ImageIO.read(new File(imageBishop));
                            searchableKey = imageBishop;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/Bishop.gif"), searchableKey);
                        } else if (tablero[tileId] == 'P') {
                            //im = ImageIO.read(new File(imagePawn));
                            searchableKey = imagePawn;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/Pawn.gif"), searchableKey);
                        } else if (tablero[tileId] == 'Q') {
                            //im = ImageIO.read(new File(imageQueen));
                            searchableKey = imageQueen;
                        } else if (tablero[tileId] == 'R') {
                            //im = ImageIO.read(new File(imageRook));
                            searchableKey = imageRook;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/Rook.gif"), searchableKey);
                        } else if (tablero[tileId] == 'N') {
                            //im = ImageIO.read(new File(imageNorse));
                            searchableKey = imageNorse;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/Norse.gif"), searchableKey);
                        } else if (tablero[tileId] == 'k') {
                            //im = ImageIO.read(new File(imageK));
                            searchableKey = imageK;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/k.gif"), searchableKey);
                        } else if (tablero[tileId] == 'b') {
                            //im = ImageIO.read(new File(imageB));
                            searchableKey = imageB;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/b.gif"), searchableKey);
                        } else if (tablero[tileId] == 'p') {
                            //im = ImageIO.read(new File(imageP));
                            searchableKey = imageP;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/p.gif"), searchableKey);
                        } else if (tablero[tileId] == 'q') {
                            //im = ImageIO.read(new File(imageQ));
                            searchableKey = imageQ;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/q.gif"), searchableKey);
                        } else if (tablero[tileId] == 'r') {
                            //im = ImageIO.read(new File(imageR));
                            searchableKey = imageR;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/r.gif"), searchableKey);
                        } else if (tablero[tileId] == 'n') {
                            //im = ImageIO.read(new File(imageN));
                            searchableKey = imageN;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/n.gif"), searchableKey);
                        }
                        JLabel label1 = new JLabel(imageIc);
                        add(label1);
                        //add(new JLabel(new ImageIcon(im, searchableKey)));
                        MyDragGestureListener dlistener = new MyDragGestureListener();
                        DragSource ds1 = new DragSource();
                        ds1.createDefaultDragGestureRecognizer(label1, DnDConstants.ACTION_COPY, dlistener);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
            } else {
                im = null;

            }
        }

            /*private void assignTilePieceIcon(char[] piecesLeft, boolean white) {
                this.removeAll();
                String searchableKey = new String();

                if (piecesLeft[tileId] != '0') {
                    try {
                        if (!white) {
                            if (piecesLeft[tileId] == 'p') {
                                im = ImageIO.read(new File(imageP));
                                searchableKey = imageP;

                            } else if (piecesLeft[tileId] == 'k') {
                                im = ImageIO.read(new File(imageK));
                                searchableKey = imageK;
                            } else if (piecesLeft[tileId] == 'b') {
                                im = ImageIO.read(new File(imageB));
                                searchableKey = imageB;
                            } else if (piecesLeft[tileId] == 'p') {
                                im = ImageIO.read(new File(imageP));
                                searchableKey = imageP;
                            } else if (piecesLeft[tileId] == 'q') {
                                im = ImageIO.read(new File(imageQ));
                                searchableKey = imageQ;
                            } else if (piecesLeft[tileId] == 'r') {
                                im = ImageIO.read(new File(imageR));
                                searchableKey = imageR;
                            } else if(piecesLeft[tileId] == 'n') {
                                im = ImageIO.read(new File(imageN));
                                searchableKey = imageN;
                            }

                    JLabel label1 = new JLabel(new ImageIcon(im, searchableKey));
                    add(label1);
                    //add(new JLabel(new ImageIcon(im, searchableKey)));
                    MyDragGestureListener dlistener = new MyDragGestureListener();
                    DragSource ds1 = new DragSource();
                    ds1.createDefaultDragGestureRecognizer(label1, DnDConstants.ACTION_MOVE, dlistener);
                } catch (Exception e) {
                        System.out.println(e);
                    }
            }*/

        private void assignTileColor() {
            setBackground(lightTileColor);

        }


        private void assignInitialTilePieceIcon(boolean white, int tileId) {
            this.removeAll();
            String searchableKey = new String();

            if (!white) {
                if (tileId > 7) {
                    try {
                        searchableKey = imageP;
                        ImageIcon imageIc = new ImageIcon(this.getClass().getResource("/res/p.gif"), searchableKey);
                        //im = ImageIO.read(new File(imageP));
                        JLabel label1 = new JLabel(imageIc);
                        add(label1);
                        //add(new JLabel(new ImageIcon(im, searchableKey)));
                        MyDragGestureListener dlistener = new MyDragGestureListener();
                        DragSource ds1 = new DragSource();
                        ds1.createDefaultDragGestureRecognizer(label1, DnDConstants.ACTION_MOVE, dlistener);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    ImageIcon imageIc = new ImageIcon();
                    try {
                        if (tileId == 0 || tileId == 7) {
                            //im = ImageIO.read(new File(imageR));
                            searchableKey = imageR;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/r.gif"), searchableKey);
                        } else if (tileId == 1 || tileId == 6) {
                            //im = ImageIO.read(new File(imageN));
                            searchableKey = imageN;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/n.gif"), searchableKey);
                        } else if (tileId == 2 || tileId == 5) {
                            //im = ImageIO.read(new File(imageB));
                            searchableKey = imageB;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/b.gif"), searchableKey);
                        } else if (tileId == 3) {
                            //im = ImageIO.read(new File(imageQ));
                            searchableKey = imageQ;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/q.gif"), searchableKey);
                        } else if (tileId == 4) {
                            //im = ImageIO.read(new File(imageK));
                            searchableKey = imageK;
                            imageIc = new ImageIcon(this.getClass().getResource("/res/k.gif"), searchableKey);
                        }
                        JLabel label1 = new JLabel(imageIc);
                        add(label1);
                        MyDragGestureListener dlistener = new MyDragGestureListener();
                        DragSource ds1 = new DragSource();
                        ds1.createDefaultDragGestureRecognizer(label1, DnDConstants.ACTION_MOVE, dlistener);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } else {
                if (tileId < 8) {
                    try {
                        //im = ImageIO.read(new File(imagePawn));
                        searchableKey = imagePawn;
                        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/res/Pawn.gif"), searchableKey);
                        JLabel label1 = new JLabel(imageIcon);
                        add(label1);
                        //add(new JLabel(new ImageIcon(im, searchableKey)));
                        MyDragGestureListener dlistener = new MyDragGestureListener();
                        DragSource ds1 = new DragSource();
                        ds1.createDefaultDragGestureRecognizer(label1, DnDConstants.ACTION_MOVE, dlistener);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    try {
                        ImageIcon imageIcon = new ImageIcon();
                        if (tileId == 15 || tileId == 8) {
                            //im = ImageIO.read(new File(imageRook));
                            searchableKey = imageRook;
                            imageIcon = new ImageIcon(this.getClass().getResource("/res/Rook.gif"), searchableKey);
                        } else if (tileId == 14 || tileId == 9) {
                            //im = ImageIO.read(new File(imageNorse));
                            searchableKey = imageNorse;
                            imageIcon = new ImageIcon(this.getClass().getResource("/res/Norse.gif"), searchableKey);
                        } else if (tileId == 13 || tileId == 10) {
                            //im = ImageIO.read(new File(imageBishop));
                            searchableKey = imageBishop;
                            imageIcon = new ImageIcon(this.getClass().getResource("/res/Bishop.gif"), searchableKey);
                        } else if (tileId == 12) {
                            //im = ImageIO.read(new File(imageQueen));
                            searchableKey = imageQueen;
                            imageIcon = new ImageIcon(this.getClass().getResource("/res/Queen.gif"), searchableKey);
                        } else if (tileId == 11) {
                            //im = ImageIO.read(new File(imageKing));
                            searchableKey = imageKing;
                            imageIcon = new ImageIcon(this.getClass().getResource("/res/King.gif"), searchableKey);
                        }

                        JLabel label1 = new JLabel(imageIcon);
                        add(label1);
                        MyDragGestureListener dlistener = new MyDragGestureListener();
                        DragSource ds1 = new DragSource();
                        ds1.createDefaultDragGestureRecognizer(label1, DnDConstants.ACTION_MOVE, dlistener);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
        }
    }


    private class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;

        BoardPanel() {
            super(new GridLayout(8, 8));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < 64; ++i) {
                final TilePanel tilePanel = new TilePanel(this, i);
                new MyDropTargetListener(tilePanel);
                MyDragGestureListener dlistener = new MyDragGestureListener();
                DragSource ds1 = new DragSource();
                ds1.createDefaultDragGestureRecognizer(tilePanel, DnDConstants.ACTION_COPY, dlistener);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            //setSize(630,620);
            setPreferredSize(new Dimension(630, 620));
            validate();
        }

        public void drawBoard(final char[] board) {
            validate();
            for (final TilePanel tlpan : boardTiles) {
                tlpan.drawTile(board);
            }
            validate();
            repaint();
        }
    }

    public class TilePanel extends JPanel {
        private final int tileId;
        private Color lightTileColor = Color.decode("#FFFACD");
        private Color darkTileColor = Color.decode("#593E1A");

        private BufferedImage im;
        private Point imgPoint = new Point(20, 20);


        public TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(new Dimension(10, 10));
            assignTileColor();
            //assignTilePieceIcon(ctrl_presentacion.getInstance().getTablero());
            //getEmptyTablero();

            TransferHandler dnd = new TransferHandler() {
                @Override
                public boolean canImport(TransferSupport support) {
                    if (!support.isDrop()) {
                        return false;
                    }
                    //only Strings
                    if (!support.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                        return false;
                    }
                    return true;
                }

                @Override
                public boolean importData(TransferSupport support) {
                    if (!canImport(support)) {
                        return false;
                    }

                    Transferable tansferable = support.getTransferable();
                    ImageIcon ico;
                    //Icon ico;
                    try {
                        //TODO: Aqui no hago lo que hago con las otras imagenes
                        ico = (ImageIcon) tansferable.getTransferData(DataFlavor.imageFlavor);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                    add(new JLabel(ico));
                    return true;
                }
            };

            setTransferHandler(dnd);



/*
                MouseAdapter ma = new MouseAdapter() {

                    private Point offset;

                    @Override
                    public void mousePressed(MouseEvent e) {
                        Rectangle bounds = getImageBounds();
                        Point mp = e.getPoint();
                        if (bounds.contains(mp)) {
                            offset = new Point();
                            offset.x = mp.x - bounds.x;
                            offset.y = mp.y - bounds.y;
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        offset = null;
                    }

                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (offset != null) {
                            Point mp = e.getPoint();
                            imgPoint.x = mp.x - offset.x;
                            imgPoint.y = mp.y - offset.y;
                            repaint();
                        }
                    }

                };
                addMouseListener(ma);
                addMouseMotionListener(ma);*/

                /*addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(final MouseEvent e) {
                        if (isRightMouseButton(e)) {
                            //unselect every selection made
                            sourceTile = -1;
                            destinationTile = -1;
                        } else if (isLeftMouseButton(e)) {
                            if (sourceTile == -1) {
                                sourceTile = tileId;
                                System.out.println("primer click tileId: " + tileId + " x " + tileId / 8 + " y " + tileId % 8 + " sourceTile: " + sourceTile);
                            } else {
                                destinationTile = tileId;
                                System.out.println("segundo click " + tileId + " x " + tileId / 8 + " y " + tileId % 8 + " destinationTile: " + destinationTile);
                                try {
                                    //controladorDom.jugar(1,1,1,1);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                //boardPanel.drawBoard(ctrl_dominio.getInstance().getTablero());
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
                });*/

        }
/*
            protected Rectangle getImageBounds() {
                Rectangle bounds = new Rectangle(0, 0, 0, 0);
                if (im != null) {
                    bounds.setLocation(imgPoint);
                    bounds.setSize(im.getWidth(), im.getHeight());
                }
                return bounds;
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(200, 200);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (im != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.drawImage(im, imgPoint.x, imgPoint.y, this);
                    g2d.dispose();
                }
            }*/

        public void drawTile(final char[] board) {
            assignTileColor();
            assignTilePieceIcon(board);
            validate();
            repaint();
        }

        private void assignTilePieceIcon(final char[] tablero) {
            this.removeAll();
            if (tablero[tileId] != '0') {
                try {
                    ImageIcon imageIcon = new ImageIcon();
                    //                   final BufferedImage im;
                    String searchableKey = new String();
                    if (tablero[tileId] == 'K') {
                        //im = ImageIO.read(new File(imageKing));
                        searchableKey = imageKing;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/king.gif"), searchableKey);
                    } else if (tablero[tileId] == 'B') {
                        //im = ImageIO.read(new File(imageBishop));
                        searchableKey = imageBishop;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/Bishop.gif"), searchableKey);
                    } else if (tablero[tileId] == 'P') {
                        //im = ImageIO.read(new File(imagePawn));
                        searchableKey = imagePawn;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/Pawn.gif"), searchableKey);
                    } else if (tablero[tileId] == 'Q') {
                        //im = ImageIO.read(new File(imageQueen));
                        searchableKey = imageQueen;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/Queen.gif"), searchableKey);
                    } else if (tablero[tileId] == 'R') {
                        //im = ImageIO.read(new File(imageRook));
                        searchableKey = imageRook;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/Rook.gif"), searchableKey);
                    } else if (tablero[tileId] == 'N') {
                        //im = ImageIO.read(new File(imageNorse));
                        searchableKey = imageNorse;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/n.gif"), searchableKey);
                    } else if (tablero[tileId] == 'k') {
                        //im = ImageIO.read(new File(imageK));
                        searchableKey = imageK;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/k.gif"), searchableKey);
                    } else if (tablero[tileId] == 'b') {
                        //im = ImageIO.read(new File(imageB));
                        searchableKey = imageB;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/b.gif"), searchableKey);
                    } else if (tablero[tileId] == 'p') {
                        //im = ImageIO.read(new File(imageP));
                        searchableKey = imageP;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/p.gif"), searchableKey);
                    } else if (tablero[tileId] == 'q') {
                        //im = ImageIO.read(new File(imageQ));
                        searchableKey = imageQ;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/q.gif"), searchableKey);
                    } else if (tablero[tileId] == 'r') {
                        //im = ImageIO.read(new File(imageR));
                        searchableKey = imageR;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/r.gif"), searchableKey);
                    } else if (tablero[tileId] == 'n') {
                        //im = ImageIO.read(new File(imageN));
                        searchableKey = imageN;
                        imageIcon = new ImageIcon(this.getClass().getResource("/res/n.gif"), searchableKey);
                    }
                    JLabel label1 = new JLabel(imageIcon);
                    add(label1);
                    //add(new JLabel(new ImageIcon(im, searchableKey)));
                    MyDragGestureListener dlistener = new MyDragGestureListener();
                    DragSource ds1 = new DragSource();
                    ds1.createDefaultDragGestureRecognizer(label1, DnDConstants.ACTION_COPY, dlistener);

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                im = null;

            }
        }

        private boolean isFirstRow(int tileId) {
            if (tileId / 8 == 0) return true;
            return false;
        }

        private boolean isSecondRow(int tileId) {
            if (tileId / 8 == 1) return true;
            return false;
        }

        private boolean isThirdRow(int tileId) {
            if (tileId / 8 == 2) return true;
            return false;
        }

        private boolean isFourthRow(int tileId) {
            if (tileId / 8 == 3) return true;
            return false;
        }

        private boolean isFifthRow(int tileId) {
            if (tileId / 8 == 4) return true;
            return false;
        }


        private boolean isSixthRow(int tileId) {
            if (tileId / 8 == 5) return true;
            return false;
        }

        private boolean isSeventhRow(int tileId) {
            if (tileId / 8 == 6) return true;
            return false;
        }

        private boolean isEightRow(int tileId) {
            if (tileId / 8 == 7) return true;
            return false;
        }


        private void assignTileColor() {
            if (isFirstRow(this.tileId) || isThirdRow(this.tileId) ||
                    isFifthRow(this.tileId) || isSeventhRow(this.tileId)) {
                setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
            } else if (isSecondRow(this.tileId) || isFourthRow(this.tileId) ||
                    isSixthRow(this.tileId) || isEightRow(this.tileId)) {
                setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
            }
        }
    }
}


