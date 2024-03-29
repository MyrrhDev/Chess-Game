package Domini;
import java.util.ArrayList;

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
        this.pts = 9;
    }

    /*
     * Pre: Cierto
     * Post: Verifica que el movimiento horizontal o vertical sea correcto
     */
    boolean movimientoHorizontalVerticalOK(final Movimiento m, char estadoTablero[][]) {
        int movX = m.getToX(), movY = m.getToY();
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            if(movX == posX && movY != posY) {
                //
                if(movY > posY) {
                    for (int j = posY + 1; j < movY; ++j)
                        if (estadoTablero[movX][j] != '0')
                            return false;
                    if(estadoTablero[movX][movY] != '0') {
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    }
                    else return true;
                }
                else if(movY < posY) {
                    for (int j = posY - 1; j > movY; --j)
                        if (estadoTablero[movX][j] != '0')
                            return false;
                    if(estadoTablero[movX][movY] != '0') {
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    }
                    else return true;
                }
            }
            else if(movX != posX && movY == posY) {
                if(movX > posX) {
                    for (int i = posX + 1; i < movX; ++i)
                        if (estadoTablero[i][movY] != '0')
                            return false;
                    if(estadoTablero[movX][movY] != '0') {
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    }
                    else return true;
                }
                if(movX < posX) {
                    for (int i = posX - 1; i > movX; --i)
                        if (estadoTablero[i][movY] != '0')
                            return false;
                    if(estadoTablero[movX][movY] != '0') {
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
    boolean movimientoDiagonalOK(final Movimiento m, char estadoTablero[][]) {
        int movX = m.getToX(), movY = m.getToY();
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            int auxX = movX - posX;
            int auxY = movY - posY;
            int i = posX, j = posY;
            if(movX != posX && movY != posY) {
                if(auxX > 0 && auxY < 0) {
                    ++i;
                    --j;
                    while(i < (movX) && j > (movY)) {
                        if (estadoTablero[i][j] != '0')
                            return false;
                        ++i;
                        --j;
                    }
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[i][j])) return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else if(auxX > 0 && auxY > 0) {
                    ++i;
                    ++j;
                    while(i < (movX) && j < (movY)) {
                        if (estadoTablero[i][j] != '0')
                            return false;
                        ++i;
                        ++j;
                    }
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else if(auxX < 0 && auxY < 0) {
                    --i;
                    --j;
                    while(i > (movX) && j > (movY)) {
                        if (estadoTablero[i][j] != '0')
                            return false;
                        --i;
                        --j;
                    }
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') { //si hay una pieza en mi destino, ver si puedo matarla
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[i][j])) return false;
                        else return true;
                    }
                    else if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY))) return true;
                    else return false;
                }
                else if(auxX < 0 && auxY > 0) {
                    --i;
                    ++j;
                    while(i > (movX) && j < (movY)) {
                        if (estadoTablero[i][j] != '0')
                            return false;
                        --i;
                        ++j;
                    }
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
        int movX = m.getToX(), movY = m.getToY();
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            if (movX == posX && movY != posY || movX != posX && movY == posY) {
                return movimientoHorizontalVerticalOK(m, estadoTablero);
            }
            else {
                return movimientoDiagonalOK(m, estadoTablero);
            }
        }
        return false;
    }


    private ArrayList<Movimiento> movimientosPosiblesDiagonales(Tablero tablero) {
        final char estadoTablero[][] = tablero.getTablero();
        ArrayList<Movimiento> listResult = new ArrayList<>();
        int i = posX, j = posY;
        boolean end = false;
        --i;
        ++j;
        while(i >= 0 && j < 8 && !end) {
            if(estadoTablero[i][j] == '0') {
                Movimiento r = new Movimiento(this, posX, posY, i, j, tablero);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, j, estadoTablero[i][j], tablero);
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
        while(i < 8 && j < 8 && !end) {
            if(estadoTablero[i][j] == '0') {
                Movimiento r = new Movimiento(this, posX, posY, i, j, tablero);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, j, estadoTablero[i][j], tablero);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
            }
            ++i;
            ++j;
        }
        end = false;
        i = posX;
        j = posY;
        ++i;
        --j;
        while(i < 8 && j >= 0 && !end) {
            if(estadoTablero[i][j] == '0') {
                Movimiento r = new Movimiento(this, posX, posY, i, j, tablero);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, j, estadoTablero[i][j], tablero);
                    listResult.add(r);
                    end = true;
                }
                else end = true;
            }
            ++i;
            --j;
        }
        end = false;
        i = posX;
        j = posY;
        --i;
        --j;
        while(i >= 0 && j >= 0 && !end) {
            if(estadoTablero[i][j] == '0') {
                Movimiento r = new Movimiento(this, posX, posY, i, j, tablero);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, j, estadoTablero[i][j], tablero);
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

    private ArrayList<Movimiento> movimientosPosiblesHorVert(Tablero tablero) {
        final char estadoTablero[][] = tablero.getTablero();
        ArrayList<Movimiento> listResult = new ArrayList<>();
        int i = posX, j = posY;
        boolean end = false;
        --i;
        while(i >= 0 && !end) {
            if(estadoTablero[i][posY] == '0') {
                Movimiento r = new Movimiento(this, posX, posY, i, posY, tablero);
                listResult.add(r);
            }
            if(estadoTablero[i][posY] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][posY])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, j, estadoTablero[i][posY], tablero);
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
        ++j;
        while(j < 8 && !end) {
            if(estadoTablero[posX][j] == '0') {
                Movimiento r = new Movimiento(this, posX, posY, posX, j, tablero);
                listResult.add(r);
            }
            if(estadoTablero[posX][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[posX][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, posX, j, estadoTablero[posX][j], tablero);
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
        ++i;
        while(i < 8 && !end) {
            if(estadoTablero[i][posY] == '0') {
                Movimiento r = new Movimiento(this, posX, posY, i, posY, tablero);
                listResult.add(r);
            }
            if(estadoTablero[i][posY] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][posY])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, posY, estadoTablero[i][posY], tablero);
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
        --j;
        while(j >= 0 && !end) {
            if(estadoTablero[posX][j] == '0') {
                Movimiento r = new Movimiento(this, posX, posY, posX, j, tablero);
                listResult.add(r);
            }
            if(estadoTablero[posX][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[posX][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, posX, j, estadoTablero[posX][j], tablero);
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
    ArrayList<Movimiento> movimientosPosibles(Tablero tablero) {
        final char estadoTablero[][] = tablero.getTablero();
        ArrayList<Movimiento> listResult = new ArrayList<>();
        ArrayList<Movimiento> ltemp1 = movimientosPosiblesDiagonales(tablero);
        ArrayList<Movimiento> ltemp2 = movimientosPosiblesHorVert(tablero);
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
