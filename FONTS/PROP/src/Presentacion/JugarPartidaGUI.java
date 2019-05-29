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
import java.util.Date;
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
    private JButton abandonarPartidaButton;
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
    private int nTotal;
    private BoardPanel boardPanel;
    private String FEN;
    private String dificultad;

    public JugarPartidaGUI(String jugador1, String jugador2, String FEN, int n, String dificultad) {
        System.out.println("creo el objeto Jugar Partida GUI");
        this.frame = new JFrame("Logic: Entorno de resolución de problemas de ajedrez");
        this.frame.setPreferredSize(new Dimension(1050, 800));
        this.frame.setContentPane(panelmain);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
        cronometro = new Cronometro();
        Thread threadCronometro = new Thread(cronometro);
        this.FEN = FEN;
        this.nTotal = n;
        this.nJugador1 = n;
        this.nJugador2 = n;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        if(this.jugador1.equals("H1")) {
            ctrl_presentacion.getInstance().incrementarPartidaJugada(ctrl_presentacion.getInstance().getNombreJugadorSesionH1());
        System.out.println("1" + ctrl_presentacion.getInstance().getNombreJugadorSesionH1());
        }
        else if(this.jugador1.equals("H2")) {
            ctrl_presentacion.getInstance().incrementarPartidaJugada(ctrl_presentacion.getInstance().getNombreJugadorSesionH2());
            System.out.println("2");
        }
        if(this.jugador2.equals("H1")) {
            ctrl_presentacion.getInstance().incrementarPartidaJugada(ctrl_presentacion.getInstance().getNombreJugadorSesionH1());
            System.out.println("3");

        }
        else if(this.jugador2.equals("H2")) {
            ctrl_presentacion.getInstance().incrementarPartidaJugada(ctrl_presentacion.getInstance().getNombreJugadorSesionH2());
            System.out.println("4" + ctrl_presentacion.getInstance().getNombreJugadorSesionH2());
        }
        this.dificultad = dificultad;
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
            /*@Override
            public void mouseClicked(MouseEvent e) {
                if(jugador1.equals("M1") || jugador1.equals("M2") || jugador2.equals("M1") || jugador2.equals("M2")) {
                    if((jugador1.equals("M1") && !turnoJugador1 && !jugador2.equals("M1") && !jugador2.equals("M2")) || (jugador1.equals("M2") && !turnoJugador1 && !jugador2.equals("M1") && !jugador2.equals("M2")) ||
                            (jugador2.equals("M1") && !turnoJugador2 && !jugador1.equals("M1") && !jugador1.equals("M2")) || (jugador2.equals("M2") && !turnoJugador2 && !jugador1.equals("M1") && !jugador1.equals("M2"))) {
                        displayFrameMessage("No es el turno de la maquina", 300, 100);
                    }
                    else jugar();
                }
            }*/
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jugador1.equals("M1") || jugador1.equals("M2") || jugador2.equals("M1") || jugador2.equals("M2")) {
                    if((jugador1.equals("M1") &&  !radioButtonTurnoJugador1.isSelected() && !jugador2.equals("M1") && !jugador2.equals("M2")) || (jugador1.equals("M2") && !radioButtonTurnoJugador1.isSelected() && !jugador2.equals("M1") && !jugador2.equals("M2")) ||
                            (jugador2.equals("M1") && !radioButtonTurnoJugador2.isSelected() && !jugador1.equals("M1") && !jugador1.equals("M2")) || (jugador2.equals("M2") && !radioButtonTurnoJugador2.isSelected() && !jugador1.equals("M1") && !jugador1.equals("M2"))) {
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

        abandonarPartidaButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cronometro.pararCronometro();
                if(radioButtonTurnoJugador1.isSelected()) { //abandona J1
                    if(jugador2.equals("H1") || jugador2.equals("H2")) {
                        ctrl_presentacion.getInstance().incrementarPartidaGanada(ctrl_presentacion.getInstance().getNombreJugadorSesionH2());
                        long tiempoFinalProblema = new Date().getTime();
                        long tiempo = tiempoFinalProblema - boardPanel.getTiempoInicioProblema();
                        int tpartida = (int) tiempo/60000;
                        if(tpartida == 0) tpartida = 1;
                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(ctrl_presentacion.getInstance().getNombreJugadorSesionH2(), FEN, nTotal, jugador1, dificultad, tpartida);
                    }
                }
                else { //abandona J2
                    if(jugador1.equals("H1") || jugador1.equals("H2")) {
                        ctrl_presentacion.getInstance().incrementarPartidaGanada(ctrl_presentacion.getInstance().getNombreJugadorSesionH1());
                        long tiempoFinalProblema = new Date().getTime();
                        long tiempo = tiempoFinalProblema - boardPanel.getTiempoInicioProblema();
                        int tpartida = (int) tiempo/60000;
                        if(tpartida == 0) tpartida = 1;
                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(ctrl_presentacion.getInstance().getNombreJugadorSesionH1(), FEN, nTotal, jugador1, dificultad, tpartida);
                    }
                }
                JOptionPane.showMessageDialog(null, "Has abandonado la partida. Tu oponente es el ganador");
                frame.setVisible(false);
                new MenuPrincipalGUI();
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
        if(message.contains("ha ganado")) jframeMessage.setLayout(new GridLayout(2, 0));
        JLabel errorText = new JLabel(message);
        jframeMessage.add(errorText);
        JButton atrasJbutton = new JButton("Volver al menú");
        if(message.contains("ha ganado")) jframeMessage.add(atrasJbutton);
        atrasJbutton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MenuPartidaGUI();
                jframeMessage.setVisible(false);
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
        jframeMessage.setSize(new Dimension(width, height));
        jframeMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframeMessage.setVisible(true);
    }

    /*
    Pre: Cierto
    Post: Jugar se ha encargado de verificar y ejecutar un movimiento de la maquina
     */
    public void jugar() {
        System.out.println("jugar");
        //if(((this.jugador1.equals("M1") || this.jugador1.equals("M2")) && turnoJugador1 && nJugador1 > 0)  ||
        //                ((this.jugador2.equals("M1") || this.jugador2.equals("M2")) && turnoJugador2 && nJugador2 > 0)) {
        if(((this.jugador1.equals("M1") || this.jugador1.equals("M2")) && radioButtonTurnoJugador1.isSelected() && nJugador1 > 0)  ||
                ((this.jugador2.equals("M1") || this.jugador2.equals("M2")) && radioButtonTurnoJugador2.isSelected() && nJugador2 > 0)) { //si j1 es maquina i es turno j1 o si j2 es maquina i es turno de j2
            System.out.println("dentro " + nJugador1 + " " + nJugador2);
            try {
                char[] tZableroAntes = ctrl_presentacion.getInstance().getTablero();
                /*if(turnoJugador1 && nJugador1 > 0) {
                    System.out.println("es turno j1");
                    ctrl_presentacion.getInstance().jugar(nJugador1);
                }*/
                if(radioButtonTurnoJugador1.isSelected() && nJugador1 > 0) {
                    System.out.println("es turno j1");
                    ctrl_presentacion.getInstance().jugar(nJugador1);
                }
                else if(nJugador1 == 0) { //y el jugador 2 no está en jaque mate
                    if(jugador2.equals("M1")) displayFrameMessage("El jugador M1 ha ganado la partida", 350, 200);
                    else if (jugador2.equals("M2")) displayFrameMessage("El jugador M2 ha ganado la partida", 350, 200);
                }
                //si nj1 == 0 y el jugador 2 está en mate gana nj1 por mucho que nj1 == 0
                /*if(turnoJugador2 && nJugador2 > 0) {
                    System.out.println("es turno j2");
                    ctrl_presentacion.getInstance().jugar(nJugador2);
                }*/
                if(radioButtonTurnoJugador2.isSelected() && nJugador2 > 0) {
                    System.out.println("es turno j2");
                    ctrl_presentacion.getInstance().jugar(nJugador2);
                }
                else if(nJugador2 == 0) { //y j1 no está en jaque mate
                    if(jugador1.equals("M1")) displayFrameMessage("El jugador M1 ha ganado la partida", 350, 200);
                    else if (jugador1.equals("M2")) displayFrameMessage("El jugador M2 ha ganado la partida", 350, 200);
                }
                char[] tableroDespues = ctrl_presentacion.getInstance().getTablero();
                /*if (Arrays.equals(tableroAntes, tableroDespues)) System.out.println("iguales");
                else System.out.println("distintos");*/
                //if((this.jugador1.equals("M1") || this.jugador1.equals("M2")) && turnoJugador1) --nJugador1;
                if((this.jugador1.equals("M1") || this.jugador1.equals("M2")) && radioButtonTurnoJugador1.isSelected()) --nJugador1;
                //else if((this.jugador2.equals("M1") || this.jugador2.equals("M2")) && turnoJugador2) --nJugador2;
                else if((this.jugador2.equals("M1") || this.jugador2.equals("M2")) && radioButtonTurnoJugador2.isSelected()) --nJugador2;
                if (radioButtonTurnoJugador1.isSelected()) {
                    radioButtonTurnoJugador1.setSelected(false);
                    //turnoJugador1 = false;
                    radioButtonTurnoJugador2.setSelected(true);
                    //turnoJugador2 = true;
                } else {
                    radioButtonTurnoJugador2.setSelected(false);
                    //turnoJugador2 = false;
                    radioButtonTurnoJugador1.setSelected(true);
                    //turnoJugador1 = true;
                }
                this.boardPanel.drawBoard(ctrl_presentacion.getInstance().getTablero());
            } catch (Exception e) {
                if(e.getMessage().equals("Jugador 2 en Jaque mate")) {
                    if(jugador1.equals("H1")) {
                        displayFrameMessage("Ha ganado " + ctrl_presentacion.getInstance().getNombreJugadorSesionH1(), 300, 100); //j1 ha ganado
                        long tiempoFinalProblema = new Date().getTime();
                        long tiempo = tiempoFinalProblema - boardPanel.getTiempoInicioProblema();
                        int tiempoAguardar = (int) tiempo/60000;
                        if(tiempoAguardar == 0) tiempoAguardar = 1;
                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(ctrl_presentacion.getInstance().getNombreJugadorSesionH1(), FEN, nTotal, jugador1, dificultad, tiempoAguardar);
                        ctrl_presentacion.getInstance().incrementarPartidaGanada(ctrl_presentacion.getInstance().getNombreJugadorSesionH1());
                    }
                    else if(jugador1.equals("H2")) {
                        displayFrameMessage("Ha ganado " + ctrl_presentacion.getInstance().getNombreJugadorSesionH2(), 300, 100); //j2 ha ganado
                        long tiempoFinalProblema = new Date().getTime();
                        long tiempo = tiempoFinalProblema - boardPanel.getTiempoInicioProblema();
                        int tiempoAguardar = (int) tiempo/60000;
                        if(tiempoAguardar == 0) tiempoAguardar = 1;
                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(ctrl_presentacion.getInstance().getNombreJugadorSesionH2(), FEN, nTotal, jugador1, dificultad, tiempoAguardar);
                        ctrl_presentacion.getInstance().incrementarPartidaGanada(ctrl_presentacion.getInstance().getNombreJugadorSesionH2());

                    }
                    cronometro.pararCronometro();
                }
                else {
                    if(jugador1.equals("H1")) {
                        displayFrameMessage("Ha ganado " + ctrl_presentacion.getInstance().getNombreJugadorSesionH1(), 300, 100); //j1 ha ganado
                        long tiempoFinalProblema = new Date().getTime();
                        long tiempo = tiempoFinalProblema - boardPanel.getTiempoInicioProblema();
                        int tiempoAguardar = (int) tiempo/60000;
                        if(tiempoAguardar == 0) tiempoAguardar = 1;
                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(ctrl_presentacion.getInstance().getNombreJugadorSesionH1(), FEN, nTotal, jugador1, dificultad, tiempoAguardar);
                        ctrl_presentacion.getInstance().incrementarPartidaGanada(ctrl_presentacion.getInstance().getNombreJugadorSesionH1());
                    }
                    else if(jugador1.equals("H2")) {
                        displayFrameMessage("Ha ganado " + ctrl_presentacion.getInstance().getNombreJugadorSesionH2(), 300, 100); //j2 ha ganado
                        long tiempoFinalProblema = new Date().getTime();
                        long tiempo = tiempoFinalProblema - boardPanel.getTiempoInicioProblema();
                        int tiempoAguardar = (int) tiempo/60000;
                        if(tiempoAguardar == 0) tiempoAguardar = 1;
                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(ctrl_presentacion.getInstance().getNombreJugadorSesionH2(), FEN, nTotal, jugador1, dificultad, tiempoAguardar);
                        ctrl_presentacion.getInstance().incrementarPartidaGanada(ctrl_presentacion.getInstance().getNombreJugadorSesionH2());

                    }
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
        boardPanel.setPreferredSize(new Dimension(600, 600));
        tableroPlace.add(boardPanel);
    }

    /*
    Clase privada para pintar el tablero en un JPanel
     */
    private class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;
        private long tiempoInicioProblema;

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
            this.tiempoInicioProblema = new Date().getTime(); //tiempo inicial en milisegundos
        }

        public long getTiempoInicioProblema() {
            return this.tiempoInicioProblema;
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
                        //if((!jugador1.equals("M1") && !jugador1.equals("M2") && turnoJugador1) || (!jugador2.equals("M1") && !jugador2.equals("M2") && turnoJugador2)) {
                        if((!jugador1.equals("M1") && !jugador1.equals("M2") && radioButtonTurnoJugador1.isSelected()) || (!jugador2.equals("M1") && !jugador2.equals("M2") && radioButtonTurnoJugador2.isSelected())) {
                            if (sourceTile == -1) {
                                if (esMovimientoGUIOk(tileId)) sourceTile = tileId;
                                //System.out.println("primer click tileId: " + tileId + " x " + tileId / 8 + " y " + tileId % 8 + " sourceTile: " + sourceTile);
                            } else {
                                destinationTile = tileId;
                                //System.out.println("segundo click " + tileId + " x " + tileId / 8 + " y " + tileId % 8 + " destinationTile: " + destinationTile);
                                try {
                                    if (esMovimientoGUIOk(sourceTile)) {
                                        if (nJugador1 == 0 && (jugador1.equals("H1") || jugador1.equals("H2")) && radioButtonTurnoJugador1.isSelected()) { //fin de la partida
                                            System.out.println("nJugador1 == 0");
                                            cronometro.pararCronometro();
                                            long tiempoFinalProblema = new Date().getTime();
                                            long tiempo = tiempoFinalProblema - boardPanel.getTiempoInicioProblema();
                                            int tiempoAguardar = (int) tiempo/60000;
                                            if(tiempoAguardar == 0) tiempoAguardar = 1;
                                            //verificamos quien gana: J1 si J2 está en mate o J2 si no está en mate
                                            if (ctrl_presentacion.getInstance().isJ2EnJaqueMate()) {
                                                if(!jugador1.equals("M1") && !jugador1.equals("M2")) {
                                                    String nombre = "";
                                                    if(jugador1.equals("H1")) {
                                                        nombre = ctrl_presentacion.getInstance().getNombreJugadorSesionH1();
                                                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(nombre, FEN, nTotal, jugador2,  dificultad,tiempoAguardar);
                                                        ctrl_presentacion.getInstance().incrementarPartidaGanada(nombre);
                                                    }
                                                    else if(jugador1.equals("H2")) {
                                                        nombre = ctrl_presentacion.getInstance().getNombreJugadorSesionH2();
                                                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(nombre, FEN, nTotal, jugador1,  dificultad,tiempoAguardar);
                                                        ctrl_presentacion.getInstance().incrementarPartidaGanada(nombre);
                                                    }
                                                    displayFrameMessage("el jugador " + nombre + " ha ganado la partida!", 300, 100);
                                                }
                                                else if(jugador1.equals("M1")) displayFrameMessage("el jugador M1 ha ganado la partida!", 300, 100);
                                                else if(jugador1.equals("M2")) displayFrameMessage("el jugador M2 ha ganado la partida!", 300, 100);
                                            }
                                            else {
                                                if(jugador2.equals("M1")) displayFrameMessage("el jugador M1 ha ganado la partida!", 300, 100);
                                                else if(jugador2.equals("M2")) displayFrameMessage("el jugador M2 ha ganado la partida!", 300, 100);
                                                else {
                                                    displayFrameMessage("el jugador " + ctrl_presentacion.getInstance().getNombreJugadorSesionH2() +  " ha ganado la partida!", 300, 100);
                                                    ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(ctrl_presentacion.getInstance().getNombreJugadorSesionH2(), FEN, nTotal, jugador1, dificultad, tiempoAguardar);
                                                    ctrl_presentacion.getInstance().incrementarPartidaGanada(ctrl_presentacion.getInstance().getNombreJugadorSesionH2());
                                                }

                                            }
                                        }
                                        else if(nJugador2 == 0 && (jugador2.equals("H1") || jugador2.equals("H2")) && radioButtonTurnoJugador2.isSelected()) {
                                            System.out.println("nJugador1 == 0");
                                            cronometro.pararCronometro();
                                            long tiempoFinalProblema = new Date().getTime();
                                            long tiempo = tiempoFinalProblema - boardPanel.getTiempoInicioProblema();
                                            int tiempoAguardar = (int) tiempo / 60000;
                                            if(tiempoAguardar == 0) tiempoAguardar = 1;
                                            //verificamos quien gana: J1 si J2 está en mate o J2 si no está en mate
                                            if (!ctrl_presentacion.getInstance().isJ2EnJaqueMate()) {
                                                if(!jugador2.equals("M1") && !jugador2.equals("M2")) {
                                                    String nombre = "";
                                                    if(jugador2.equals("H1")) {
                                                        nombre = ctrl_presentacion.getInstance().getNombreJugadorSesionH1();
                                                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(nombre, FEN, nTotal, jugador2,  dificultad,tiempoAguardar);
                                                        ctrl_presentacion.getInstance().incrementarPartidaGanada(nombre);
                                                    }
                                                    else if(jugador2.equals("H2")) {
                                                        nombre = ctrl_presentacion.getInstance().getNombreJugadorSesionH2();
                                                        ctrl_presentacion.getInstance().guardarProblemaGanadoJugador(nombre, FEN, nTotal, jugador1,  dificultad,tiempoAguardar);
                                                        ctrl_presentacion.getInstance().incrementarPartidaGanada(nombre);
                                                    }
                                                    displayFrameMessage("el jugador " + nombre + " ha ganado la partida!", 300, 100);
                                                }
                                                else if(jugador2.equals("M1")) displayFrameMessage("el jugador M1 ha ganado la partida!", 300, 100);
                                                else if(jugador2.equals("M2")) displayFrameMessage("el jugador M2 ha ganado la partida!", 300, 100);
                                            }
                                            else {
                                                if(jugador2.equals("M1")) displayFrameMessage("el jugador M1 ha ganado la partida!", 300, 100);
                                                else if(jugador2.equals("M2")) displayFrameMessage("el jugador M2 ha ganado la partida!", 300, 100);
                                            }
                                        }
                                        else {
                                            ctrl_presentacion.getInstance().jugar(sourceTile / 8, sourceTile % 8, destinationTile / 8, destinationTile % 8);
                                            //Cambiamos el indicador de quien es el turno
                                            if (radioButtonTurnoJugador1.isSelected()) {
                                                radioButtonTurnoJugador1.setSelected(false);
                                                --nJugador1;
                                                //turnoJugador1 = false;
                                                radioButtonTurnoJugador2.setSelected(true);
                                                //turnoJugador2 = true;
                                            } else {
                                                radioButtonTurnoJugador2.setSelected(false);
                                                //turnoJugador2 = false;
                                                radioButtonTurnoJugador1.setSelected(true);
                                                //turnoJugador1 = true;
                                                --nJugador2;
                                            }
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
                        im = ImageIO.read(new File("./res/Rock.gif"));
                    } else if(tablero[tileId] == 'N') {
                        im = ImageIO.read(new File("./res/Norse.gif"));
                    } else {
                        im = ImageIO.read(new File("./res/" + tablero[tileId] + ".gif"));
                    }
                    add(new JLabel(new ImageIcon(im)));
                } catch (Exception e) {
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