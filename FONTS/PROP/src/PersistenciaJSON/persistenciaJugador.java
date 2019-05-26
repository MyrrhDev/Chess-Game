package PersistenciaJSON;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.*;

public class persistenciaJugador {
    String path = "./Database/Jugadores";
    String currentPath = path + "/" +"data.JSON5";

    /*
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: Develve cierto si puede jugar el problema identificado con los parámetros FEN y N contra un oponente
    identificado con el parámetro con el mismo nombre
    Excepciones: Ninguna
     */
    public boolean puedeJugarProblema(final String nombreJugador, final String FEN, final int N, final String contra) {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for (int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if (jo.get("username").equals(nombreJugador)) {
                    JSONArray pr = jo.getJSONArray("problemas");
                    for(int j = 0; j < pr.length(); ++j) {
                        if (pr.getJSONObject(j).get("FEN").equals(FEN) && (int) pr.getJSONObject(j).get("N") == N) {
                            if (pr.getJSONObject(j).get("Contra").equals(contra)) return false;
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }

    private JSONArray leerJSONdata() {
        String jsonObject = "";
        File f = new File(currentPath);
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(sc.hasNext()) {
            jsonObject += sc.nextLine();
        }
        JSONArray jarr = new JSONArray(jsonObject);
        return jarr;
    }

    private void guardaJSONdb(final JSONArray ja) throws Exception {
        String json = ja.toString();
        BufferedWriter bw;
        try {
            File db = new File(currentPath);
            db.delete();
            db.createNewFile();
            bw = Files.newBufferedWriter(Paths.get("./Database/Jugadores/data.JSON5"), StandardOpenOption.APPEND);
            bw.write(json);
            bw.close();
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean existeJugador(final String nombreJugador) {
        boolean res = false;
        File dir = new File(currentPath);
        if(dir.exists()) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if(jo.get("username").equals(nombreJugador)) return true;
            }
        }
        return res;
    }

    /*
    Pre: Cierto
    Post: Se guarda el jugador con nombre: nombre y password: password en la base de datos de los jugadores
    Excepciones: IOException, El nombre de usario ya existe, elije otro
     */
    public void guardarJugador(final String username, final String password) throws Exception {
        File dir = new File(path);
        JSONArray rootA;
        if(dir.exists()) {
            File dbJugadores = new File(currentPath);
            JSONObject jo = new JSONObject();
            jo.put("username", username);
            jo.put("password", password);
            jo.put("partidasTotales", 0);
            jo.put("partidasGanadas", 0);
            JSONArray problemas = new JSONArray();
            jo.put("problemas", problemas);
            if (!dbJugadores.exists()) {
                dbJugadores.createNewFile();
                rootA = new JSONArray();
                rootA.put(jo);
                String json = rootA.toString();
                BufferedWriter bw;
                try {
                    bw = Files.newBufferedWriter(Paths.get("./Database/Jugadores/data.JSON5"), StandardOpenOption.APPEND);
                    bw.write(json);
                    bw.close();
                }
                catch(IOException e) {
                    System.err.println("An IOException was caught :"+e.getMessage());
                    throw e;
                }
            }
            else {
                if(existeJugador(username)) {
                    Exception e = new Exception("El nombre de usario ya existe, elije otro");
                    throw e;
                }
                else {
                    String jsonObject = "";
                    File f = new File(currentPath);
                    Scanner sc = new Scanner(f);
                    while (sc.hasNext()) {
                        jsonObject += sc.nextLine();
                    }
                    JSONArray jarr = new JSONArray(jsonObject);
                    jarr.put(jo);
                    guardaJSONdb(jarr);
                }
            }
        }
    }

        /*
    Pre: Cierto
    Post: La función devuelve true si existe un jugador, almacenado en la base de datos, que esté identificado por nombre como
    nombreJugador y tenga, como contraseña, el atributo passowrd
    Excepciones: No existe ningun jugador en la base de datos con el nombre igual a nombreJugador
     */
    public boolean esLoginOk(final String nombreJugador, final String password) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.getJSONObject(i);
                if(jo.get("username").equals(nombreJugador)) {
                    if(jo.get("password").equals(password)) {
                        return true;
                    }
                    else return false;
                }
            }
        }
        else {
            Exception e = new Exception("No hay ningun jugador registrado con nombre " + nombreJugador);
            throw e;
        }
        return false;
    }

    /*
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: Se borra de la base de datos el jugador con nombre igual a nombre de jugador
     */
    public void borrarJugador(final String nombreJugador) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            JSONArray newjarr = new JSONArray();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if(jo.get("username").equals(nombreJugador)) {
                }
                else {
                    newjarr.put(jo);
                }
            }

