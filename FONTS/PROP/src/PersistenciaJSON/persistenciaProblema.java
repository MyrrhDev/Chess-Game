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
    /*String path = "./src/PersistenciaJSON/Problemas";
    String currentPath = path + "/" +"data.JSON5";*/

    public persistenciaProblema() {

    }

    /*
    Pre: Existe la base de datos
    Post: Guarda el JSONArray que recibe por parametro en la base de datos
    */
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

    /*
    Pre: La base de datos existe
    Post: Devuelve un JSONArray que contiene la base de datos del sistema
     */
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

    //TODO: Pre/Post
    public boolean existeProblemaEnElSistema(final String FEN, final int N) {
        JSONArray jarr = leerJSONdata();
        JSONArray newjarr = new JSONArray();
        for(int i = 0; i < jarr.length(); ++i) {
            JSONObject jo = jarr.optJSONObject(i);
            if (jo.get("FEN").equals(FEN) && (int) jo.get("N") == N) {
                return true;
            }
        }
        return false;
    }

    /*
    Pre: Cierto
    Post: Se guarda el problema identificado con FEN y con N en la base de datos del sistema.
    Excepciones: IO Exception
     */
    public void guardarProblema(final String FEN, final int N, final String dificultad, final boolean val, final int vecesJugado, final int tiempoMedio, final boolean iniJuegoBlancas) throws Exception {
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
            //TODO: NEW!
            jo.put("iniJuegoBlancas", iniJuegoBlancas);
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

    /*
    Pre: Cierto
    Post: Se borra del sistema el problema identificado por FEN y por N
    Excepciones: IO Exception
     */
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
    Pre: El usuario identificado con nombreJugador está autenticado en el sistema
    Post: La función devuelve todos los problemas que sean de la misma dificultad que el parámetro dif de la función
     */
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

    /*
    Pre: Cierto
    Post: Devuelve una lista de todos los problemas que aún no han sido validados
     */
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

    /*
    Pre: Cierto
    Post: Se incrementa en uno el número de partidas jugadas por parte del jugador
    Excepciones: No existe el jugador identificado como nombreJugador en el sistema
     */
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

    /*
    Pre: Cierto
    Post: Devuelve el tiempo medio empleado por todos los jugadores para solucionar el problema identificado por FEN
    y por N
     */
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

    /*
    Pre: Cierto
    Post: devuelve un array de todos los problemas de la base de datos
     */
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
}
