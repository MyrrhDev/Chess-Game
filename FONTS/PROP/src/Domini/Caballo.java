package Domini;


import java.util.ArrayList;
import java.util.HashMap;

public class Caballo extends Pieza {

    public Caballo() {

    }

    /* Pre: Cierto
     * Post: Se crea un objeto torre con los parámetros esNegra, id, posX, posY
     */
    public Caballo(boolean esNegra, Integer id) {
        this.esNegra = esNegra;
        this.id = id;
        firstMove = false;
    }

    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    boolean esMovimientoOk(int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        int posX = -1, posY = -1;
        int x = 0, y = 0;
        boolean found = false;
        while(x < 8 && !found) {
            y = 0;
            while (y < 8 && !found) {
                if(estadoTablero[x][y] == this.id) {
                    found = true;
                    posX = x;
                    posY = y;
                }
                ++y;
            }
            ++x;
        }
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            int auxX = movX - posX;
            int auxY = movY - posY;

            if((auxX > -3 && auxX < 3) && (auxY > -3 && auxY < 3)){ //movimiento alguno valido

                if ((auxX == -2 && auxY == -1) || (auxX == -1 && auxY == -2) || (auxX == 2 && auxY == -1) ||
                        (auxX == 1 && auxY == -2) || (auxX == -2 && auxY == 1) || (auxX == -1 && auxY == 2) || (auxX == 2 && auxY == 1) || (auxX == 1 && auxY == 2)) {
                    if (estadoTablero[movX][movY] != 0) { //me encuentro una pieza en mi camino
                        Pieza p = piezasTablero.get(estadoTablero[movX][movY]);
                        if (p.isEsNegra() == esNegra) return false; //no lo puedo matar porque es de mi equipo
                        else return true;
                    } else return true;
                }
            }
            else return false;
        }
        return false;
    }

    ArrayList<Movimiento> movimientosPosibles(int posX, int posY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        ArrayList<Movimiento> Movimiento = new ArrayList<>();
        return Movimiento;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public void setTipo(char t) {
        this.tipo = t;
    }

    public char getTipo() {
        return this.tipo;
    }

}
