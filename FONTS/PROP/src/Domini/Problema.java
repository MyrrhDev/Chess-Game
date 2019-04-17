package Domini;

import java.util.Date;

public class Problema {
    public String problema;
    int N;
    public boolean abreJuego; // true -> empiezan blancas, false -> empiezan negras

    //constructoras
    public Problema() {

    }

    /*
     Pre: Cierto
     Post: el atributo problema pasa a ser el FEN que se le pasa por parámetro, pero tratado de forma
           que se eliminan los elementos inútiles de la codificacion
     */
    public void setFEN(String FEN) {
        //ejemplo: 1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1
        int newLength = FEN.indexOf(' ');
        this.problema = FEN.substring(0, newLength);
    }

    public void setAbreJuego(String FEN) {
        int i = FEN.indexOf('w');
        if(i == -1) { //no existe el carácter w, verificamos que efectivamente empiezan las negras atacando
            i = FEN.indexOf('b');
        }
        if(FEN.charAt(i) == 'w') abreJuego = true;
        else abreJuego = false;
    }

    public String getFEN() {
        return this.problema;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public boolean getAbreJuego() {
        return this.abreJuego;
    }
}