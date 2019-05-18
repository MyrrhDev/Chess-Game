package Domini;

import java.util.ArrayList;

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
        this.pts = 5;
    }

    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    @Override
    public boolean esMovimientoOk(final Movimiento m, char estadoTablero[][]) {
        int movX = m.getToX(), movY = m.getToY();
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            if(movX == posX && movY != posY) {
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

    @Override
    ArrayList<Movimiento> movimientosPosibles(Tablero tablero) {
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

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

}