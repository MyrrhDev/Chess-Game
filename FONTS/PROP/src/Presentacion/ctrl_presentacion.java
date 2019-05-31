package Presentacion;

import Domini.ctrl_dominio;

public class ctrl_presentacion {
    private static ctrl_presentacion ctrl_presentacion = null;
    private static ctrl_dominio ctrl_dominio = null;
    private static boolean maquinas = false;
    private static String nombreJugadorSesionH1 = "";
    private static String nombreJugadorSesionH2 = "";

    private ctrl_presentacion() {

    }

    public void crearFENdeTablero() {
        ctrl_dominio.TableroToFEN();
    }

    public boolean guardarProblema(final String FEN, final int N, boolean blancas) {
        return ctrl_dominio.guardarProblema(FEN, N, blancas);
    }

    /*
     Pre: Cierto
     Post: Se incrementa en uno el número de partidas ganadas por parte del jugador
     Excepciones: No existe el jugador identificado como nombreJugador en el sistema
     */
    public void incrementarPartidaGanada(final String nombreJugador) {
        ctrl_dominio.incrementarPartidaGanada(nombreJugador);
    }

    /*
    Pre: Cierto
    Post: Si existe el jugador con nombre igual al parámetro nombreJugador, se guarda el problema FEN, con N, en la base de datos,
    guardando también contra quien se ha ganado, la dificultad y el tiempo empleado para solucionar el problema
    Excepciones: No existe ningun jugador en la base de datos con el nombre igual a nombreJugador
     */
    public void guardarProblemaGanadoJugador(final String nombre, final String FEN, final int N, final String contra, final String dificultad, final int tpartida) {
        ctrl_dominio.guardarProblemaGanadoJugador(nombre, FEN, N, contra, dificultad, tpartida);
    }

    public boolean validarProblema(final String FEN, final int N, boolean blancas) {
        return ctrl_dominio.validarProblema(FEN, N, blancas);
    }

    public void borrarProblema(final String FEN, final int N) {
        ctrl_dominio.borrarProblema(FEN, N);
    }

    public void setNombreJugadorSesionH1(final String username) {
        this.nombreJugadorSesionH1 = username;
    }

    public void setNombreJugadorSesionH2(final String username) {
        this.nombreJugadorSesionH2 = username;
    }

    public String getNombreJugadorSesionH1() {
        System.out.println(this.nombreJugadorSesionH1);

        return this.nombreJugadorSesionH1;
    }

    public static boolean isJ1EnJaqueMate() {return ctrl_dominio.isJ1EnJaqueMate(); }



    public String getNombreJugadorSesionH2() {
        return this.nombreJugadorSesionH2;
    }

    public static char[] getTablero() {
        return ctrl_dominio.getTablero();
    }

    public static char[] getEmptyTablero() {
        return ctrl_dominio.getEmptyTablero();
    }

    public static void jugar(final int n) throws Exception {
        try {
            ctrl_dominio.jugar(n);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void jugar(final int fromX, final int fromY, final int toX, final int toY) throws Exception {
        try {
            ctrl_dominio.jugar(fromX, fromY, toX, toY);
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean isJ2EnJaqueMate() {
        return ctrl_dominio.isJ2EnJaqueMate();
    }

    public static void crearPartida(final String FEN, final int n, final int playerId1, final int playerId2) {
        ctrl_dominio.crearPartida(FEN, n, playerId1, playerId2);
    }

    public static void crearPartidaProblema(final String FEN, final int n, final int playerId1, final int playerId2) {
        ctrl_dominio.crearPartida(FEN, n, playerId1, playerId2);
    }

    /*
    Pre: Cierto
    Post: Se incrementa en uno el número de partidas jugadas por parte del jugador
    Excepciones: No existe el jugador identificado como nombreJugador en el sistema
     */
    public void incrementarPartidaJugada(final String nombreJugador) {
        try {
            ctrl_dominio.incrementarPartidaJugada(nombreJugador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[][] getProblemasDificultad(final String diff) {
        return ctrl_dominio.getProblemasDificultad(diff);
    }

    public static String[][] getTodosLosProblemas() {
        return ctrl_dominio.getTodosProblemas();
    }

    public static boolean verificarJugador(final String username, final String password) throws Exception {
        try {
            return ctrl_dominio.verificarJugador(username, password);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void registrarJugador(final String username, final String password) throws Exception {
        ctrl_dominio.registrarJugador(username, password);
    }

    public static String[][] refrescarRanking() {
        return ctrl_dominio.refrescarRanking();
    }

    public static ctrl_presentacion getInstance() {
        if(ctrl_presentacion == null) {
            ctrl_presentacion = new ctrl_presentacion();
            ctrl_dominio = ctrl_dominio.getInstance();
            return ctrl_presentacion;
        }
        else return ctrl_presentacion;
    }

    public static void main(String args[]) {
        new ctrl_presentacion();

        new LoginGUI();
    }
}