package Domini;

import java.util.ArrayList;

public class Alfil extends Pieza {


    public Alfil() {
        this.firstMove = false;
    }

    /* Pre: Cierto
     * Post: Se crea un objeto alfil con los parámetros esNegra, id, posX, posY
     */
    public Alfil(boolean esNegra, int posX, int posY) {
        this.esNegra = esNegra;
        if(this.esNegra) {
            this.tipo = 'b';
        }
        else this.tipo = 'B';
        this.firstMove = false;
        this.posX = posX;
        this.posY = posY;
        this.pts = 3;
    }

    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    @Override
    boolean esMovimientoOk(final Movimiento m, final char estadoTablero[][]) {
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
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') {
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
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') {
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
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') {
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
                    if(((i-1 == movX && j-1 == movY) || (i == movX && j == movY)) && estadoTablero[movX][movY] != '0') {
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

    @Override
    ArrayList<Movimiento> movimientosPosibles(final char estadoTablero[][]) {
        ArrayList<Movimiento> listResult = new ArrayList<>();
        int i = this.posX, j = this.posY;
        boolean end = false;
        --i;
        ++j;
        while(i >= 0 && j < 8 && !end) {
            if(estadoTablero[i][j] == '0') {
                Movimiento r = new Movimiento(this, posX, posY, i, j);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, j, estadoTablero[i][j]);
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
                Movimiento r = new Movimiento(this,posX, posY, i, j);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, j, estadoTablero[i][j]);
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
                Movimiento r = new Movimiento(this, posX, posY, i, j);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, j, estadoTablero[i][j]);
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
                Movimiento r = new Movimiento(this,posX, posY, i, j);
                listResult.add(r);
            }
            else if(estadoTablero[i][j] != '0') {
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[i][j])) {
                    Movimiento r = new Movimiento(this, posX, posY, i, j, estadoTablero[i][j]);
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


    public void setTipo(char t) {
        this.tipo = t;
    }

    public char getTipo() {
        return this.tipo;
    }
}