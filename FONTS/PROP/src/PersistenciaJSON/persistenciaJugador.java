package PersistenciaJSON;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import org.json.*;

public class persistenciaJugador {
    String path = "./Database/Jugadores";
    String currentPath = path + "/" +"data.JSON5";

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

    public void guardarJugador(final String nombreJugador) throws Exception {
        File dir = new File(path);
        JSONArray rootA;
        if(dir.exists()) {
            File dbJugadores = new File(currentPath);
            if (!dbJugadores.exists()) {
                dbJugadores.createNewFile();
                rootA = new JSONArray();
                JSONObject jo = new JSONObject();
                rootA.put(jo);
                jo.put("nombre", nombreJugador);
                jo.put("partidasTotales", 0);
                jo.put("partidasGanadas", 0);
                JSONArray problemas = new JSONArray();
                jo.put("problemas", problemas);
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
                    JSONObject jo = new JSONObject();
                    jarr.put(jo);
                    jo.put("nombre", nombreJugador);
                    jo.put("partidasTotales", 0);
                    jo.put("partidasGanadas", 0);
                    JSONArray problemas = new JSONArray();
                    jo.put("problemas", problemas);
                    guardaJSONdb(jarr);
                }
            }
        }
    }

    public boolean existeJugador(final String nombreJugador) {
        boolean res = false;
        File dir = new File(currentPath);
        if(dir.exists()) {
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
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if(jo.get("nombre").equals(nombreJugador)) return true;
            }
        }
        return res;
    }

    public void borrarJugador(final String nombreJugador) throws Exception {
        if(existeJugador(nombreJugador)) {
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

    public void guardarProblemasGanadosJugador(final String nombreJugador, final String FEN, final int N, final String dificultad, final String vs, final int tiempo) throws Exception {
        if(existeJugador(nombreJugador)) {
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
            for(int i = 0; i < jarr.length(); ++i) {
                JSONObject jo = jarr.optJSONObject(i);
                if(jo.get("nombre").equals(nombreJugador)) {
                    JSONObject nuevoProb = new JSONObject();
                    nuevoProb.put("FEN", FEN);
                    nuevoProb.put("N", N);
                    nuevoProb.put("Contra", vs);
                    nuevoProb.put("Dificultad", vs);
                    nuevoProb.put("Tiempo", tiempo);
                    //jo.put("problemas", nuevoProb);
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
}


/*JSONArray rootA = new JSONArray();
        JSONObject jo = new JSONObject();
        rootA.put(jo);
        jo.put("nombre", "pepito");
        jo.put("partidasTotales", 0);
        jo.put("partidasGanadas", 0);
        JSONObject p1 = new JSONObject();
        JSONArray problemas = new JSONArray();
        p1.put("FEN", "3NK3/4Pp2/3P1Rp1/1Bpk4/n1N5/5P2/1r2b3/Q3R1B1 w - - 0 1");
        p1.put("N", 2);
        p1.put("Dificultad", "Facil");
        p1.put("tiempo", 4);
        problemas.put(p1);
        rootA.put(problemas);

        JSONObject jo2 = new JSONObject();
        rootA.put(jo2);
        jo2.put("nombre", "menganito");
        jo2.put("partidasTotales", 0);
        jo2.put("partidasGanadas", 0);
        JSONObject p2 = new JSONObject();
        JSONArray problemas2 = new JSONArray();
        p2.put("FEN", "2r4R/3r4/b6B/5PPk/7p/8/3pRP2/b2B1KQ1 w - - 0 1");
        p2.put("N", 3);
        p2.put("Dificultad", "Dificil");
        p2.put("tiempo", 7);
        problemas2.put(p2);
        rootA.put(problemas2);

        System.out.println(rootA);*/

        /*String json = rootA.toString();
        BufferedWriter bw = null;
        try {
            bw = Files.newBufferedWriter(Paths.get("./Database/Jugadores/data.JSON5"), StandardOpenOption.APPEND);
            bw.write(json);
            bw.close();
        }
        catch(Exception e) {
            throw e;
        }*/

        /*JSONObject rootJ = new JSONObject();
        JSONObject a1 = new JSONObject();
        JSONObject a2= new JSONObject();
        JSONArray ja = new JSONArray();

        rootJ.put("jugador", a1);
        rootJ.put("partidas", a2);
        rootJ.put("problemasGanados", ja);
        a1.put("nombre", nombreJugador);
        a2.put("ganadas", 0);
        a2.put("totales", 0);

        //ja.put("problemas");
        JSONArray je = new JSONArray();
        //ja.put(je);
        JSONObject aa = new JSONObject();
        ja.put(aa);
        aa.put("FEN", "3NK3/4Pp2/3P1Rp1/1Bpk4/n1N5/5P2/1r2b3/Q3R1B1 w - - 0 1");
        aa.put("N", 2);
        aa.put("Dificultad", "Facil");
        aa.put("tiempo", 4);

        JSONObject aaa = new JSONObject();
        ja.put(aaa);
        aaa.put("FEN", "2r4R/3r4/b6B/5PPk/7p/8/3pRP2/b2B1KQ1 w - - 0 1");
        aaa.put("N", 2);
        aaa.put("Dificultad", "Facil");
        aaa.put("tiempo", 2);

        String json = rootJ.toString();
        String currentPath = path + "/" +"data.JSON5";
        File dir = new File(path);
        if(dir.exists()) {
            File dbJugadores = new File(currentPath);
            if (!dbJugadores.exists()) {
                dbJugadores.createNewFile();
            }
        }
        BufferedWriter bw = null;
        try {
            bw = Files.newBufferedWriter(Paths.get("./Database/Jugadores/data.JSON5"), StandardOpenOption.APPEND);
            bw.write(json);
            bw.close();
        }
        catch(Exception e) {
            throw e;
        }

        //retrieve data from json
        String jsonObject = "";
        File f = new File("./Database/Jugadores/data.JSON5");
        Scanner sc = new Scanner(f);
        while(sc.hasNext()) {
            jsonObject += sc.nextLine();
        }
        JSONObject jobj = new JSONObject(jsonObject);
        //JSONArray problemasGanados = new JSONArray(jobj.get("problemasGanados"));
        JSONArray matches = jobj.optJSONArray("problemasGanados");
        matches.get(0);
        //System.out.println(matches);
        if (matches != null) {
            for (int i = 0; i < matches.length(); i++) {
                JSONObject objAtIndex =  matches.optJSONObject(i);
                if (objAtIndex != null && objAtIndex.get("FEN").equals("3NK3/4Pp2/3P1Rp1/1Bpk4/n1N5/5P2/1r2b3/Q3R1B1 w - - 0 1")) {
                    //System.out.println(objAtIndex);
                    objAtIndex.put("Dificultad", "CRAAAAAAAAAAAACK");
                    matches.put(i, objAtIndex);
                    //matches.put(objAtIndex);
                    /*JSONArray smallImageUrls = objAtIndex.optJSONArray("smallImageUrls");
                    for (int j = 0; j < smallImageUrlsSize; j++) {
                        String urlAtIndex = smallImageUrls.optString(j);
                    }
                }
            }
        }*/
//jobj.optJSONArray("problemasGanados").put(matches);
