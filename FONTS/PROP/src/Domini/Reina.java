package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Reina extends Pieza {

    public Reina() {
        this.firstMove = false;
    }

    /* Pre: Cierto
     * Post: Se crea un objeto torre con los parámetros esNegra, id, posX, posY
     */
    public Reina(boolean esNegra, int posX, int posY) {
        this.esNegra = esNegra;
        firstMove = false;
        if(this.esNegra) this.tipo = 'q';
        else this.tipo = 'Q';
        this.posX = posX;
        this.posY = posY;
    }

    /*
     * Pre: Cierto
     * Post: Verifica que el movimiento horizontal o vertical sea correcto
     */
    boolean movimientoHorizontalVerticalOK(final Movimiento m, char estadoTablero[][]) {
        //int posX = m.getFromX(), posY = m.getFromY();
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
                        if (estadoTablero[i][movY] != '0') //me encuentro una pieza en mi camino
                            return false;
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

    /*
     * Pre: Cierto
     * Post: Verifica que el movimiento diagonal sea correcto
     */
    boolean movimientoDiagonalOK(final Movimiento m, final char estadoTablero[][]) {
        //int posX = m.getFromX(), posY = m.getFromY();
        int movX = m.getToX(), movY = m.getToY();
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
                        if (estadoTablero[i][j] != '0') //me encuentro una pieza en mi camino
                            return false;
                        ++i;
                        --j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j+1 i queremos revisar hasta i, j
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[i][j])) return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else if(auxX > 0 && auxY > 0) { //movimiento hacia esquina inferior derecha
                    ++i;
                    ++j;
                    while(i < (movX) && j < (movY)) {
                        if (estadoTablero[i][j] != '0') //me encuentro una pieza en mi camino
                            return false;
                        ++i;
                        ++j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j-1 i queremos revisar hasta i, j
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else if(auxX < 0 && auxY < 0) { //movimiento hacia esquina superior izquierda
                    --i;
                    --j;
                    while(i > (movX) && j > (movY)) {
                        if (estadoTablero[i][j] != '0') //me encuentro una pieza en mi camino
                            return false;
                        --i;
                        --j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j-1 i queremos revisar hasta i, j
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[i][j])) return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else if(auxX < 0 && auxY > 0) { //movimiento hacia esquina superior derecha
                    --i;
                    ++j;
                    while(i > (movX) && j < (movY)) {
                        if (estadoTablero[i][j] != '0') //me encuentro una pieza en mi camino
                            return false;
                        --i;
                        ++j;
                    } //cuando salimos del bucle estamos en la posicion i-1, j-1 i queremos revisar hasta i, j
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
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

    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    @Override
    boolean esMovimientoOk(final Movimiento m, char estadoTablero[][]) {
        //int posX = m.getFromX(), posY = m.getFromY();
        int movX = m.getToX(), movY = m.getToY();
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            //seguidamente verificamos que el movimiento sea en horizontal o vertical estrictamente
            if (movX == posX && movY != posY || movX != posX && movY == posY) { //horizontal o vertical
                return movimientoHorizontalVerticalOK(m, estadoTablero);
            }
            else {
                return movimientoDiagonalOK(m, estadoTablero);
            }
        }
        return false;
    }


    private ArrayList<Movimiento> movimientosPosiblesDiagonales(char estadoTablero[][]) {
        ArrayList<Movimiento> listResult = new ArrayList<>();
        int i = posX, j = posY;
        boolean end = false; //end será true cuando llegue al final del tablero o me encuentre con una pieza
        // amiga o enemiga (en el caso particular de la torre)

        //diagonal superior derecha --i ++j
        --i;
        ++j;
        while(i >= 0 && j < 8 && !end) {
            if(estadoTablero[i][j] == '0') {
                Movimiento r = new Movimiento(posX, posY, i, j);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(posX, posY, i, j, estadoTablero[i][j]);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
            }
            --i;
            ++j;
        }

        end = false;
        i = posX;
        j = posY;
        ++i;
        ++j;
        //diagonal inferior derecha
        while(i < 8 && j < 8 && !end) {
            if(estadoTablero[i][j] == '0') {
                Movimiento r = new Movimiento(posX, posY, i, j);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(posX, posY, i, j, estadoTablero[i][j]);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
            }
            ++i;
            ++j;
        }
        //diagonal inferior izquierda

        end = false;
        i = posX;
        j = posY;
        ++i;
        --j;
        while(i < 8 && j < 8 && !end) {
            if(estadoTablero[i][j] == '0') {
                Movimiento r = new Movimiento(posX, posY, i, j);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(posX, posY, i, j, estadoTablero[i][j]);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
            }
            ++i;
            --j;
        }
        //diagonal superior izquierda
        end = false;
        i = posX;
        j = posY;
        --i;
        --j;
        while(i >= 0 && j >= 0 && !end) {
            if(estadoTablero[i][j] == '0') {
                Movimiento r = new Movimiento(posX, posY, i, j);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(posX, posY, i, j, estadoTablero[i][j]);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
            }
            --i;
            --j;
        }

        return listResult;
    }

    private ArrayList<Movimiento> movimientosPosiblesHorVert(char estadoTablero[][]) {
        ArrayList<Movimiento> listResult = new ArrayList<>();
        //debo mirar 4 posibles movimientos: arriba, abajo izquierda y derecha
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

    @Override
    ArrayList<Movimiento> movimientosPosibles(final char estadoTablero[][]) {
        ArrayList<Movimiento> listResult = new ArrayList<>();
        ArrayList<Movimiento> ltemp1 = movimientosPosiblesDiagonales(estadoTablero);
        ArrayList<Movimiento> ltemp2 = movimientosPosiblesHorVert(estadoTablero);
        listResult.addAll(ltemp1);
        listResult.addAll(ltemp2);
        return listResult;
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

    public void setTipo(char t) {
        this.tipo = t;
    }

    public char getTipo() {
        return this.tipo;
    }
}