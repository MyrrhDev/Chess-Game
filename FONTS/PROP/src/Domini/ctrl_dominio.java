package Domini;

import PersistenciaJSON.ctrl_persistencia;
import Presentacion.TableroGUI;

import java.util.ArrayList;
import java.util.Date;

public class ctrl_dominio {
    private static Jugador j1, j2;
    private static Tablero t;
    private static Problema p;
    private static ctrl_dominio singleInstance = null;
    private static ctrl_persistencia cp;
    private static long tini, tfin, tpartida;

    private ctrl_dominio() {
        j1 = j2 = null;
    }

    /*
    Patron Singleton
     */
    public static ctrl_dominio getInstance() {
        if(singleInstance == null) {
            singleInstance = new ctrl_dominio();
            cp = ctrl_persistencia.getInstance();
        }
        return singleInstance;
    }

    /*
    Pre: Cierto
    Post: Tanto para jug1, como para jug2:
        Si jug = 1 -> Se crea una instancia de Persona
        Si jug = 2 -> Se crea una instancia de Maquina
        j1EsBlanco detalla el color de piezas del jugador 1
     */
    public static void seleccionarJugadores(int jug1, int jug2, boolean j1EsBlanco) {
        switch(jug1) {
            case 1:
                if(j1EsBlanco) j1 = new Persona(false, false, true); //blanca //TODO: li he de passar es maquina, es negro i esta atacando
                else j1 = new Persona(false, true, true); //negra
                break;
            case 2:
                if(j1EsBlanco) j1 = new Maquina(true, false, true);
                else j1 = new Maquina(true, true, true);
                break;
            case 3:
                //j1 = new M2();
                break;
        }
        switch(jug2) {
            case 1:
                if(j1EsBlanco) j2 = new Persona(false, true, false); //negra porque jug1 blanca
                else j2 = new Persona(false, false, false); //blanca porque jug1 negra
                break;
            case 2:
                if(j1EsBlanco) j2 = new Maquina(true, true, false);
                else j2 = new Maquina(true, false, false);
                //j2 = new M1();
                break;
            case 3:
                //j2 = new M2();
                break;
        }
    }


    /*
        Pre: Cierto
        Post: La función jugar intenta efectuar un movimiento según los parámetros de entrada
     */
    public static void jugar(int n) throws Exception {
        if(tini == -1 && tfin == -1) {
            tini = new Date().getTime();
        }
        if(!j1.isEnJaqueMate() && !j2.isEnJaqueMate()) {
            if (((j1.isEsNegro() && !t.getTurnoBlancas()) || (!j1.isEsNegro() && t.getTurnoBlancas())) && j1.isEsMaquina()) {
                try {
                    t = j1.jugar(t,n); // paso tablero y N
                    t.setTurnoBlancas(!t.getTurnoBlancas());
                } catch(Exception e) {
                    if(j2 instanceof Persona) {
                        tfin = new Date().getTime();
                        tpartida = tfin - tini;
                        tpartida = tpartida/60000;
                        String vs = "";
                        if(j1 instanceof Maquina) vs = "M";
                        else vs = "H1";
                        //cambiar nombre pepito por nombre del jugador
                        cp.guardarProblemaGanado("pepito", p.getFEN(), p.getN(), vs, p.getDificultad(), tpartida);
                        tini = -1;
                        tfin = -1;
                        tpartida = -1;
                    }
                    Exception e1 = new Exception("Jugador 1 en Jaque mate");
                    throw e1;
                }

            }
            if (((j2.isEsNegro() && !t.getTurnoBlancas()) || (!j2.isEsNegro() && t.getTurnoBlancas())) && j2.isEsMaquina()) {
                try {
                    t = j2.jugar(t,n); // paso tablero y N
                    t.setTurnoBlancas(!t.getTurnoBlancas());
                } catch(Exception e) {
                    if(j1 instanceof Persona) {
                        tfin = new Date().getTime();
                        tpartida = tfin - tini;
                        tpartida = tpartida/60000;
                        String vs = "";
                        if(j1 instanceof Maquina) vs = "M";
                        else vs = "H2";
                        //cambiar nombre pepito por nombre del jugador
                        cp.guardarProblemaGanado("pepito", p.getFEN(), p.getN(), vs, p.getDificultad(), tpartida);
                        tini = -1;
                        tfin = -1;
                        tpartida = -1;
                    }
                    Exception e1 = new Exception("Jugador 2 en Jaque mate");
                    throw e1;
                }

            }
        }
        else if(j1.isEnJaqueMate()) {
            if(j2 instanceof Persona) {
                tfin = new Date().getTime();
                tpartida = tfin - tini;
                tpartida = tpartida/60000;
                String vs = "";
                if(j1 instanceof Maquina) vs = "M";
                else vs = "H1";
                //cambiar nombre pepito por nombre del jugador
                cp.guardarProblemaGanado("pepito", p.getFEN(), p.getN(), vs, p.getDificultad(), tpartida);
                tini = -1;
                tfin = -1;
                tpartida = -1;
            }
            Exception e = new Exception("Jugador 1 en Jaque mate");
            throw e;
        }
        else if(j2.isEnJaqueMate()) {
            if(j1 instanceof Persona) {
                tfin = new Date().getTime();
                tpartida = tfin - tini;
                tpartida = tpartida/60000;
                String vs = "";
                if(j1 instanceof Maquina) vs = "M";
                else vs = "H2";
                //cambiar nombre pepito por nombre del jugador
                cp.guardarProblemaGanado("pepito", p.getFEN(), p.getN(), vs, p.getDificultad(), tpartida);
                tini = -1;
                tfin = -1;
                tpartida = -1;
            }
            Exception e = new Exception("Jugador 2 en Jaque mate");
            throw e;
        }
    }

