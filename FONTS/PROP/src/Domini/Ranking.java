package Domini;

<<<<<<< HEAD
public class Ranking {
    int numJugadores;
    int puntMax;
    int puntMin;

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public int getPuntMax() {
        return puntMax;
    }

    public void setPuntMax(int puntMax) {
        this.puntMax = puntMax;
    }

    public int getPuntMin() {
        return puntMin;
    }

    public void setPuntMin(int puntMin) {
        this.puntMin = puntMin;
    }
}
=======
import java.util.*;
import java.util.stream.Collectors;

public class Ranking {
    static private HashMap<String, Integer> rank;
    int Pb = 20;
    double Df = 0.3333;

    /*
    Pre: Cierto
    Post: Actualiza el ranking acorde a los datos almacenados
     */
    public String[][] refrescarRanking() {
        rank = new HashMap<>();
        ArrayList<String> nombreJugadores = ctrl_dominio.getInstance().getTodosLosJugadores();
        for(int i = 0; i < nombreJugadores.size(); ++i) {
            rank.put(nombreJugadores.get(i), 0);
        }
        for (HashMap.Entry<String, Integer> entry : rank.entrySet()) {
            ArrayList<Problema> problemasGanados = ctrl_dominio.getInstance().getProblemasGanadosJugador(entry.getKey());
            System.out.println(entry.getKey());
            double pts = 0.0;
            for(Problema p : problemasGanados) {
                if(p.getDificultad().equals("Facil")) Df = 0.3333;
                else if(p.getDificultad().equals("Medio")) Df *= 2;
                else if(p.getDificultad().equals("Dificil")) Df *= 3;
                int Tm = p.getTiempo();
                int Tmp = ctrl_dominio.getInstance().getTiempoMedioProblema(p.getFEN(), p.getN());
                pts += Pb * Df * (Df/(Df+Math.log10((double)Tmp/(double)Tm)));
            }
            rank.put(entry.getKey(), (int)pts);
        }
        for (HashMap.Entry<String, Integer> entry : rank.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        //sort rank
        final Map<String, Integer> sortedByCount = rank.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println(sortedByCount);
        String[][] res = new String[sortedByCount.size()][3];
        Iterator<Map.Entry<String, Integer>> it = sortedByCount.entrySet().iterator();
        for(int i = 0; i < sortedByCount.size(); ++i) {
            Map.Entry entry = it.next();
            System.out.println("entry: " + entry);
            String[] data = new String[3];
            data[0] = String.valueOf(i+1);
            data[1] = String.valueOf(entry.getKey());
            data[2] = String.valueOf(entry.getValue());
            res[i] = data;
        }
        return res;
    }
}
>>>>>>> Mayra-Logic
