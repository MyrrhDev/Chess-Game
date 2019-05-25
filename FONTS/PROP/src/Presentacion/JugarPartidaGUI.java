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

public class JugarPartidaGUI {
    private JPanel panel1;
    private JRadioButton radioButtonTurnoJugador2;
    private JRadioButton radioButtonTurnoJugador1;
    private JPanel tableroPlace;
    private JPanel panelmain;
    private JLabel tiempoLabel;
    private JLabel jugadornegro;
    private JLabel jugadorblanco;
    private JPanel butttonpanel;
    private JButton button1;
    private JPanel panelButton;
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
        System.out.println("creo el objeto Jugar Partida GUI");
        this.frame = new JFrame("Logic: Entorno de resolución de problemas de ajedrez");
        this.frame.setPreferredSize(new Dimension(900, 750));
        this.frame.setContentPane(panelmain);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
        cronometro = new Cronometro();
        Thread threadCronometro = new Thread(cronometro);
        this.nJugador1 = n;
        this.nJugador2 = n;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        if(this.jugador1.equals("H1")) jugadorblanco.setText("Jugador: " + ctrl_presentacion.getInstance().getNombreJugadorSesionH1());
        else if(this.jugador1.equals("H2")) jugadorblanco.setText("Jugador: " + ctrl_presentacion.getInstance().getNombreJugadorSesionH2());
        else jugadorblanco.setText("Jugador: " + jugador1);
        if(this.jugador2.equals("H1")) jugadornegro.setText("Jugador: " + ctrl_presentacion.getInstance().getNombreJugadorSesionH1());
        else if(this.jugador2.equals("H2"))jugadornegro.setText("Jugador: " + ctrl_presentacion.getInstance().getNombreJugadorSesionH2());
        else jugadornegro.setText("Jugador: " + jugador2);
        radioButtonTurnoJugador1.setSelected(true);
        radioButtonTurnoJugador2.setSelected(false);
        if(jugador1.equals("M1") || jugador1.equals("M2") || jugador2.equals("M1") || jugador2.equals("M2")) {
            button1.setVisible(true);
            button1.setText("Calcular siguiente movimiento de la Máquina");
        } else button1.setVisible(false);
        System.out.println("acabo la creadora");

