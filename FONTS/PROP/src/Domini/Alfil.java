package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Alfil extends Pieza {
    int pts = 3;

    public Alfil() {

    }

    /* Pre: Cierto
     * Post: Se crea un objeto alfil con los parámetros esNegra, id, posX, posY
     */
    public Alfil(boolean esNegra, Integer id) {
        this.esNegra = esNegra;
        this.id = id;
        if(this.esNegra) {
            this.tipo = 'b';
        }
        else this.tipo = 'B';
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
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            int auxX = movX - posX;
            int auxY = movY - posY;
            int i = posX, j = posY;
            //seguidamente verificamos que el movimiento sea en horizontal o vertical estrictamente
            if(movX != posX && movY != posY) { //el movimiento es en diagonal estrictamente
                if(auxX > 0 && auxY < 0) { //movimiento hacia esquina inferior izquierda
                    ++i;
                    --j;
                    while(i < (movX) && j > (movY)) {
                        if (estadoTablero[i][j] != 0) //me encuentro una pieza en mi camino
                            return false;
                        ++i;
                        --j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j+1 i queremos revisar hasta i, j
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                        Pieza p = piezasTablero.get(estadoTablero[i][j]);
                        if (p.isEsNegra() == esNegra) //tenemos el mismo color
                            return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else if(auxX > 0 && auxY > 0) { //movimiento hacia esquina inferior derecha
                    ++i;
                    ++j;
                    while(i < (movX) && j < (movY)) {
                        if (estadoTablero[i][j] != 0) //me encuentro una pieza en mi camino
                            return false;
                        ++i;
                        ++j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j-1 i queremos revisar hasta i, j
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                        Pieza p = piezasTablero.get(estadoTablero[i][j]);
                        if (p.isEsNegra() == esNegra) //tenemos el mismo color
                            return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else if(auxX < 0 && auxY < 0) { //movimiento hacia esquina superior izquierda
                    --i;
                    --j;
                    while(i > (movX) && j > (movY)) {
                        if (estadoTablero[i][j] != 0) //me encuentro una pieza en mi camino
                            return false;
                        --i;
                        --j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j-1 i queremos revisar hasta i, j
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                        Pieza p = piezasTablero.get(estadoTablero[i][j]);
                        if (p.isEsNegra() == esNegra) //tenemos el mismo color
                            return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else if(auxX < 0 && auxY > 0) { //movimiento hacia esquina superior derecha
                    --i;
                    ++j;
                    while(i > (movX) && j < (movY)) {
                        if (estadoTablero[i][j] != 0) //me encuentro una pieza en mi camino
                            return false;
                        --i;
                        ++j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j-1 i queremos revisar hasta i, j
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                        Pieza p = piezasTablero.get(estadoTablero[i][j]);
                        if (p.isEsNegra() == esNegra) //tenemos el mismo color
                            return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else return false;
            }
            else return false;
        }
        return false;
    }

    ArrayList<res> movimientosPosibles(int posX, int posY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        ArrayList<res> listResult = new ArrayList<>();
        int i = posX, j = posY;
        boolean end = false; //end será true cuando llegue al final del tablero o me encuentre con una pieza
        // amiga o enemiga (en el caso particular de la torre)

        //diagonal superior derecha --i ++j
        --i;
        ++j;
        while(i >= 0 && j < 8 && !end) {
            if(estadoTablero[i][j] == 0) {
                res r = new res(i, j, '-');
                listResult.add(r);
            }
            if(estadoTablero[i][j] != 0) {
                if(piezasTablero.containsKey(estadoTablero[i][j])) {
                    Pieza p2 = piezasTablero.get(estadoTablero[i][j]);
                    if(this.esNegra != p2.esNegra) {
                        res r = new res(i, posY, p2.getTipo());
                        listResult.add(r);
                        end = true;
                    }
                    else end = true;
                }
            }
            --i;
            ++j;
        }
        //diagonal inferior derecha

        //diagonal inferior izquierda

        //diagonal superior izquierda

        return listResult;
    }

    void setPts(int pts) {
        this.pts = pts;
    }

    int getPts() {
        return this.pts;
    }

    public void setTipo(char t) {
        this.tipo = t;
    }

    public char getTipo() {
        return this.tipo;
    }
}