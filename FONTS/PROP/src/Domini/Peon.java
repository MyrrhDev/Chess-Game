package Domini;

import java.util.HashMap;

public class Peon extends Pieza {

    boolean firstMove;

    /* Pre: Cierto
     * Post: Se crea un objeto peon y se inicializa si es su primer movimiento
     */
    public Peon() {
        firstMove = false;

    }

    /* Pre: Cierto
     * Post: Se crea un objeto peon con los parámetros esNegra, id, posX, posY
     */
    public Peon(boolean esNegra, Integer id, int posX, int posY) {
        this.esNegra = esNegra;
        this.id = id;
        this.posX = posX;
        this.posY = posY;
    }



    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */

    boolean esMovimientoOk(int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            int auxX = movX - posX;
            int auxY = movY - posY;

            if (movX < posX && movY != posY) { //movimiento alguno valido
                //Anyone to kill?  //Diagonal solo si se puede matar
                if (auxX == -1 && auxY == -1) { //movimiento hacia esquina superior izquierda
                    if (estadoTablero[movX][movY] != 0) {
                        //me encuentro una pieza en mi camino
                        Pieza p = piezasTablero.get(estadoTablero[movX][movY]);
                        if (p.isEsNegra() == esNegra) return false;
                        else return true;
                    } else return false;
                } else if (auxX == -1 && auxY == 1) { //movimiento hacia esquina superior derecha
                    if (estadoTablero[movX][movY] != 0) {
                        //me encuentro una pieza en mi camino
                        Pieza p = piezasTablero.get(estadoTablero[movX][movY]);
                        if (p.isEsNegra() == esNegra) return false;
                        else return true;
                    } else return false;
                } else if (movY == posY && movX < posX) { //mov hacia adelante
                    if (firstMove && auxY == -2) {
                        if (estadoTablero[movX][movY] == 0) { //no hay pieza alguna
                            return true;
                        }
                    } else if (auxY == -2 && !firstMove) return false;

                    else if (auxX == -1) {
                        if (estadoTablero[movX][movY] == 0) { //no hay pieza alguna
                            return true;
                        }
                    } else return false;
                } else return false; //la pieza no se ha movido realmente
            } else return false;
        }
        return false;
    }

    /*
     * Pre: La posicion, pasada por parametro, es correcta dentro del tablero
     * Post: La posición del objeto alfil en el tablero ahora son los parametros de la funcion
     */
    void actualizarPosPieza(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
}
