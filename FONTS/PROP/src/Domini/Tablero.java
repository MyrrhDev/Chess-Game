package Domini;
import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tablero {

    public char tablero[][] = new char[8][8];
    //private final ArrayList <Pieza> PiezasBlancas;
    //private final ArrayList <Pieza> PiezasNegras;

    //private final Jugador jugador1;
    //private final Jugador jugador2;
    //public HashMap < Integer ,Pieza> piece = new HashMap<>();
    //private HashMap <Character ,Integer> piezasDisponibles = new HashMap<>();

    //Si false: Negras
    //Si true: Blancas
    boolean siguienteMovimiento;


    public boolean isSiguienteMovimiento() {
        return siguienteMovimiento;
    }

    //====Constructora====
    public Tablero() {
    }

    public Tablero(String FEN, boolean primerMovimiento) {
        //TODO: Mala práctica. Solucionar!!!
        FENtoTablero(FEN, primerMovimiento);
    }

    private Tablero FENtoTablero(String FEN, boolean primerMovimiento) {
        Tablero t = new Tablero();
        return t;
    }
}