package Domini;

import java.util.Date;

public class Problema {
    public String problema;
    int N;
    public boolean abreJuego; // true -> empiezan blancas, false -> empiezan negras

    public int getN() {
        return N;
    }

    public String getFEN() {
        return problema;
    }
}