            if(jarr.length() > 1) {
                guardaJSONdb(newjarr);
            }
            else {
                File db = new File(currentPath);
                db.delete();
            }
        }
    }

    /*
    Pre: Cierto
    Post: Si existe el jugador con nombre igual al parámetro nombreJugador, se guarda el problema FEN, con N, en la base de datos,
    guardando también contra quien se ha ganado, la dificultad y el tiempo empleado para solucionar el problema
    Excepciones: No existe ningun jugador en la base de datos con el nombre igual a nombreJugador
     */
    public void guardarProblemasGanadosJugador(final String nombreJugador, final String FEN, final int N, final String dificultad, final String vs, final long tiempo) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if(jo.get("username").equals(nombreJugador)) {
                    JSONObject nuevoProb = new JSONObject();
                    nuevoProb.put("FEN", FEN);
                    nuevoProb.put("N", N);
                    nuevoProb.put("Contra", vs);
                    nuevoProb.put("Dificultad", vs);
                    nuevoProb.put("Tiempo", tiempo);
                    JSONArray pr;
                    pr = jo.getJSONArray("problemas");
                    pr.put(nuevoProb);
                    guardaJSONdb(jarr);
                }
            }
        }
        else {
            Exception e = new Exception("No existe ningun jugador en la base de datos");
            throw e;
        }
    }

    /*
    Pre: Cierto
    Post: Se incrementa en uno el número de partidas ganadas por parte del jugador
    Excepciones: No existe el jugador identificado como nombreJugador en el sistema
     */
    void incrementarPartidaGanada(final String nombreJugador) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if (jo.get("username").equals(nombreJugador)) {
                    int tmpPartidas = (int)jo.get("partidasGanadas");
                    ++tmpPartidas;
                    jo.put("partidasGanadas", tmpPartidas);
                    tmpPartidas = (int)jo.get("partidasTotales");
                    ++tmpPartidas;
                    jo.put("partidasTotales", tmpPartidas);
                }
            }
            guardaJSONdb(jarr);
        }
    }

    /*
    Pre: Cierto
    Post: Se incrementa en uno el número de partidas jugadas por parte del jugador
    Excepciones: No existe el jugador identificado como nombreJugador en el sistema
     */
    void incrementarPartidaJugada(final String nombreJugador) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if (jo.get("username").equals(nombreJugador)) {
                    int tmpPartidas = (int)jo.get("partidasTotales");
                    ++tmpPartidas;
                    jo.put("partidasTotales", tmpPartidas);
                }
            }
            guardaJSONdb(jarr);
        }
    }

    /*
    FEN
    N
    Contra
    Dificultad
    Tiempo
     */
    public ArrayList<ArrayList<String>> getProblemasGanadosJugador(final String nombreJugador) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        JSONArray jarr = leerJSONdata();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.optJSONObject(i);
            if(jo.get("username").equals(nombreJugador)) {
                JSONArray prob = jo.optJSONArray("problemas");
                for(int j = 0; j < prob.length(); ++j) {
                    ArrayList<String> data = new ArrayList<>();
                    JSONObject pganado = prob.optJSONObject(j);
                    data.add((String)pganado.get("FEN"));
                    data.add(String.valueOf(pganado.get("N")));
                    data.add((String)pganado.get("Contra"));
                    data.add((String)pganado.get("Dificultad"));
                    data.add(String.valueOf(pganado.get("Tiempo")));
                    result.add(data);
                }
            }
        }
        return result;
    }

    public ArrayList<String> getTodosJugadores() {
        ArrayList<String> res = new ArrayList<>();
        JSONArray jarr = leerJSONdata();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.getJSONObject(i);
            res.add((String)jo.get("username"));
        }
        return res;
    }
}