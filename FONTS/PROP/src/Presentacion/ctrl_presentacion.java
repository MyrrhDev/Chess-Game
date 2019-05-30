package Presentacion;

import Domini.ctrl_dominio;

public class ctrl_presentacion {
    private static ctrl_presentacion ctrl_presentacion = null;
    private static ctrl_dominio ctrl_dominio = null;
    private static String nombreJugadorSesionH1 = "";
    private static String nombreJugadorSesionH2 = "";

    private ctrl_presentacion() {

    }

    public boolean validarProblema(final String FEN, final int N, boolean blancas) {
        return ctrl_dominio.validarProblema(FEN, N, blancas);
    }

    public boolean guardarProblema(final String FEN, final int N, boolean blancas) {
        return ctrl_dominio.guardarProblema(FEN, N, blancas);
    }


    //patron singleton
    public static ctrl_presentacion getInstance() {
        if(ctrl_presentacion == null) {
            ctrl_presentacion = new ctrl_presentacion();
            ctrl_dominio = ctrl_dominio.getInstance();
            return ctrl_presentacion;
        }
        else return ctrl_presentacion;
    }

    public static char[] getEmptyTablero() {
        return ctrl_dominio.getEmptyTablero();
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

    /*
        Pre: Cierto
        Post: La función jugar intenta efectuar un movimiento según los parámetros de entrada
     */
    public static void jugar(final int n) throws Exception {
        try {
            ctrl_dominio.jugar(n);
        } catch (Exception e) {
            throw e;
        }
    }

    /*
        Pre: Cierto
        Post: La función jugar intenta efectuar un movimiento según los parámetros de entrada
     */
    public static void jugar(final int fromX, final int fromY, final int toX, final int toY) throws Exception {
        try {
            ctrl_dominio.jugar(fromX, fromY, toX, toY);
        } catch (Exception e) {
            throw e;
        }
    }

    /*
        Pre: Cierto
        Post: Se borra del sistema el problema identificado por FEN y por N
        Excepciones: IO Exception
    */
    public void borrarProblema(final String FEN, final int N) {
        ctrl_dominio.borrarProblema(FEN, N);
    }


    /*
        Pre: Cierto
        Post: devuelve un array de todos los problemas de la base de datos
    */
    public static String[][] getTodosLosProblemas() {
        return ctrl_dominio.getTodosProblemas();
    }

    /*
    Pre: Cierto
    Post: La función crea una partida nueva a partir del string FEN, el valor de N y los jugadores pasados por parámetro
     */
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

    public static boolean isJ1EnJaqueMate() {return ctrl_dominio.isJ1EnJaqueMate(); }

    public static boolean isJ2EnJaqueMate() {
        return ctrl_dominio.isJ2EnJaqueMate();
    }

    /*
    Pre: Cierto
    Post: La función crea una partida nueva a partir del string FEN, el valor de N y los jugadores pasados por parámetro
     */
    public static void crearPartida(final String FEN, final int n, final int playerId1, final int playerId2) {
        ctrl_dominio.crearPartida(FEN, n, playerId1, playerId2);
    }

    /*
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: La función devuelve todos los problemas que sean de la misma dificultad que el parámetro dif de la función
    */
    public static String[][] getProblemasDificultad(final String diff) {
        return ctrl_dominio.getProblemasDificultad(diff);
    }

    /*
    Pre: Cierto
    Post: La función devuelve true si existe un jugador, almacenado en la base de datos, que esté identificado por nombre como
    nombreJugador y tenga, como contraseña, el atributo passowrd
    Excepciones: No existe ningun jugador en la base de datos con el nombre igual a nombreJugador
     */
    public static boolean verificarJugador(final String username, final String password) throws Exception {
        try {
            return ctrl_dominio.verificarJugador(username, password);
        } catch (Exception e) {
            throw e;
        }
    }

    /*
    Pre: Cierto
    Post: Se guarda el jugador con nombre: nombre y password: password en la base de datos de los jugadores
    Excepciones: IOException, El nombre de usario ya existe, elije otro
     */
    public static void registrarJugador(final String username, final String password) throws Exception {
        ctrl_dominio.registrarJugador(username, password);
    }

    /*
    Pre: Cierto
    Post: Actualiza la clasificacion
     */
    public static String[][] refrescarRanking() {
        return ctrl_dominio.refrescarRanking();
    }

    public static void main(String args[]) {
        new ctrl_presentacion();
        new LoginGUI();

    }
}
