package Domini;

import PersistenciaJSON.ctrl_persistencia;
import Presentacion.TableroGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ctrl_dominio {
    private static Jugador jugador1, jugador2;
    private static Tablero tablero;
    private static Problema problema;
    private static ctrl_dominio singleInstance = null;
    private static ctrl_persistencia controlPersistencia;
    private static long tinicio, tfin, tpartida;

    private ctrl_dominio() {
        jugador1 = jugador2 = null;
    }

    /*
    Patron Singleton
     */
    public static ctrl_dominio getInstance() {
        if(singleInstance == null) {
            singleInstance = new ctrl_dominio();
            controlPersistencia = ctrl_persistencia.getInstance();
        }
        return singleInstance;
    }


    public static String[][] getProblemasDificultad(final String diff) {
        ArrayList<ArrayList<String>> tmpres = controlPersistencia.getProblemasDificultad(diff);
        String[][] result = new String[tmpres.size()][2];
        for(int i = 0; i < tmpres.size(); ++i) {
            for(int j = 0; j < tmpres.get(i).size(); ++j) {
                result[i][0] = tmpres.get(i).get(0);
                result[i][1] = tmpres.get(i).get(1);
            }
        }
        return result;
    }


    public boolean verificarJugador(final String nombre, final String password) throws Exception {
        try {
            if(controlPersistencia.esLoginOk(nombre, password)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public void registrarJugador(final String nombre, final String password) throws Exception {
        try {
            controlPersistencia.guardarJugador(nombre, password);
        } catch (Exception e) {
            throw e;
        }
    }


    /*
    Pre: Cierto
    Post: Tanto para jug1, como para jug2:
        Si jug = 1 -> Se crea una instancia de Persona
        Si jug = 2 -> Se crea una instancia de M1
        Si jug = 3 -> Se crea una instancia de M2
        j1EsBlanco detalla el color de piezas del jugador 1
     */
    public static void seleccionarJugadores(int jug1, int jug2, boolean j1EsBlanco) {
        switch(jug1) {
            case 1:
                if(j1EsBlanco) jugador1 = new Persona(false, true); //blanca //TODO: li he de passar es maquina, es negro i esta atacando
                else jugador1 = new Persona(true, true); //negra
                break;
            case 2:
                if(j1EsBlanco) jugador1 = new M1(false, true);
                else jugador1 = new M1(true, true);
                break;
            case 3:
                if(j1EsBlanco) jugador1 = new M2(false, true);
                else jugador1 = new M2(true, true);
                break;
        }
        switch(jug2) {
            case 1:
                if(j1EsBlanco) jugador2 = new Persona(true, false); //negra porque jug1 blanca
                else jugador2 = new Persona(false, false); //blanca porque jug1 negra
                break;
            case 2:
                if(j1EsBlanco) jugador2 = new M1(true, false);
                else jugador2 = new M1(false, false);
                break;
            case 3:
                if(j1EsBlanco) jugador2 = new M2(true, false);
                else jugador2 = new M2(false, false);
                break;
        }
    }


    /*
        Pre: Cierto
        Post: La función jugar intenta efectuar un movimiento según los parámetros de entrada
     */
    public static void jugar(int n) throws Exception {
        if(tinicio == -1 && tfin == -1) {
            tinicio = new Date().getTime();
        }
        if(!jugador1.isEnJaqueMate() && !jugador2.isEnJaqueMate()) {
            if (((jugador1.isEsNegro() && !tablero.getTurnoBlancas()) || (!jugador1.isEsNegro() && tablero.getTurnoBlancas())) && (jugador1 instanceof M1 || jugador1 instanceof  M2)) {
                try {
                    tablero = jugador1.jugar(tablero,n); // paso tablero y N
                    tablero.setTurnoBlancas(!tablero.getTurnoBlancas());
                } catch(Exception e) {
                    if(jugador2 instanceof Persona) {
                        tfin = new Date().getTime();
                        tpartida = tfin - tinicio;
                        tpartida = tpartida/60000;
                        String vs = "";
                        if(jugador1 instanceof M1) vs = "M1";
                        else if(jugador1 instanceof M2) vs = "M2";
                        else vs = "H1";
                        //cambiar nombre pepito por nombre del jugador
                        controlPersistencia.guardarProblemaGanado("pepito", problema.getFEN(), problema.getN(), vs, problema.getDificultad(), tpartida);
                        tinicio = -1;
                        tfin = -1;
                        tpartida = -1;
                    }
                    Exception e1 = new Exception("Jugador 1 en Jaque mate");
                    throw e1;
                }

            }
            if (((jugador2.isEsNegro() && !tablero.getTurnoBlancas()) || (!jugador2.isEsNegro() && tablero.getTurnoBlancas())) && (jugador2 instanceof M1 || jugador2 instanceof M2)) {
                try {
                    tablero = jugador2.jugar(tablero,n); // paso tablero y N
                    tablero.setTurnoBlancas(!tablero.getTurnoBlancas());
                } catch(Exception e) {
                    if(jugador1 instanceof Persona) {
                        tfin = new Date().getTime();
                        tpartida = tfin - tinicio;
                        tpartida = tpartida/60000;
                        String vs = "";
                        if(jugador2 instanceof M1) vs = "M1";
                        else if(jugador2 instanceof M2) vs = "M2";
                        else vs = "H2";
                        //cambiar nombre pepito por nombre del jugador
                        controlPersistencia.guardarProblemaGanado("pepito", problema.getFEN(), problema.getN(), vs, problema.getDificultad(), tpartida);
                        tinicio = -1;
                        tfin = -1;
                        tpartida = -1;
                    }
                    Exception e1 = new Exception("Jugador 2 en Jaque mate");
                    throw e1;
                }

            }
        }
        else if(jugador1.isEnJaqueMate()) {
            if(jugador2 instanceof Persona) {
                tfin = new Date().getTime();
                tpartida = tfin - tinicio;
                tpartida = tpartida/60000;
                String vs = "";
                if(jugador1 instanceof M1) vs = "M1";
                else if(jugador1 instanceof M2) vs = "M2";
                else vs = "H1";
                //cambiar nombre pepito por nombre del jugador
                controlPersistencia.guardarProblemaGanado("pepito", problema.getFEN(), problema.getN(), vs, problema.getDificultad(), tpartida);
                tinicio = -1;
                tfin = -1;
                tpartida = -1;
            }
            Exception e = new Exception("Jugador 1 en Jaque mate");
            throw e;
        }
        else if(jugador2.isEnJaqueMate()) {
            if(jugador1 instanceof Persona) {
                tfin = new Date().getTime();
                tpartida = tfin - tinicio;
                tpartida = tpartida/60000;
                String vs = "";
                if(jugador2 instanceof M1) vs = "M1";
                else if(jugador2 instanceof M2) vs = "M2";
                else vs = "H2";
                //cambiar nombre pepito por nombre del jugador
                controlPersistencia.guardarProblemaGanado("pepito", problema.getFEN(), problema.getN(), vs, problema.getDificultad(), tpartida);
                tinicio = -1;
                tfin = -1;
                tpartida = -1;
            }
            Exception e = new Exception("Jugador 2 en Jaque mate");
            throw e;
        }
    }

    public char[][] getTableroPrint() {
        return tablero.getTablero();
    }



    public char[] getTablero() {
        //return tablero.getTablero();

        //For testing pruposes
        char[] tablero = {'K', '0', '0', '0', 'n', '0', 'P', '0',
                            '0', '0', '0', '0', '0', '0', '0', '0',
                            '0', '0', '0', '0', 'P', '0', 'k', '0',
                            '0', '0', 'P', '0', '0', '0', '0', '0',
                            '0', '0', '0', '0', '0', 'Q', '0', '0',
                            '0', '0', '0', '0', '0', '0', '0', '0',
                            '0', 'Q', '0', '0', 'P', '0', '0', '0',
                            '0', '0', 'K', '0', '0', '0', 'p', 'P'};
        return tablero;

    }

    public Jugador getJ1() {
        return jugador1;
    }

    public Jugador getJ2() {
        return jugador2;
    }

    /*
        Pre: Cierto
        Post: La función jugar intenta efectuar un movimiento según los parámetros de entrada
     */
    public static void jugar(int posX, int posY, int movX, int movY) throws Exception {
        if(tinicio == -1 && tfin == -1) {
            tinicio = new Date().getTime();
        }
        if (!jugador1.isEnJaqueMate() && !jugador2.isEnJaqueMate()) {
            //TODO: Need to change: need to get Pieza from posX,posY somehow
            Pieza pieza = new Peon();

            Movimiento m = new Movimiento(pieza, posX, posY, movX, movY, tablero);
            //////
            if ((jugador1.isEsNegro() && !tablero.getTurnoBlancas()) || (!jugador1.isEsNegro() && tablero.getTurnoBlancas())) {
                try {
                    tablero = jugador1.jugar(tablero, m);
                    tablero.setTurnoBlancas(!tablero.getTurnoBlancas());
                } catch (Exception e) {
                    throw e;
                }
            } else if ((jugador2.isEsNegro() && !tablero.getTurnoBlancas()) || (!jugador2.isEsNegro() && tablero.getTurnoBlancas())) {
                try {
                    tablero = jugador2.jugar(tablero, m);
                    tablero.setTurnoBlancas(!tablero.getTurnoBlancas());
                } catch (Exception e) {
                    throw e;
                }

            }
        } else {
            Exception e = new Exception("H1 en Mate");
            throw e;
        }
    }

    /*
    Pre: Cierto
    Post: La función crea una partida nueva a partir del string FEN, el valor de N y los jugadores pasados por parámetro
     */
    public static void crearPartida(String FEN, int N, int jug1, int jug2) {
        problema = new Problema();
        problema.setFEN(FEN);
        problema.setN(N);
        seleccionarJugadores(jug1, jug2, problema.getIniJuegoBlancas());
        tablero = new Tablero(jugador1, jugador2); //antigua FENToTablero
        tablero.FENToTablero(problema.getFEN(), problema.getIniJuegoBlancas());
    }

    /*
    Pre: Cierto
    Post: Valida el problema FEN pasado por parámetro
     */
    public static boolean esProblemaValidable(String FEN, int N) {
        problema = new Problema();
        problema.setFEN(FEN);
        problema.setN(N);
        return problema.verificarProblema();
    }

    public String[][] refrescarRanking() {
        Ranking r = new Ranking();
        return r.refrescarRanking();
    }

    public ArrayList<String> getTodosLosJugadores() {
        return controlPersistencia.getTodosLosJugadores();
    }

    public static void main(String[] args) {
        controlPersistencia = ctrl_persistencia.getInstance();
        /*try {
            controlPersistencia.guardarJugador("pepito", "test1");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            controlPersistencia.guardarJugador("menganito", "test2");
        } catch (Exception e) {
            System.out.println(e);
        }
        controlPersistencia.guardarProblemaGanado("pepito", "B4K2/p1NR1P2/Rp6/2N1kbQn/1np5/2p1p3/2P3pP/6B1", 2, "H2", "Facil", 2);
        controlPersistencia.guardarProblemaGanado("pepito", "5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5", 2, "H2", "Dificil", 3);
        controlPersistencia.guardarProblemaGanado("menganito", "2R5/2N4K/2pn2B1/Nb6/5p2/B1k1p2Q/2pn4/3R4", 1, "H2", "Facil", 2);
        controlPersistencia.guardarProblema("B4K2/p1NR1P2/Rp6/2N1kbQn/1np5/2p1p3/2P3pP/6B1", 2,"Facil", true, 1, 2);
        controlPersistencia.guardarProblema("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5", 2,"Dificil", true, 1, 2);
        controlPersistencia.guardarProblema("2R5/2N4K/2pn2B1/Nb6/5p2/B1k1p2Q/2pn4/3R4", 1, "Facil", true, 1, 1);
*/

        /*try {
            controlPersistencia.borrarJugador("pepito");
        } catch (Exception e) {
            System.out.println(e);
        }*/
        /*try {
            controlPersistencia.guardarJugador("fulanito");
        } catch (Exception e) {
            System.out.println(e);
        }*/
        /*try {
            int tablero = (int)System.currentTimeMillis();
            controlPersistencia.guardarProblemaGanado("pepito", "B4K2/p1NR1P2/Rp6/2N1kbQn/1np5/2p1p3/2P3pP/6B1 w - - 0 1" , 2, "H1", "Facil", tablero);
        } catch (Exception e) {
            System.out.println(e);
        }
        ArrayList<ArrayList<String>> tmp = controlPersistencia.getProblemasGanadosJugador("pepito");
        Stream.of(tmp)
                .flatMap(Stream::of)
                .forEach(System.out::println);*/
        //System.out.println(controlPersistencia.puedeJugarProblema("pepito", "B4K2/p1NR1P2/Rp6/2N1kbQn/1np5/2p1p3/2P3pP/6B1 w - - 0 1" , 2, "H1"));
        //System.out.println(controlPersistencia.puedeJugarProblema("pepito", "2R5/2N4K/2pn2B1/Nb6/5p2/B1k1p2Q/2pn4/3R4 w - - 0 1" , 2, "H1"));

        //controlPersistencia.guardarProblema("2R5/2N4K/2pn2B1/Nb6/5p2/B1k1p2Q/2pn4/3R4 w - - 0 1" , 5, "Dificil", false, 0, 0);
        /*try {
            controlPersistencia.incrementarPartidaJugada("menganito");
        } catch (Exception e) {
            System.out.println(e);
        }*/

    }


    /*
    FEN
    N
    Contra
    Dificultad
    Tiempo
     */

    public ArrayList<Problema> getProblemasGanadosJugador(final String nombreJugador) {
        ArrayList<ArrayList<String>> tmp = controlPersistencia.getProblemasGanadosJugador(nombreJugador);
        ArrayList<Problema> res = new ArrayList<>();
        for(int i = 0; i < tmp.size(); ++i) {
            Problema p = new Problema();
            p.setFEN(tmp.get(i).get(0));
            p.setN(Integer.parseInt(tmp.get(i).get(1)));
            p.setTiempo(Integer.parseInt(tmp.get(i).get(4)));
            res.add(p);
        }
        return res;
    }

    public int getTiempoMedioProblema(final String FEN, final int N) {
        return controlPersistencia.getTiempoMedioProblema(FEN,N);
    }
}
