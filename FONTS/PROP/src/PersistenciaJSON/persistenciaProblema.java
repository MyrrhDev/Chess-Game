package PersistenciaJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class persistenciaProblema {
    String path = "./Database/Problemas";
    String currentPath = path + "/" +"data.JSON5";

    public persistenciaProblema() {

    }

    private void guardaJSONdb(final JSONArray ja) throws Exception {
        String json = ja.toString();
        BufferedWriter bw;
        try {
            File db = new File(currentPath);
            db.delete();
            db.createNewFile();
            bw = Files.newBufferedWriter(Paths.get(currentPath), StandardOpenOption.APPEND);
            bw.write(json);
            bw.close();
        } catch (Exception e) {
            throw e;
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

    /*
    FEN
    N
    Dificultad
    Validado?
    Veces jugado
    Tiempo medio
     */
    public void guardarProblema(final String FEN, final int N, final String dificultad, final boolean val, final int vecesJugado, final int tiempoMedio) throws Exception {
        File dir = new File(path);
        JSONArray rootA;
        if(dir.exists()) {
            File dbProblemas = new File(currentPath);
            JSONObject jo = new JSONObject();
            jo.put("FEN", FEN);
            jo.put("N", N);
            jo.put("Dificultad", dificultad);
            jo.put("Validado?", val);
            jo.put("Veces jugado", vecesJugado);
            jo.put("Tiempo medio", tiempoMedio);
            if (!dbProblemas.exists()) {
                dbProblemas.createNewFile();
                rootA = new JSONArray();
                rootA.put(jo);
                String json = rootA.toString();
                BufferedWriter bw;
                try {
                    bw = Files.newBufferedWriter(Paths.get(currentPath), StandardOpenOption.APPEND);
                    bw.write(json);
                    bw.close();
                }
                catch(Exception e) {
                    throw e;
                }
            }
            else {
                JSONArray jarr = leerJSONdata();
                jarr.put(jo);
                guardaJSONdb(jarr);
            }
        }
    }


    //Post: Borra un problema con el N especificado, entonces permitimos que se
    // guarde un problema mas de una vez con differentes N's
    public void borrarProblema(final String FEN, final int N) throws Exception {
        JSONArray jarr = leerJSONdata();
        JSONArray newjarr = new JSONArray();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.optJSONObject(i);
            if(jo.get("FEN").equals(FEN) && (int)jo.get("N") == N) {
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

    /*
    FEN
    N
    Dificultad
    Validado?
    Veces jugado
    Tiempo medio
     */

    //public void guardarProblema(final String FEN, final int N, final String dificultad, final boolean val, final int vecesJugado, final int tiempoMedio)
    public ArrayList<ArrayList<String>> getProblemasDificultad(final String dificultad) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        JSONArray jarr = leerJSONdata();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.optJSONObject(i);
            if(jo.get("Dificultad").equals(dificultad) && (boolean) jo.get("Validado?")) {
                ArrayList<String> data = new ArrayList<>();
                data.add((String)jo.get("FEN"));
                data.add(String.valueOf(jo.get("N")));
                data.add((String)jo.get("Dificultad"));
                data.add(String.valueOf(jo.get("Validado?")));
                data.add(String.valueOf(jo.get("Veces jugado")));
                data.add(String.valueOf(jo.get("Tiempo medio")));
                result.add(data);
            }
        }
        return result;
    }

    //Post: devuelve un array de todos los problemas de la base de datos:
    //TODO: Se tenian que guardar/separar o indicar de alguna manera si no son validados, no?
    public ArrayList<ArrayList<String>> getProblemas() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        JSONArray jarr = leerJSONdata();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.optJSONObject(i);
            //if(jo.get("Dificultad").equals(dificultad) && (boolean) jo.get("Validado?")) {
                ArrayList<String> data = new ArrayList<>();
                data.add((String)jo.get("FEN"));
                data.add(String.valueOf(jo.get("N")));
                data.add((String)jo.get("Dificultad"));
                if(String.valueOf(jo.get("Validado?")) == "true") {
                    data.add("Si");
                } else {
                    data.add("No");
                }
                data.add(String.valueOf(jo.get("Veces jugado")));
                data.add(String.valueOf(jo.get("Tiempo medio")));
                result.add(data);
            //}
        }
        return result;
    }


    public ArrayList<ArrayList<String>> getProblemasNoValidados() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        JSONArray jarr = leerJSONdata();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.optJSONObject(i);
            if((boolean) jo.get("Validado?")) {
                ArrayList<String> data = new ArrayList<>();
                data.add((String)jo.get("FEN"));
                data.add(String.valueOf(jo.get("N")));
                data.add((String)jo.get("Dificultad"));
                data.add(String.valueOf(jo.get("Validado?")));
                data.add(String.valueOf(jo.get("Veces jugado")));
                data.add(String.valueOf(jo.get("Tiempo medio")));
                result.add(data);
            }
        }
        return result;
    }

    public void incVecesJugado(final String FEN, final int N) {
        JSONArray jarr = leerJSONdata();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.optJSONObject(i);
            if(jo.get("FEN").equals(FEN) && Integer.parseInt((String)jo.get("N")) == N) {
                int tmp = Integer.parseInt((String)jo.get("Veces jugado"));
                ++tmp;
                jo.put("Veces jugado", tmp);
            }
        }
    }

    //https://math.stackexchange.com/questions/22348/how-to-add-and-subtract-values-from-an-average
    //average new = average old + ((value new - average old)/size new)
    //como pre tiene que primero se actualiza veces jugado del problema
    public void actualizarTiempoMedio(final String FEN, final int N, final int valNew) {
        JSONArray jarr = leerJSONdata();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.optJSONObject(i);
            if(jo.get("FEN").equals(FEN) && Integer.parseInt((String)jo.get("N")) == N) {
                int avgOld = Integer.parseInt((String)jo.get("Tiempo medio"));
                int vecesJugado = Integer.parseInt((String)jo.get("Veces jugado"));
                int avgNew = avgOld + (valNew - avgOld)/vecesJugado;
                jo.put("Veces jugado", avgNew);
            }
        }
    }

    public int getTiempoMedio(final String FEN, final int N) {
        JSONArray jarr = leerJSONdata();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.optJSONObject(i);
            if(String.valueOf(jo.get("FEN")).equals(FEN) && (int)jo.get("N") == N) {
                return (Integer)jo.get("Tiempo medio");
            }
        }
        return -1;
    }


    //listar problemas segun dificultad si validados
    //guardar veces jugado
    //guardar tiempo medio (tiempo medio + tiempo problema / veces jugado+1)
    //devolver problema (en un arraylist quizás?)
}