    public char[] getTablero() {
        //return t.getTablero();

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
        return j1;
    }

    public Jugador getJ2() {
        return j2;
    }

    /*
        Pre: Cierto
        Post: La función jugar intenta efectuar un movimiento según los parámetros de entrada
     */
    public static void jugar(int posX, int posY, int movX, int movY) throws Exception {
        if(tini == -1 && tfin == -1) {
            tini = new Date().getTime();
        }
        if (!j1.isEnJaqueMate() && !j2.isEnJaqueMate()) {
            Movimiento m = new Movimiento(posX, posY, movX, movY);
            if ((j1.isEsNegro() && !t.getTurnoBlancas()) || (!j1.isEsNegro() && t.getTurnoBlancas())) {
                try {
                    t = j1.jugar(t, m);
                    t.setTurnoBlancas(!t.getTurnoBlancas());
                } catch (Exception e) {
                    throw e;
                }
            } else if ((j2.isEsNegro() && !t.getTurnoBlancas()) || (!j2.isEsNegro() && t.getTurnoBlancas())) {
                try {
                    t = j2.jugar(t, m);
                    t.setTurnoBlancas(!t.getTurnoBlancas());
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
        p = new Problema();
        p.setFEN(FEN);
        p.setN(N);
        seleccionarJugadores(jug1, jug2, p.getIniJuegoBlancas());
        t = new Tablero(j1, j2); //antigua FENToTablero
        t.FENToTablero(p.getFEN(), p.getIniJuegoBlancas());
    }

    /*
    Pre: Cierto
    Post: Valida el problema FEN pasado por parámetro
     */
    public static boolean esProblemaValidable(String FEN, int N) {
        p = new Problema();
        p.setFEN(FEN);
        p.setN(N);
        return p.verificarProblema();
    }

    public String[][] refrescarRanking() {
        Ranking r = new Ranking();
        return r.refrescarRanking();
    }

    public ArrayList<String> getTodosLosJugadores() {
        return cp.getTodosLosJugadores();
    }

    public static void main(String[] args) {
        cp = ctrl_persistencia.getInstance();
        /*try {
            cp.guardarJugador("pepito", "test1");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            cp.guardarJugador("menganito", "test2");
        } catch (Exception e) {
            System.out.println(e);
        }
        cp.guardarProblemaGanado("pepito", "B4K2/p1NR1P2/Rp6/2N1kbQn/1np5/2p1p3/2P3pP/6B1", 2, "H2", "Facil", 2);
        cp.guardarProblemaGanado("pepito", "5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5", 2, "H2", "Dificil", 3);
        cp.guardarProblemaGanado("menganito", "2R5/2N4K/2pn2B1/Nb6/5p2/B1k1p2Q/2pn4/3R4", 1, "H2", "Facil", 2);
        cp.guardarProblema("B4K2/p1NR1P2/Rp6/2N1kbQn/1np5/2p1p3/2P3pP/6B1", 2,"Facil", true, 1, 2);
        cp.guardarProblema("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5", 2,"Dificil", true, 1, 2);
        cp.guardarProblema("2R5/2N4K/2pn2B1/Nb6/5p2/B1k1p2Q/2pn4/3R4", 1, "Facil", true, 1, 1);
*/

        /*try {
            cp.borrarJugador("pepito");
        } catch (Exception e) {
            System.out.println(e);
        }*/
        /*try {
            cp.guardarJugador("fulanito");
        } catch (Exception e) {
            System.out.println(e);
        }*/
        /*try {
            int t = (int)System.currentTimeMillis();
            cp.guardarProblemaGanado("pepito", "B4K2/p1NR1P2/Rp6/2N1kbQn/1np5/2p1p3/2P3pP/6B1 w - - 0 1" , 2, "H1", "Facil", t);
        } catch (Exception e) {
            System.out.println(e);
        }
        ArrayList<ArrayList<String>> tmp = cp.getProblemasGanadosJugador("pepito");
        Stream.of(tmp)
                .flatMap(Stream::of)
                .forEach(System.out::println);*/
        //System.out.println(cp.puedeJugarProblema("pepito", "B4K2/p1NR1P2/Rp6/2N1kbQn/1np5/2p1p3/2P3pP/6B1 w - - 0 1" , 2, "H1"));
        //System.out.println(cp.puedeJugarProblema("pepito", "2R5/2N4K/2pn2B1/Nb6/5p2/B1k1p2Q/2pn4/3R4 w - - 0 1" , 2, "H1"));

        //cp.guardarProblema("2R5/2N4K/2pn2B1/Nb6/5p2/B1k1p2Q/2pn4/3R4 w - - 0 1" , 5, "Dificil", false, 0, 0);
        /*try {
            cp.incrementarPartidaJugada("menganito");
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
        ArrayList<ArrayList<String>> tmp = cp.getProblemasGanadosJugador(nombreJugador);
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
        return cp.getTiempoMedioProblema(FEN,N);
    }
}
