package Persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class persistenciaJugador {
    String path = "./Database/Jugadores";

    //función llamada para crear un nuevo jugador. No permite guardar dos jugadores con el mismo nombre

    public void guardarJugador(final String nombreJugador) throws Exception {
        String currentPath = path + "/" + nombreJugador + ".txt";
        File dir = new File(path);
        if(dir.exists()) {
            File dbJugadores = new File(currentPath);
            if(!dbJugadores.exists()) {
                dbJugadores.createNewFile();
                BufferedWriter bw = null;
                try {
                    bw = Files.newBufferedWriter(Paths.get(currentPath), StandardOpenOption.APPEND);
                    bw.write("nombre_jugador: " + nombreJugador);
                    bw.newLine();
                    bw.write("problemas_ganados: ");
                    bw.newLine();
                    bw.close();
                }
                catch(Exception e) {
                    throw e;
                }
            }
            else {
                Exception e = new Exception("El jugador ya existe");
                throw e;
            }
        }
        else {
            Exception e = new Exception("No ha sido posible encontrar la base de datos de jugadores en ./Database/Jugadores. ctrl_persistencia ha sido instanciado?");
            throw e;
        }
    }

    public boolean existeJugador(final String nombreJugador) {
        boolean res = false;
        File dir = new File(path);
        if(dir.exists()) {
            File dbJugador = new File(path + "/" + nombreJugador + ".txt");
            if(dbJugador.exists()) res = true;
        }
        return res;
    }

    public void borrarJugador(final String nombreJugador) throws Exception {
        File dir = new File(path);
        if(dir.exists()) {
            File dbJugador = new File(path + "/" + nombreJugador + ".txt");
            boolean res = false;
            if(dbJugador.exists()) res = dbJugador.delete();
            if(!res) {
                Exception e = new Exception("No se ha podido eliminar el jugador de la base de datos");
                throw e;
            }
        }
    }

    public void guardarProblemasGanadosJugador(String nombreJugador, String FEN, int N) {
        BufferedWriter bw = null;
        try {
            String currentPath = path+"/"+nombreJugador+".txt";
            bw = Files.newBufferedWriter(Paths.get(currentPath), StandardOpenOption.APPEND);
            bw.write(FEN + " N: " + String.valueOf(N));
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
