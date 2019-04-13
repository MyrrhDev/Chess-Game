package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Torre extends Pieza {

    public Torre() {

    }

    /* Pre: Cierto
     * Post: Se crea un objeto torre con los parámetros esNegra, id, posX, posY
     */
    public Torre(boolean esNegra, Integer id) {
        this.esNegra = esNegra;
        this.id = id;
        if(this.esNegra) this.tipo = 'r';
        else this.tipo = 'R';
    }

    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    public boolean esMovimientoOk(int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
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

    ArrayList<res> movimientosPosibles(int posX, int posY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        ArrayList<res> listResult = new ArrayList<>();
        //debo mirar 4 posibles movimientos: arriba, abajo izquierda y derecha
        int i = posX, j = posY;
        boolean end = false; //end será true cuando llegue al final del tablero o me encuentre con una pieza
        // amiga o enemiga (en el caso particular de la torre

        //arriba
        --i;
        while(i >= 0 && !end) {
            if(estadoTablero[i][posY] == 0) {
                res r = new res(i, posY, '-');
                listResult.add(r);
            }
            if(estadoTablero[i][posY] != 0) {
                if(piezasTablero.containsKey(estadoTablero[i][posY])) {
                    Pieza p2 = piezasTablero.get(estadoTablero[i][posY]);
                    if(this.esNegra != p2.esNegra) {
                        res r = new res(i, posY, p2.getTipo());
                        listResult.add(r);
                        end = true;
                    }
                }
            }
            --i;
        }

        end = false;
        i = posX;
        j = posY;
        //derecha
        ++j;
        while(j < 8 && !end) {
            if(estadoTablero[posX][j] == 0) {
                res r = new res(posX, j, '-');
                listResult.add(r);
            }
            if(estadoTablero[posX][j] != 0) {
                if(piezasTablero.containsKey(estadoTablero[posX][j])) {
                    Pieza p2 = piezasTablero.get(estadoTablero[posX][j]);
                    if(this.esNegra != p2.esNegra) {
                        res r = new res(posX, j, p2.getTipo());
                        listResult.add(r);
                        end = true;
                    }
                }
            }
            ++j;
        }

        end = false;
        i = posX;
        j = posY;
        //abajo
        ++i;
        while(i < 8 && !end) {
            if(estadoTablero[i][posY] == 0) {
                res r = new res(i, posY, '-');
                listResult.add(r);
            }
            if(estadoTablero[i][posY] != 0) {
                if(piezasTablero.containsKey(estadoTablero[i][posY])) {
                    Pieza p2 = piezasTablero.get(estadoTablero[i][posY]);
                    if(this.esNegra != p2.esNegra) {
                        res r = new res(i, posY, p2.getTipo());
                        listResult.add(r);
                        end = true;
                    }
                }
            }
            ++i;
        }

        end = false;
        i = posX;
        j = posY;
        //izquierda
        --j;
        while(j >= 0 && !end) {
            if(estadoTablero[posX][j] == 0) {
                res r = new res(posX, j, '-');
                listResult.add(r);
            }
            if(estadoTablero[posX][j] != 0) {
                if(piezasTablero.containsKey(estadoTablero[posX][j])) {
                    Pieza p2 = piezasTablero.get(estadoTablero[posX][j]);
                    if(this.esNegra != p2.esNegra) {
                        res r = new res(posX, j, p2.getTipo());
                        listResult.add(r);
                        end = true;
                    }
                }
            }
            --j;
        }
        return listResult;
    }

    public void setTipo(char t) {
        this.tipo = t;
    }

    public char getTipo() {
        return this.tipo;
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

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

}
