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

    public void setNombreJugadorSesionH1(final String username) {
        this.nombreJugadorSesionH1 = username;
    }

    public void setNombreJugadorSesionH2(final String username) {
        this.nombreJugadorSesionH2 = username;
    }

    public String getNombreJugadorSesionH1() {
        return this.nombreJugadorSesionH1;
    }

    public String getNombreJugadorSesionH2() {
        return this.nombreJugadorSesionH2;
    }

    public static char[] getTablero() {
        return ctrl_dominio.getTablero();
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

    public void borrarProblema(final String FEN, final int N) {
        ctrl_dominio.borrarProblema(FEN, N);
    }


    public static String[][] getTodosLosProblemas() {
        return ctrl_dominio.getTodosProblemas();
    }

    public static void crearPartidaProblema(final String FEN, final int n, final int playerId1, final int playerId2) {
        ctrl_dominio.crearPartida(FEN, n, playerId1, playerId2);
    }

    public void incrementarPartidaJugada(final String nombreJugador) {
        try {
            ctrl_dominio.incrementarPartidaJugada(nombreJugador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void incrementarPartidaGanada(final String nombreJugador) {
        ctrl_dominio.incrementarPartidaGanada(nombreJugador);
    }

    public void guardarProblemaGanadoJugador(final String nombre, final String FEN, final int N, final String contra, final String dificultad, final int tpartida) {
        ctrl_dominio.guardarProblemaGanadoJugador(nombre, FEN, N, contra, dificultad, tpartida);
    }

    public static boolean isJ1EnJaqueMate() {return ctrl_dominio.isJ1EnJaqueMate(); }

    public static boolean isJ2EnJaqueMate() {
        return ctrl_dominio.isJ2EnJaqueMate();
    }

    public static void crearPartida(final String FEN, final int n, final int playerId1, final int playerId2) {
        ctrl_dominio.crearPartida(FEN, n, playerId1, playerId2);
    }

    public static String[][] getProblemasDificultad(final String diff) {
        return ctrl_dominio.getProblemasDificultad(diff);
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
        //@TODO: LLAMAR VISTA LOGIN
        new LoginGUI();

    }
}
