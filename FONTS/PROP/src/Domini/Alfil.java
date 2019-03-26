package Domini;

import java.util.HashMap;

public class Alfil extends Pieza {

    /* Pre: Cierto
     * Post: Se crea un objeto alfil con los parámetros esNegra, id, posX, posY
     */
    public Alfil(boolean esNegra, String id, int posX, int posY) {
        this.esNegra = esNegra;
        this.id = id;
        this.posX = posX;
        this.posY = posY;
    }

    boolean esMovimientoOk(int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            int auxX = movX - posX;
            int auxY = movY - posY;
            int i = posX, j = posY;
            //seguidamente verificamos que el movimiento sea en horizontal o vertical estrictamente
            if(movX != posX && movY != posY) { //el movimiento es en diagonal estrictamente
                if(auxX > 0 && auxY < 0) { //movimiento hacia esquina inferior izquierda
                    while(i < (movX-1) && j > (movY+1)) {
                        if (estadoTablero[i][j] != 0) //me encuentro una pieza en mi camino
                            return false;
                        ++i;
                        --j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j+1 i queremos revisar hasta i, j
                    if(estadoTablero[i][j] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                        Pieza p = piezasTablero.get(estadoTablero[i][j]);
                        if (p.isEsNegra() && esNegra) //tenemos el mismo color
                            return false;
                        else return true;
                    }
                    else return true;
                }
                else if(auxX > 0 && auxY > 0) { //movimiento hacia esquina inferior derecha
                   while(i < (movX-1) && j < (movY-1)) {
                       if (estadoTablero[i][j] != 0) //me encuentro una pieza en mi camino
                           return false;
                       ++i;
                       ++j;
                   } //cuando salimos del bucle estamos en la posicion i-1, j-1 i queremos revisar hasta i, j
                   if(estadoTablero[i][j] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                       Pieza p = piezasTablero.get(estadoTablero[i][j]);
                       if (p.isEsNegra() && esNegra) //tenemos el mismo color
                           return false;
                       else return true;
                   }
                   else return true;
                }
                else if(auxX < 0 && auxY < 0) { //movimiento hacia esquina superior izquierda
                    while(i < (movX+1) && j < (movY+1)) {
                        if (estadoTablero[i][j] != 0) //me encuentro una pieza en mi camino
                            return false;
                        --i;
                        --j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j-1 i queremos revisar hasta i, j
                    if(estadoTablero[i][j] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                        Pieza p = piezasTablero.get(estadoTablero[i][j]);
                        if (p.isEsNegra() && esNegra) //tenemos el mismo color
                            return false;
                        else return true;
                    }
                    else return true;
                }
                else if(auxX < 0 && auxY > 0) { //movimiento hacia esquina superior derecha
                    while(i < (movX+1) && j < (movY-1)) {
                        if (estadoTablero[i][j] != 0) //me encuentro una pieza en mi camino
                            return false;
                        --i;
                        ++j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j-1 i queremos revisar hasta i, j
                    if(estadoTablero[i][j] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                        Pieza p = piezasTablero.get(estadoTablero[i][j]);
                        if (p.isEsNegra() && esNegra) //tenemos el mismo color
                            return false;
                        else return true;
                    }
                    else return true;
                }
                else return false;
            }
            else return false;
        }
        return false;
    }
}