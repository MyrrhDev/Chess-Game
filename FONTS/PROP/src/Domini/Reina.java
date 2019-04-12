package Domini;

import java.util.HashMap;

public class Reina extends Pieza {
    int pts = 5;
    public Reina() {

    }

    /* Pre: Cierto
     * Post: Se crea un objeto torre con los parámetros esNegra, id, posX, posY
     */
    public Reina(boolean esNegra, Integer id, int posX, int posY) {
        this.esNegra = esNegra;
        this.id = id;
    }

    /*
     * Pre: Cierto
     * Post: Verifica que el movimiento horizontal o vertical sea correcto
     */
    boolean movimientoHorizontalVerticalOK(int posX, int posY, int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        if(movX == posX && movY != posY) { //horizontal
            //derecha
            if(movY > posY) {
                for (int j = posY + 1; j < movY; ++j)
                    if (estadoTablero[movX][j] != 0) //me encuentro una pieza en mi camino
                        return false;
                if(estadoTablero[movX][movY] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                    Pieza p = piezasTablero.get(estadoTablero[movX][movY]);
                    if (p.isEsNegra() == esNegra) //tenemos el mismo color
                        return false;
                    else return true;
                }
                else return true;
            }
            else if(movY < posY) {
                //izquierda
                for (int j = posY - 1; j > movY; --j)
                    if (estadoTablero[movX][j] != 0) //me encuentro una pieza en mi camino
                        return false;
                if(estadoTablero[movX][movY] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                    Pieza p = piezasTablero.get(estadoTablero[movX][movY]);
                    if (p.isEsNegra() == esNegra) //tenemos el mismo color
                        return false;
                    else return true;
                }
                else return true;
            }
        }
        else if(movX != posX && movY == posY) { //vertical
            //abajo
            if(movX > posX) {
                for (int i = posX + 1; i < movX; ++i)
                    if (estadoTablero[i][movY] != 0) //me encuentro una pieza en mi camino
                        return false;
                if(estadoTablero[movX][movY] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                    Pieza p = piezasTablero.get(estadoTablero[movX][movY]);
                    if (p.isEsNegra() == esNegra) //tenemos el mismo color
                        return false;
                    else return true;
                }
                else return true;
            }
            //arriba
            if(movX < posX) {
                for (int i = posX - 1; i > movX; --i)
                    if (estadoTablero[i][movY] != 0) //me encuentro una pieza en mi camino
                        return false;
                if(estadoTablero[movX][movY] != 0) { //si hay una pieza en mi destino, ver si puedo matarla
                    Pieza p = piezasTablero.get(estadoTablero[movX][movY]);
                    if (p.isEsNegra() == esNegra) //tenemos el mismo color
                        return false;
                    else return true;
                }
                else return true;
            }
        }
        return false;
    }

    /*
     * Pre: Cierto
     * Post: Verifica que el movimiento diagonal sea correcto
     */
    boolean movimientoDiagonalOK(int posX, int posY, int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
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
        return false;
    }

    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    boolean esMovimientoOk(int posX, int posY, int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            //seguidamente verificamos que el movimiento sea en horizontal o vertical estrictamente
            if (movX == posX && movY != posY || movX != posX && movY == posY) { //horizontal o vertical
                return movimientoHorizontalVerticalOK(posX, posY, movX, movY, estadoTablero, piezasTablero);
            }
            else {
                return movimientoDiagonalOK(posX, posY, movX, movY, estadoTablero, piezasTablero);
            }
        }
        return false;
    }

    public boolean isEsNegra() {
        return esNegra;
    }

    public void setEsNegra(boolean esNegra) {
        this.esNegra = esNegra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    void setPts(int pts) {
        this.pts = pts;
    }

    int getPts() {
        return this.pts;
    }

}
