package Presentacion;

import Domini.Pieza;
import Domini.Tablero;
import Domini.ctrl_dominio;
import javafx.scene.control.Tab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TableroGUI {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final String imgPiecesPath = "./res";

    public TableroGUI() {
        this.gameFrame = new JFrame("Chess Game");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(new Dimension(600, 600));
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
    }

    private int getPosPiezaEnBoard(int x, int y) {
        if(x == 0 && y != 0) return y;
        if(x != 0 && y == 0) return x*8;
        else if(x == 0 && y == 0) return 0;
        else return x*y;
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
            assignTilePiceIcon();
        }

        //@TODO: OJO NO LI PUC PASSAR UN OBJECTE DEL DOMINI
         private void assignTilePiceIcon() {
             try {

                 final BufferedImage im = ImageIO.read(new File("./res/P.gif"));
                 add(new JLabel(new ImageIcon(im)));

             } catch(Exception e) {
                 System.out.println(e);
             }
         }

         private void assignTileColor() {
             if (BoardUtils.INSTANCE.FIRST_ROW.get(this.tileId) ||
                     BoardUtils.INSTANCE.THIRD_ROW.get(this.tileId) ||
                     BoardUtils.INSTANCE.FIFTH_ROW.get(this.tileId) ||
                     BoardUtils.INSTANCE.SEVENTH_ROW.get(this.tileId)) {
                 setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
             } else if(BoardUtils.INSTANCE.SECOND_ROW.get(this.tileId) ||
                     BoardUtils.INSTANCE.FOURTH_ROW.get(this.tileId) ||
                     BoardUtils.INSTANCE.SIXTH_ROW.get(this.tileId)  ||
                     BoardUtils.INSTANCE.EIGHTH_ROW.get(this.tileId)) {
                 setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
             }
        }
     }

    public static void main(String[] args)
    {
        new TableroGUI();
    }

}