        button1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jugador1.equals("M1") || jugador1.equals("M2") || jugador2.equals("M1") || jugador2.equals("M2")) {
                    if((jugador1.equals("M1") && !turnoJugador1 && !jugador2.equals("M1") && !jugador2.equals("M2")) || (jugador1.equals("M2") && !turnoJugador1 && !jugador2.equals("M1") && !jugador2.equals("M2")) ||
                            (jugador2.equals("M1") && !turnoJugador2 && !jugador1.equals("M1") && !jugador1.equals("M2")) || (jugador2.equals("M2") && !turnoJugador2 && !jugador1.equals("M1") && !jugador1.equals("M2"))) {
                        displayFrameMessage("No es el turno de la maquina", 300, 100);
                    }
                    else jugar();
                }
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


    /*
    Pre: Cierto
    Post: Se muestra en un frame nuevo el mensaje pasado por parámetro
     */
    private void displayFrameMessage(final String message, final int width, final int height) {
        JFrame jframeMessage = new JFrame();
        JLabel errorText = new JLabel(message);
        jframeMessage.add(errorText);
        jframeMessage.setSize(new Dimension(width, height));
        jframeMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframeMessage.setVisible(true);
    }

    public void jugar() {
        System.out.println("jugar");
        if(((this.jugador1.equals("M1") || this.jugador1.equals("M2")) && turnoJugador1 && nJugador1 > 0)  ||
                ((this.jugador2.equals("M1") || this.jugador2.equals("M2")) && turnoJugador2 && nJugador2 > 0)) { //si j1 es maquina i es turno j1 o si j2 es maquina i es turno de j2
            System.out.println("dentro " + nJugador1 + " " + nJugador2);
            try {
                char[] tableroAntes = ctrl_presentacion.getInstance().getTablero();
                if(turnoJugador1) {
                    System.out.println("es turno j1");
                    ctrl_presentacion.getInstance().jugar(nJugador1);
                }
                else if(turnoJugador2) {
                    System.out.println("es turno j2");
                    ctrl_presentacion.getInstance().jugar(nJugador2);
                }
                char[] tableroDespues = ctrl_presentacion.getInstance().getTablero();
                /*if (Arrays.equals(tableroAntes, tableroDespues)) System.out.println("iguales");
                else System.out.println("distintos");*/
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
                this.boardPanel.drawBoard(ctrl_presentacion.getInstance().getTablero());
            } catch (Exception e) {
                if(e.getMessage().equals("Jugador 2 en Jaque mate")) {
                    displayFrameMessage("Ha ganado " + jugador1, 300, 100); //j1 ha ganado
                    cronometro.pararCronometro();
                }
                else {
                    displayFrameMessage("Ha ganado " + jugador2, 300, 100); //j2 ha ganado
                    cronometro.pararCronometro();
                }

            }
        }
    }

    /*
    Componentes customizados del GUI Form
     */
    private void createUIComponents() {
        // TODO: place custom component creation code here
        tableroPlace = new JPanel();
        this.boardPanel = new BoardPanel();
        tableroPlace.add(boardPanel);
    }

    /*
    Clase privada para pintar el tablero en un JPanel
     */
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

    /*
    Clase privada para gestionar los recuadros del tablero
     */
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
            //movimiento pieza de un humano
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if (isRightMouseButton(e)) {
                        //unselect every selection made
                        sourceTile = -1;
                        destinationTile = -1;
                    } else if (isLeftMouseButton(e)) {
                        //@TODO: Verificar que realment sigui el torn de l'humà
                        if((!jugador1.equals("M1") && !jugador1.equals("M2") && turnoJugador1) || (!jugador2.equals("M1") && !jugador2.equals("M2") && turnoJugador2)) {
                            if (sourceTile == -1) {
                                if (esMovimientoGUIOk(tileId)) sourceTile = tileId;
                                //System.out.println("primer click tileId: " + tileId + " x " + tileId / 8 + " y " + tileId % 8 + " sourceTile: " + sourceTile);
                            } else {
                                destinationTile = tileId;
                                //System.out.println("segundo click " + tileId + " x " + tileId / 8 + " y " + tileId % 8 + " destinationTile: " + destinationTile);
                                try {
                                    if (esMovimientoGUIOk(sourceTile)) {
                                        ctrl_presentacion.getInstance().jugar(sourceTile / 8, sourceTile % 8, destinationTile / 8, destinationTile % 8);
                                        //Cambiamos el indicador de quien es el turno
                                        if (radioButtonTurnoJugador1.isSelected()) {
                                            radioButtonTurnoJugador1.setSelected(false);
                                            --nJugador1;
                                            turnoJugador1 = false;
                                            radioButtonTurnoJugador2.setSelected(true);
                                            turnoJugador2 = true;
                                        } else {
                                            radioButtonTurnoJugador2.setSelected(false);
                                            turnoJugador2 = false;
                                            radioButtonTurnoJugador1.setSelected(true);
                                            turnoJugador1 = true;
                                            --nJugador2;
                                        }
                                        if (nJugador1 == 0) { //fin de la partida
                                            cronometro.pararCronometro();
                                            //verificamos quien gana: J1 si J2 está en mate o J2 si no está en mate
                                            if (ctrl_presentacion.getInstance().isJ2EnJaqueMate())
                                                displayFrameMessage("TODO: Hacer get nombre H1: Ha ganado!!", 300, 100);
                                            else
                                                displayFrameMessage("TODO: Hacer get nombre H2: Ha ganado!!", 300, 100);
                                        }
                                    }
                                    boardPanel.drawBoard(ctrl_dominio.getInstance().getTablero());
                                } catch (Exception e1) {
                                    if (e1.getMessage().contains("No value present"))
                                        displayFrameMessage("Movimiento incorrecto", 200, 100);
                                    else if (e1.getMessage().contains("No se puede ejecutar el movimiento"))
                                        displayFrameMessage("No se puede ejecutar el movimiento", 300, 100);
                                    else e1.printStackTrace();
                                }
                                sourceTile = -1;
                                destinationTile = -1;
                            }
                        } else
                            displayFrameMessage("No es el turno del jugador", 300, 100);
                    }
                }

                private boolean esMovimientoGUIOk(final int tileId) {
                    return(ctrl_presentacion.getInstance().getTablero()[tileId] != '0');
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
                        im = ImageIO.read(new File("./res/Rook.gif"));
                    } else if(tablero[tileId] == 'N') {
                        im = ImageIO.read(new File("./res/Norse.gif"));
                    } else {
                        im = ImageIO.read(new File("./res/" + tablero[tileId] + ".gif"));
                    }
                    add(new JLabel(new ImageIcon(im)));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al cargar la imagen de una pieza");
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

    /*
    Clase privada que implementa el cronómetro
     */
    private class Cronometro extends JFrame implements Runnable
    {
        Thread hilo;

        public Cronometro()
        {
            tiempoLabel.setText("00:00:000");
            tiempoLabel.setHorizontalAlignment(JLabel.CENTER);
            tiempoLabel.setOpaque(true);
            hilo = new Thread(this);
            hilo.start();
        }

        public void run() {
            Integer minutos = 0 , segundos = 0, milesimas = 0;
            String min="", seg="", mil="";
            try {
                while(cronometroActivo) {
                    Thread.sleep( 4 );
                    milesimas += 4;
                    if( milesimas == 1000 ) {
                        milesimas = 0;
                        segundos += 1;
                        if( segundos == 60 ) {
                            segundos = 0;
                            minutos++;
                        }
                    }
                    if( minutos < 10 ) min = "0" + minutos;
                    else min = minutos.toString();
                    if( segundos < 10 ) seg = "0" + segundos;
                    else seg = segundos.toString();
                    if( milesimas < 10 ) mil = "00" + milesimas;
                    else if( milesimas < 100 ) mil = "0" + milesimas;
                    else mil = milesimas.toString();
                    tiempoLabel.setText( min + ":" + seg + ":" + mil );
                }
            }catch(Exception e){}
        }

        public void pararCronometro(){
            cronometroActivo = false;
        }
    }
}