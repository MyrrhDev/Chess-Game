package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Ranking {
    static private HashMap<String, Integer> rank;
    int Pb = 20;
    double Df = 0.3333;

    public void refrescarRanking() {
        rank = new HashMap<>();
        ArrayList<String> nombreJugadores = ctrl_dominio.getInstance().getTodosLosJugadores();
        for(int i = 0; i < nombreJugadores.size(); ++i) {
            rank.put(nombreJugadores.get(i), 0);
        }
        for (HashMap.Entry<String, Integer> entry : rank.entrySet()) {
            ArrayList<Problema> problemasGanados = ctrl_dominio.getInstance().getProblemasGanadosJugador(entry.getKey());
            double pts = 0.0;
            for(Problema p : problemasGanados) {
                int Tm = p.getTiempo();
                int Tmp = ctrl_dominio.getInstance().getTiempoMedioProblema(p.getFEN(), p.getN());
                pts += Pb * Df * (Df/(Df+Math.log10(Tmp/Tm)));
            }
            rank.put(entry.getKey(), (int)pts);
        }
        for (HashMap.Entry<String, Integer> entry : rank.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
