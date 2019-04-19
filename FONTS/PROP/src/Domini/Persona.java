package Domini;


import java.util.ArrayList;

public class Persona extends Jugador {


    public Persona(boolean esMaquina, boolean esNegro, boolean estaAtacando) {
        super(esMaquina, esNegro, estaAtacando);
    }

    @Override
    public Tablero jugar(Tablero tablero, Movimiento movimiento) throws Exception {

        boolean sePuede = tablero.movimientoHumano(movimiento);

        if(!sePuede) throw new Exception("No se puede ejecutar el movimiento");
        tablero.setTurnoBlancas(false);
        return tablero;
    }

    @Override
    public Tablero jugar(Tablero tablero, int N) throws Exception {
        return tablero;
    }
}
