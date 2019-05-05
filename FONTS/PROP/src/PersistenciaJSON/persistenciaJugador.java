package PersistenciaJSON;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;
import sun.misc.IOUtils;


public class persistenciaJugador {
    String path = "./Database/Jugadores";

    //función llamada para crear un nuevo jugador. No permite guardar dos jugadores con el mismo nombre

    public void guardarJugador(final String nombreJugador) throws Exception {
        JSONObject rootJ = new JSONObject();
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
        System.out.println(matches);
        if (matches != null) {
            for (int i = 0; i < matches.length(); i++) {
                System.out.println("holi");
                JSONObject objAtIndex =  matches.optJSONObject(i);
                if (objAtIndex != null && objAtIndex.get("FEN").equals("3NK3/4Pp2/3P1Rp1/1Bpk4/n1N5/5P2/1r2b3/Q3R1B1 w - - 0 1")) {
                    System.out.println(objAtIndex);
                    objAtIndex.put("Dificultad", "CRAAAAAAAAAAAACK");
                    matches.put(objAtIndex);
                    /*JSONArray smallImageUrls = objAtIndex.optJSONArray("smallImageUrls");
                    for (int j = 0; j < smallImageUrlsSize; j++) {
                        String urlAtIndex = smallImageUrls.optString(j);
                    }*/
                }
            }

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

    public void guardarProblemasGanadosJugador(final String nombreJugador, final String FEN, final int N, final String dificultad) {
        BufferedWriter bw = null;
        try {
            String currentPath = path+"/"+nombreJugador+".txt";
            bw = Files.newBufferedWriter(Paths.get(currentPath), StandardOpenOption.APPEND);
            bw.write(FEN + " N: " + String.valueOf(N) + " Dificultad: " + dificultad);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
