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
import java.util.Arrays;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class JugarPartidaGUI {
    private JPanel panel1;
    private JRadioButton radioButtonTurnoJugador2;
    private JRadioButton radioButtonTurnoJugador1;
    private JPanel tableroPlace;
    private JPanel panelmain;
    private JLabel tiempoLabel;
    private JLabel jugadornegro;
    private JLabel jugadorblanco;
    private JFrame frame;
    private int sourceTile = -1;
    private int destinationTile = -1;
    private boolean cronometroActivo = true;
    private Cronometro cronometro;
    private String jugador1 = "";
    private String jugador2 = "";
    private int nJugador2;
    private int nJugador1;
    private BoardPanel boardPanel;
    private boolean turnoJugador1 = true, turnoJugador2 = false;

    public JugarPartidaGUI(String jugador1, String jugador2, int n) {
        frame = new JFrame("Logic: Entorno de resolución de problemas de ajedrez");
        frame.setPreferredSize(new Dimension(900, 750));
        frame.setContentPane(panelmain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        cronometro = new Cronometro();
        Thread threadCronometro = new Thread(cronometro);
        this.nJugador1 = n;
        this.nJugador2 = n;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        if(this.jugador1.equals("H1")) jugadorblanco.setText("get ctrl persist");
        else jugadorblanco.setText("Jugador: " + jugador1);
        if(this.jugador2.equals("H1")) jugadornegro.setText("get ctrl persist");
        else jugadornegro.setText("Jugador: " + jugador2);
        radioButtonTurnoJugador1.setSelected(true);
        radioButtonTurnoJugador2.setSelected(false);
        /*if((jugador1.equals("M1") && jugador2.equals("M1")) || (jugador1.equals("M1") && jugador2.equals("M2")) || (jugador1.equals("M2") && jugador2.equals("M1")) || (jugador1.equals("M2") && jugador2.equals("M2"))) {
            jugarMaquinas();
        }*/
        if((jugador1.equals("M1") || jugador1.equals("M2")) && !(jugador2.equals("M1") || jugador2.equals("M2")) ||
                (jugador2.equals("M1") || jugador2.equals("M2")) && !(jugador1.equals("M1") || jugador1.equals("M2"))) jugar();
    }

    public int getnJugador2() {
        return nJugador2;
    }

    public int getnJugador1() {
        return nJugador1;
    }

    public void jugar() {
        System.out.println("jugar");
        if(((this.jugador1.equals("M1") || this.jugador1.equals("M2")) && turnoJugador1 && nJugador1 > 0)  ||
                ((this.jugador2.equals("M1") || this.jugador2.equals("M2")) && turnoJugador2 && nJugador2 > 0)) { //si j1 es maquina i es turno j1 o si j2 es maquina i es turno de j2
            System.out.println("dentro " + nJugador1 + " " + nJugador2);
            try {
                char[] tableroAntes = ctrl_dominio.getInstance().getTablero();
                if(turnoJugador1) ctrl_dominio.getInstance().jugar(nJugador1);
                else if(turnoJugador2) ctrl_dominio.getInstance().jugar(nJugador2);
                char[] tableroDespues = ctrl_dominio.getInstance().getTablero();
                if (Arrays.equals(tableroAntes, tableroDespues)) System.out.println("iguales");
                else System.out.println("distintos");
                if((this.jugador1.equals("M1") || this.jugador1.equals("M2")) && turnoJugador1) --nJugador1;
                else if((this.jugador2.equals("M1") || this.jugador2.equals("M2")) && turnoJugador2) --nJugador2;
                if (radioButtonTurnoJugador1.isSelected()) {
                    radioButtonTurnoJugador1.setSelected(false);
                    turnoJugador1 = false;
                    radioButtonTurnoJugador2.setSelected(true);
                    turnoJugador2 = true;
                } else {
                    radioButtonTurnoJugador2.setSelected(false);
                    turnoJugador2 = false;
                    radioButtonTurnoJugador1.setSelected(true);
                    turnoJugador1 = true;
                }
                boardPanel.drawBoard(ctrl_dominio.getInstance().getTablero());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        tableroPlace = new JPanel();
        this.boardPanel = new BoardPanel();
        tableroPlace.add(boardPanel);
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


        public void printTablero(char[] tablero) {
            //System.out.println(Arrays.toString(tablero));
        }

        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(new Dimension(10, 10));
            assignTileColor();
            assignTilePiceIcon(ctrl_dominio.getInstance().getTablero());
            printTablero(ctrl_dominio.getInstance().getTablero());

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if (isRightMouseButton(e)) {
                        //unselect every selection made
                        sourceTile = -1;
                        destinationTile = -1;
                    } else if (isLeftMouseButton(e)) {
                        if (sourceTile == -1) {
                            if(esMovimientoGUIOk(tileId)) sourceTile = tileId;
                            //System.out.println("primer click tileId: " + tileId + " x " + tileId / 8 + " y " + tileId % 8 + " sourceTile: " + sourceTile);
                        } else {
                            destinationTile = tileId;
                            //System.out.println("segundo click " + tileId + " x " + tileId / 8 + " y " + tileId % 8 + " destinationTile: " + destinationTile);
                            try {
                                if(esMovimientoGUIOk(sourceTile)) {
                                    ctrl_dominio.getInstance().jugar(sourceTile / 8, sourceTile % 8, destinationTile / 8, destinationTile % 8);
                                    if(radioButtonTurnoJugador1.isSelected()) {
                                        radioButtonTurnoJugador1.setSelected(false);
                                        --nJugador1;
                                        turnoJugador1 = false;
                                        radioButtonTurnoJugador2.setSelected(true);
                                        turnoJugador2 = true;
                                    }
                                    else {
                                        radioButtonTurnoJugador2.setSelected(false);
                                        turnoJugador2 = false;
                                        radioButtonTurnoJugador1.setSelected(true);
                                        turnoJugador1 = true;
                                        --nJugador2;
                                        }
                                    if(nJugador1 == 0) { //fin de la partida
                                        cronometro.pararCronometro();
                                        //verificamos quien gana: J1 si J2 está en mate o J2 si no está en mate
                                        if(ctrl_dominio.getInstance().isJ2EnJaqueMate()) {
                                            JFrame jframeWinner = new JFrame();
                                            JLabel errorText = new JLabel("TODO: Hacer get nombre H1: Ha ganado!!");
                                            jframeWinner.add(errorText);
                                            jframeWinner.setSize(new Dimension(300, 100));
                                            jframeWinner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                            jframeWinner.setVisible(true);
                                        }
                                        else {
                                            JFrame jframeWinner = new JFrame();
                                            JLabel errorText = new JLabel("TODO: Hacer get nombre H2: Ha ganado!!");
                                            jframeWinner.add(errorText);
                                            jframeWinner.setSize(new Dimension(300, 100));
                                            jframeWinner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                            jframeWinner.setVisible(true);
                                        }
                                    }
                                }
                                boardPanel.drawBoard(ctrl_dominio.getInstance().getTablero());
                            } catch (Exception e1) {
                                //e1.printStackTrace();
                                if(e1.getMessage().contains("No value present")) {
                                    JFrame jerror = new JFrame();
                                    JLabel errorText = new JLabel("Movimiento incorrecto");
                                    jerror.add(errorText);
                                    jerror.setSize(new Dimension(200, 100));
                                    jerror.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    jerror.setVisible(true);
                                }
                                else if(e1.getMessage().contains("No se puede ejecutar el movimiento")) {
                                    JFrame jerror = new JFrame();
                                    JLabel errorText = new JLabel("No se puede ejecutar el movimiento");
                                    jerror.add(errorText);
                                    jerror.setSize(new Dimension(300, 100));
                                    jerror.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    jerror.setVisible(true);
                                    e1.printStackTrace();
                                }
                                else e1.printStackTrace();
                            }
                            sourceTile = -1;
                            destinationTile = -1;
                            jugar();
                        }
                    }
                }

                private boolean esMovimientoGUIOk(final int tileId) {
                    return(ctrl_dominio.getInstance().getTablero()[tileId] != '0');
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
                        im = ImageIO.read(new File("./res/Rock.gif"));
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

    private class Cronometro extends JFrame implements Runnable
    {
        Thread hilo;

        public Cronometro()
        {
            //Etiqueta donde se colocara el tiempo
            tiempoLabel.setText("00:00:000");
            //tiempoLabel.setFont(new Font(Font.SERIF, Font.BOLD, 15));
            tiempoLabel.setHorizontalAlignment(JLabel.CENTER);
            tiempoLabel.setOpaque(true);
            hilo = new Thread(this);
            hilo.start();
        }

        public void run() {
            Integer minutos = 0 , segundos = 0, milesimas = 0;
            //min es minutos, seg es segundos y mil es milesimas de segundo
            String min="", seg="", mil="";
            try
            {
                while(cronometroActivo) {
                    Thread.sleep( 4 );
                    //Incrementamos 4 milesimas de segundo
                    milesimas += 4;
                    //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                    //y las milesimas de segundo de nuevo a 0
                    if( milesimas == 1000 ) {
                        milesimas = 0;
                        segundos += 1;
                        //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                        //y los segundos vuelven a 0
                        if( segundos == 60 ) {
                            segundos = 0;
                            minutos++;
                        }
                    }
                    //Esto solamente es estetica para que siempre este en formato
                    //00:00:000
                    if( minutos < 10 ) min = "0" + minutos;
                    else min = minutos.toString();
                    if( segundos < 10 ) seg = "0" + segundos;
                    else seg = segundos.toString();
                    if( milesimas < 10 ) mil = "00" + milesimas;
                    else if( milesimas < 100 ) mil = "0" + milesimas;
                    else mil = milesimas.toString();

                    //Colocamos en la etiqueta la informacion
                    tiempoLabel.setText( min + ":" + seg + ":" + mil );
                }
            }catch(Exception e){}
        }

        //Esto es para parar el cronometro
        public void pararCronometro(){
            cronometroActivo = false;
        }
    }
}