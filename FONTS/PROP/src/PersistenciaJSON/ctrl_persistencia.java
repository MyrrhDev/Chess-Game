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

    public void guardarJugador(final String nombre, final String contrasena) throws Exception {
        try {
            dbJugadores.guardarJugador(nombre, contrasena);
        } catch(Exception e) {
            throw e;
        }
    }

    public void guardarProblemaGanado(final String nombreJugador, final String FEN, final int N, final String vs, final String dificultad, final long tiempo) {
        try {
            dbJugadores.guardarProblemasGanadosJugador(nombreJugador, FEN, N, dificultad, vs, tiempo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean esLoginOk(final String nombreJugador, final String contrasena) throws Exception {
        try {
            return dbJugadores.esLoginOk(nombreJugador, contrasena);
        } catch (Exception e) {
            throw e;
        }
    }

    public void borrarJugador(final String nombreJugador) throws Exception {
        try {
            dbJugadores.borrarJugador(nombreJugador);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public boolean puedeJugarProblema(final String nombreJugador, final String FEN, final int N, final String contra) {
        return dbJugadores.puedeJugarProblema(nombreJugador, FEN, N, contra);
    }

    public void incrementarPartidaGanada(final String nombreJugador) {
        try {
            dbJugadores.incrementarPartidaGanada(nombreJugador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void incrementarPartidaJugada(final String nombreJugador) {
        try {
            dbJugadores.incrementarPartidaJugada(nombreJugador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarProblema(final String FEN, final int N, final String dificultad, final boolean val, final int vecesJugado, final int tiempoMedio) {
        try {
            dbProblemas.guardarProblema(FEN, N, dificultad, val, vecesJugado, tiempoMedio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> getProblemasGanadosJugador(final String nombreJugador) {
        return dbJugadores.getProblemasGanadosJugador(nombreJugador);
    }

    public static void main(String[] args) {
    }

    public ArrayList<String> getTodosLosJugadores() {
        return dbJugadores.getTodosJugadores();
    }

    public int getTiempoMedioProblema(final String FEN, final int N) {
        return dbProblemas.getTiempoMedio(FEN, N);
    }
}
