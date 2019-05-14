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

    public boolean puedeJugarProblema(final String nombreJugador, final String FEN, final int N, final String contra) {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for (int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if (jo.get("nombre").equals(nombreJugador)) {
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

    public void guardarJugador(final String nombreJugador, final String contrasena) throws Exception {
        File dir = new File(path);
        JSONArray rootA;
        if(dir.exists()) {
            File dbJugadores = new File(currentPath);
            JSONObject jo = new JSONObject();
            jo.put("nombre", nombreJugador);
            jo.put("contraseña", contrasena);
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
                catch(Exception e) {
                    throw e;
                }
            }
            else {
                if(existeJugador(nombreJugador)) {
                    Exception e = new Exception("El jugador ya existe");
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

    private boolean existeJugador(final String nombreJugador) {
        boolean res = false;
        File dir = new File(currentPath);
        if(dir.exists()) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if(jo.get("nombre").equals(nombreJugador)) return true;
            }
        }
        return res;
    }

    public boolean esLoginOk(final String nombreJugador, final String contrasena) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.getJSONObject(i);
                if(jo.get("nombre").equals(nombreJugador)) {
                    if(jo.get("contrasena").equals(contrasena)) {
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

    public void borrarJugador(final String nombreJugador) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            JSONArray newjarr = new JSONArray();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if(jo.get("nombre").equals(nombreJugador)) {
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

    public void guardarProblemasGanadosJugador(final String nombreJugador, final String FEN, final int N, final String dificultad, final String vs, final long tiempo) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if(jo.get("nombre").equals(nombreJugador)) {
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

    void incrementarPartidaGanada(final String nombreJugador) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if (jo.get("nombre").equals(nombreJugador)) {
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

    void incrementarPartidaJugada(final String nombreJugador) throws Exception {
        if(existeJugador(nombreJugador)) {
            JSONArray jarr = leerJSONdata();
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if (jo.get("nombre").equals(nombreJugador)) {
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
            if(jo.get("nombre").equals(nombreJugador)) {
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
            res.add((String)jo.get("nombre"));
        }
        return res;
    }
}