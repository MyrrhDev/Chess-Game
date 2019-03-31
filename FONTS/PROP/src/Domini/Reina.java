package Domini;

import java.util.HashMap;

public class Reina extends Pieza {
    boolean esMovimientoOk(int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) { return false; }

    /*
     * Pre: La posicion, pasada por parametro, es correcta dentro del tablero
     * Post: La posición del objeto alfil en el tablero ahora son los parametros de la funcion
     */
    void actualizarPosPieza(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
}
