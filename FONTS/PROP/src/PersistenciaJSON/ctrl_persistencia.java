package PersistenciaJSON;

import PersistenciaJSON.persistenciaJugador;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class ctrl_persistencia {
    private static ctrl_persistencia singleInstance = null;
    private static persistenciaJugador dbJugadores;
    private static persistenciaProblema dbProblemas;

    private ctrl_persistencia() {
        new File("./Database").mkdirs();
        //creamos la base de datos para jugadores
        new File("./Database/Jugadores").mkdir();
        //creamos la base de datos para problemas
        new File("./Database/Problemas").mkdir();
        dbJugadores = new persistenciaJugador();
        dbProblemas = new persistenciaProblema();
    }

    /*
    Patron Singleton
   */
    public static ctrl_persistencia getInstance() {
        if (singleInstance == null) singleInstance = new ctrl_persistencia();
        return singleInstance;
    }

    /*
    Pre: Cierto
    Post: Se guarda el jugador con nombre: nombre y password: password en la base de datos de los jugadores
    Excepciones: IOException, El nombre de usario ya existe, elije otro
     */
    public void guardarJugador(final String nombre, final String password) throws Exception {
        try {
            dbJugadores.guardarJugador(nombre, password);
        } catch(Exception e) {
            throw e;
        }
    }

    /*
    Pre: Cierto
    Post: Si existe el jugador con nombre igual al parámetro nombreJugador, se guarda el problema FEN, con N, en la base de datos,
    guardando también contra quien se ha ganado, la dificultad y el tiempo empleado para solucionar el problema
    Excepciones: No existe ningun jugador en la base de datos con el nombre igual a nombreJugador
     */
    public void guardarProblemaGanado(final String nombreJugador, final String FEN, final int N, final String vs, final String dificultad, final long tiempo) {
        try {
            dbJugadores.guardarProblemasGanadosJugador(nombreJugador, FEN, N, dificultad, vs, tiempo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Pre: Cierto
    Post: La función devuelve true si existe un jugador, almacenado en la base de datos, que esté identificado por nombre como
    nombreJugador y tenga, como contraseña, el atributo passowrd
    Excepciones: No existe ningun jugador en la base de datos con el nombre igual a nombreJugador
     */
    public boolean esLoginOk(final String nombreJugador, final String password) throws Exception {
        try {
            return dbJugadores.esLoginOk(nombreJugador, password);
        } catch (Exception e) {
            throw e;
        }
    }

    /*
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: Se borra de la base de datos el jugador con nombre igual a nombre de jugador
     */
    public void borrarJugador(final String nombreJugador) throws Exception {
        try {
            dbJugadores.borrarJugador(nombreJugador);
        }
        catch(Exception e) {
            throw e;
        }
    }

    /*
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: Develve cierto si puede jugar el problema identificado con los parámetros FEN y N contra un oponente
    identificado con el parámetro con el mismo nombre
    Excepciones: Ninguna
     */
    public boolean puedeJugarProblema(final String nombreJugador, final String FEN, final int N, final String contra) {
        return dbJugadores.puedeJugarProblema(nombreJugador, FEN, N, contra);
    }

    /*
    Pre: Cierto
    Post: Se incrementa en uno el número de partidas ganadas por parte del jugador
    Excepciones: No existe el jugador identificado como nombreJugador en el sistema
     */
    public void incrementarPartidaGanada(final String nombreJugador) {
        try {
            dbJugadores.incrementarPartidaGanada(nombreJugador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Pre: Cierto
    Post: Se incrementa en uno el número de partidas jugadas por parte del jugador
    Excepciones: No existe el jugador identificado como nombreJugador en el sistema
     */
    public void incrementarPartidaJugada(final String nombreJugador) {
        try {
            dbJugadores.incrementarPartidaJugada(nombreJugador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Pre: Cierto
    Post: Se guarda el problema identificado con FEN y con N en la base de datos del sistema.
    Excepciones: IO Exception
     */
    public void guardarProblema(final String FEN, final int N, final String dificultad, final boolean esValidado, final int vecesJugado, final int tiempoMedio) {
        try {
            dbProblemas.guardarProblema(FEN, N, dificultad, esValidado, vecesJugado, tiempoMedio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: La función devuelve todos los problemas ganados por el jugador identificado con nombreJugador
     */
    public ArrayList<ArrayList<String>> getProblemasGanadosJugador(final String nombreJugador) {
        return dbJugadores.getProblemasGanadosJugador(nombreJugador);
    }

    /*
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: La función devuelve todos los problemas que sean de la misma dificultad que el parámetro dif de la función
     */
    public static ArrayList<ArrayList<String>> getProblemasDificultad(final String dif) {
        return dbProblemas.getProblemasDificultad(dif);
    }

    /*
    Pre: Cierto
    Post: Devuelve el nombre de usuario de todos los jugadores registrados en el sistema
     */
    public ArrayList<String> getTodosLosJugadores() {
        return dbJugadores.getTodosJugadores();
    }

    /*
    Pre: Cierto
    Post: Obtiene el tiempo medio que ha sido necesario para solucionar el problema
    */
    public int getTiempoMedioProblema(final String FEN, final int N) {
        return dbProblemas.getTiempoMedio(FEN, N);
    }

}
