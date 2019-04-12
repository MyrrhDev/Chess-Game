package Domini;

import java.util.HashMap;

public class Torre extends Pieza {
    int pts = 2;
    public Torre() {

    }

    /* Pre: Cierto
     * Post: Se crea un objeto torre con los parámetros esNegra, id, posX, posY
     */
    public Torre(boolean esNegra, Integer id, int posX, int posY) {
        this.esNegra = esNegra;
        this.id = id;
    }

    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    public boolean esMovimientoOk(int posX, int posY, int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            //seguidamente verificamos que el movimiento sea en horizontal o vertical estrictamente
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
            else return false;
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
