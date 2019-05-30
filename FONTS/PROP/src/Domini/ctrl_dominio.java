package Domini;

import PersistenciaJSON.ctrl_persistencia;

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
        if (singleInstance == null) {
            singleInstance = new ctrl_dominio();
            controlPersistencia = ctrl_persistencia.getInstance();
        }
        return singleInstance;
    }

    public char[] getEmptyTablero() {
        char[] tableroArray = tablero.getEmptyTablero();
        return tableroArray;
    }

    /*
    Pre: Cierto
    Post: La función devuelve true si existe un jugador, almacenado en la base de datos, que esté identificado por nombre como
    nombreJugador y tenga, como contraseña, el atributo passowrd
    Excepciones: No existe ningun jugador en la base de datos con el nombre igual a nombreJugador
     */
    public boolean verificarJugador(final String username, final String password) throws Exception {
        try {
            if (controlPersistencia.esLoginOk(username, password)) return true;
            else return false;
        } catch (IOException e) {
            throw e;
        }
    }

    /*
    Pre: Cierto
    Post: Se guarda el jugador con nombre: nombre y password: password en la base de datos de los jugadores
    Excepciones: IOException, El nombre de usario ya existe, elije otro
     */
    public void registrarJugador(final String username, final String password) throws Exception {
        try {
            controlPersistencia.guardarJugador(username, password);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean isJ1EnJaqueMate() {
        return jugador1.isEnJaqueMate();
    }

    public boolean isJ2EnJaqueMate() {
        return jugador2.isEnJaqueMate();
    }

    public boolean isJ1EnMate() {
        return jugador1.isEnMate();
    }

    public boolean isJ2EnMate() {
        return jugador2.isEnMate();
    }

    /*
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: La función devuelve todos los problemas que sean de la misma dificultad que el parámetro dif de la función
    */

    public static String[][] getProblemasDificultad(final String diff) {
        ArrayList<ArrayList<String>> tmpres = controlPersistencia.getProblemasDificultad(diff);
        String[][] result = new String[tmpres.size()][2];
        for (int i = 0; i < tmpres.size(); ++i) {
            for (int j = 0; j < tmpres.get(i).size(); ++j) {
                result[i][0] = tmpres.get(i).get(0); //FEN
                result[i][1] = tmpres.get(i).get(1); //N
            }
        }
        return result;
    }


    /*
    Pre: Cierto
    Post: Si existe el jugador con nombre igual al parámetro nombreJugador, se guarda el problema FEN, con N, en la base de datos,
    guardando también contra quien se ha ganado, la dificultad y el tiempo empleado para solucionar el problema
    Excepciones: No existe ningun jugador en la base de datos con el nombre igual a nombreJugador
     */
    public void guardarProblemaGanadoJugador(final String nombre, final String FEN, final int N, final String contra, final String dificultad, final int tpartida) {
        controlPersistencia.guardarProblemaGanado(nombre, FEN, N, contra, dificultad, tpartida);
    }

    //TODO: Pre/post
    public boolean existeProblemaEnElSistema(final String FEN, final int N) {
        return controlPersistencia.existeProblemaEnElSistema(FEN, N);
    }

    /*
    Pre: Cierto
    Post: Se incrementa en uno el número de partidas ganadas por parte del jugador
    Excepciones: No existe el jugador identificado como nombreJugador en el sistema
    */
    public void incrementarPartidaGanada(final String nombreJugador) {
        controlPersistencia.incrementarPartidaGanada(nombreJugador);
    }

    /*
    Pre: Cierto
    Post: Se incrementa en uno el número de partidas jugadas por parte del jugador
    Excepciones: No existe el jugador identificado como nombreJugador en el sistema
     */
    public void incrementarPartidaJugada(final String nombreJugador) {
        try {
            controlPersistencia.incrementarPartidaJugada(nombreJugador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Pre: Cierto
    Post: Se borra del sistema el problema identificado por FEN y por N
    Excepciones: IO Exception
    */
    public static void borrarProblema(final String FEN, final int N) {
        try {
            controlPersistencia.borrarProblema(FEN, N);
        } catch (Exception e) {
            e.printStackTrace();
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
                if(j1EsBlanco) jugador1 = new Persona(false, true); //blanca
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
        Post: devuelve un array de todos los problemas de la base de datos
     */
    public static String[][] getTodosProblemas() {
        ArrayList<ArrayList<String>> tmpres = controlPersistencia.getProblemas();
        String[][] result = new String[tmpres.size()][3];
        for(int i = 0; i < tmpres.size(); ++i) {
            for(int j = 0; j < tmpres.get(i).size(); ++j) {
                result[i][0] = tmpres.get(i).get(0); //FEN
                result[i][1] = tmpres.get(i).get(1); //N

                result[i][2] = tmpres.get(i).get(3); //Validado?

            }
        }
        return result;
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
            if (((jugador1.isEsNegro() && !tablero.getTurnoBlancas()) || (!jugador1.isEsNegro() && tablero.getTurnoBlancas())) && jugador1 instanceof Maquina) {
                try {
                    tablero = jugador1.jugar(tablero,n); // paso tablero y N
                    tablero.setTurnoBlancas(!tablero.getTurnoBlancas());
                } catch(Exception e) {
                    if(jugador2 instanceof Persona) {
                        guardarDatosTiempo(false);
                    }
                    Exception e1 = new Exception("Jugador 1 en Jaque mate");
                    throw e1;
                }

            }
            else if (((jugador2.isEsNegro() && !tablero.getTurnoBlancas()) || (!jugador2.isEsNegro() && tablero.getTurnoBlancas())) && jugador2 instanceof Maquina) {
                try {
                    tablero = jugador2.jugar(tablero,n); // paso tablero y N
                    tablero.setTurnoBlancas(!tablero.getTurnoBlancas());
                } catch(Exception e) {
                    if(jugador1 instanceof Persona) guardarDatosTiempo(true);
                    Exception e1 = new Exception("Jugador 2 en Jaque mate");
                    throw e1;
                }

            }
        }
        else if(jugador1.isEnJaqueMate()) {
            System.out.println("jaque mate jugador1");
            if(jugador2 instanceof Persona) guardarDatosTiempo(false);
            Exception e = new Exception("Jugador 1 en Jaque mate");
            throw e;
        }
        else if(jugador2.isEnJaqueMate()) {
            System.out.println("jaque mate jugador2");
            if(jugador1 instanceof Persona) guardarDatosTiempo(true);
            Exception e = new Exception("Jugador 2 en Jaque mate");
            throw e;
        }
    }


    /*
    Guardamos en la capa de persistencia los datos de la partida en referencia al tiempo requerido para solucionar el problema
     */
    private static void guardarDatosTiempo(boolean ganaJugador1) {
        tfin = new Date().getTime();
        tpartida = tfin - tinicio;
        tpartida = tpartida/60000;
        //cambiar nombre pepito por nombre del jugador
        String vs = "";
        if(ganaJugador1) {
            if (jugador2 instanceof Maquina) vs = "M";
            else vs = "H2";
        }
        else {
            if (jugador1 instanceof Maquina) vs = "M";
            else vs = "H2";
        }
        //@TODO: Hacer get del nombre del jugador en la capa de presentación y cambiarlo por pepito
        tinicio = -1;
        tfin = -1;
        tpartida = -1;
    }

    public char[] getTablero() {
        char[][] tmpTablero = tablero.getTablero();
        char[] tableroArray = new char[64];
        int count = 0;
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                tableroArray[count] = tmpTablero[i][j];
                ++count;
            }
        }
        return tableroArray;
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
        } else if(jugador1.isEnJaqueMate()) {
            Exception e = new Exception("H1 en Mate");
            throw e;
        }
        else if(jugador2.isEnJaqueMate()) {
            Exception e = new Exception("H2 en Mate");
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

    /*
    Pre: Cierto
    Post: Actualiza la clasificacion
     */
    public String[][] refrescarRanking() {
        Ranking r = new Ranking();
        return r.refrescarRanking();
    }

    /*
    Pre: Cierto
    Post: Devuelve el nombre de usuario de todos los jugadores registrados en el sistema
     */
    public ArrayList<String> getTodosLosJugadores() {
        return controlPersistencia.getTodosLosJugadores();
    }

    public static void main(String[] args) {
        controlPersistencia = ctrl_persistencia.getInstance();
        try {
            controlPersistencia.guardarJugador("hola", "adeu");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: La función devuelve todos los problemas ganados por el jugador identificado con nombreJugador
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

    /*
    Pre: Cierto
    Post: Obtiene el tiempo medio que ha sido necesario para solucionar el problema
    */
    public int getTiempoMedioProblema(final String FEN, final int N) {
        return controlPersistencia.getTiempoMedioProblema(FEN,N);
    }
}
