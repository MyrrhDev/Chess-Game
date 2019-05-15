package Presentacion;

import Domini.ctrl_dominio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class TableroGUI {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final String imgPiecesPath = "./res";
    private final ctrl_dominio c;
    private int sourceTile = -1;
    private int destinationTile = -1;

    public TableroGUI() {
        c = ctrl_dominio.getInstance();
        this.gameFrame = new JFrame("Chess Game");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(new Dimension(600, 600));
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
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
             setPreferredSize(new Dimension(400, 350));
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
            assignTilePiceIcon(c.getTablero());

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
                                //c.jugar(1,1,1,1);
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
                                                    '0', '0', '0', '0', 'P', '0', '0', '0',
                                                    '0', '0', 'K', '0', '0', '0', 'p', 'P'};
                                    //aqui iria c.getTablero() y le pasaríamos ese tablero a la función drawBoard()
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
                    final BufferedImage im = ImageIO.read(new File("./res/" + tablero[tileId] + ".gif"));
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
        new TableroGUI();
    }

}
