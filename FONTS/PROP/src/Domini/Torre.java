package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Torre extends Pieza {

    public Torre() {

    }

    /* Pre: Cierto
     * Post: Se crea un objeto torre con los parámetros esNegra, id, posX, posY
     */
    public Torre(boolean esNegra, int posX, int posY) {
        this.esNegra = esNegra;
        if(this.esNegra) this.tipo = 'r';
        else this.tipo = 'R';
        this.posX = posX;
        this.posY = posY;
        pts = 5;
    }

    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    @Override
    public boolean esMovimientoOk(final Movimiento m, char estadoTablero[][]) {
        //int posX = posX, posY = posY;
        int movX = m.getToX(), movY = m.getToY();
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            //seguidamente verificamos que el movimiento sea en horizontal o vertical estrictamente
            if(movX == posX && movY != posY) { //horizontal
                //derecha
                if(movY > posY) {
                    for (int j = posY + 1; j < movY; ++j)
                        if (estadoTablero[movX][j] != '0') //me encuentro una pieza en mi camino
                            return false;
                    if(estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    }
                    else return true;
                }
                else if(movY < posY) {
                    //izquierda
                    for (int j = posY - 1; j > movY; --j)
                        if (estadoTablero[movX][j] != '0') //me encuentro una pieza en mi camino
                            return false;
                    if(estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    }
                    else return true;
                }
            }
            else if(movX != posX && movY == posY) { //vertical
                //abajo
                if(movX > posX) {
                    for (int i = posX + 1; i < movX; ++i)
                        if (estadoTablero[i][movY] != '0') {//me encuentro una pieza en mi camino
                            return false;
                        }
                    if(estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    }
                    else return true;
                }
                //arriba
                if(movX < posX) {
                    for (int i = posX - 1; i > movX; --i)
                        if (estadoTablero[i][movY] != '0') //me encuentro una pieza en mi camino
                            return false;
                    if(estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    }
                    else return true;
                }
            }
            else return false;
        }
        return false;
    }

    @Override
    ArrayList<Movimiento> movimientosPosibles(char estadoTablero[][]) {
        ArrayList<Movimiento> listResult = new ArrayList<>();
        //debo mirar 4 posibles movimientos: arriba, abajo izquierda y derecha
        //int i = posX, j = posY;
        int i = posX, j = posY;
        boolean end = false; //end será true cuando llegue al final del tablero o me encuentre con una pieza
        // amiga o enemiga (en el caso particular de la torre)

        //arriba
        --i;
        while(i >= 0 && !end) {
            if(estadoTablero[i][posY] == '0') {
                Movimiento r = new Movimiento(posX, posY, i, posY);
                listResult.add(r);
            }
            if(estadoTablero[i][posY] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][posY])) {
                    Movimiento r = new Movimiento(posX, posY, i, j, estadoTablero[i][posY]);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
            }
            --i;
        }

        end = false;
        i = posX;
        j = posY;
        //derecha
        ++j;
        while(j < 8 && !end) {
            if(estadoTablero[posX][j] == '0') {
                Movimiento r = new Movimiento(posX, posY, posX, j);
                listResult.add(r);
            }
            if(estadoTablero[posX][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[posX][j])) {
                    Movimiento r = new Movimiento(posX, posY, posX, j, estadoTablero[posX][j]);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
            }
            ++j;
        }

        end = false;
        i = posX;
        j = posY;
        //abajo
        ++i;
        while(i < 8 && !end) {
            if(estadoTablero[i][posY] == '0') {
                Movimiento r = new Movimiento(posX, posY, i, posY);
                listResult.add(r);
            }
            if(estadoTablero[i][posY] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][posY])) {
                    Movimiento r = new Movimiento(posX, posY, i, posY, estadoTablero[i][posY]);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
            }
            ++i;
        }

        end = false;
        i = posX;
        j = posY;
        //izquierda
        --j;
        while(j >= 0 && !end) {
            if(estadoTablero[posX][j] == '0') {
                Movimiento r = new Movimiento(posX, posY, posX, j);
                listResult.add(r);
            }
            if(estadoTablero[posX][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[posX][j])) {
                    Movimiento r = new Movimiento(posX, posY, posX, j, estadoTablero[posX][j]);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
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
