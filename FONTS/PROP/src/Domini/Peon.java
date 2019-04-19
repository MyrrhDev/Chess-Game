
package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Peon extends Pieza {

    public boolean firstMove;

    /* Pre: Cierto
     * Post: Se crea un objeto peon y se inicializa si es su primer movimiento
     */
    public Peon() {
        firstMove = false;

    }

    /* Pre: Cierto
     * Post: Se crea un objeto peon con los parámetros esNegra, id, posX, posY
     */
    public Peon(boolean esNegra, boolean firstMove, int posX, int posY) {
        this.esNegra = esNegra;
        if(this.esNegra) {
            tipo = 'p';
        }
        else tipo = 'P';
        this.firstMove = firstMove;
        this.posX = posX;
        this.posY = posY;
    }



    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    @Override
    boolean esMovimientoOk(final Movimiento m, final char estadoTablero[][]) {
        //int posX = this.posX, posY = this.posY;
        int movX = m.getToX(), movY = m.getToY();
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            int auxX = movX - posX;
            int auxY = movY - posY;

            if (!this.esNegra && movX < posX && movY != posY) { //movimiento alguno valido (soy negra)
                //Anyone to kill?  //Diagonal solo si se puede matar
                if (auxX == -1 && auxY == -1) { //movimiento hacia esquina superior izquierda
                    if (estadoTablero[movX][movY] != '0') {
                        //me encuentro una pieza en mi camino
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    } else return false;
                } else if (auxX == -1 && auxY == 1) { //movimiento hacia esquina superior derecha
                    if (estadoTablero[movX][movY] != '0') {
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    } else return false;
                } else if (movY == posY && movX < posX) { //mov hacia adelante
                    if (firstMove && auxY == -2) {
                        if (estadoTablero[movX][movY] == '0') { //no hay pieza alguna
                            return true;
                        }
                    } else if (auxY == -2 && !firstMove) return false;

                    else if (auxX == -1) {
                        if (estadoTablero[movX][movY] == '0') { //no hay pieza alguna
                            return true;
                        }
                    } else return false;
                } else return false; //la pieza no se ha movido realmente
            }
            else {
                if (auxX == 1 && auxY == 1) { //movimiento hacia esquina inferior izquierda
                    if (estadoTablero[movX][movY] != '0') {
                        //me encuentro una pieza en mi camino
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    } else return false;
                } else if (auxX == 1 && auxY == 1) { //movimiento hacia esquina inferior derecha
                    if (estadoTablero[movX][movY] != '0') {
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    } else return false;
                } else if (movY == posY && movX > posX) { //mov hacia adelante
                    if (firstMove && auxY == 2) {
                        if (estadoTablero[movX][movY] == '0') { //no hay pieza alguna
                            return true;
                        }
                    } else if (auxY == 2 && !firstMove) return false;

                    else if (auxX == 1) {
                        if (estadoTablero[movX][movY] == '0') { //no hay pieza alguna
                            return true;
                        }
                    } else return false;
                } else return false; //la pieza no se ha movido realmente
            }
        }
        return false;
    }

    @Override
    ArrayList<Movimiento> movimientosPosibles(char estadoTablero[][]) {
        ArrayList<Movimiento> listResult = new ArrayList<>();
        //int posX = this.posX, posY = this.posY;
        if(this.esNegra) {
            if((posX+1 < 8) && (estadoTablero[posX+1][posY] == '0')) { //si no hay nada
                Movimiento r = new Movimiento(this.posX, this.posY,posX+1, posY);
                listResult.add(r);
            }
            if(this.firstMove) { //una pos más
                if((posX+2 < 8) && (estadoTablero[posX+2][posY] == '0')) { //si no hay nada
                    Movimiento r = new Movimiento(this.posX, this.posY, posX+2, posY);
                    listResult.add(r);
                }
            }
            if((posX+1 < 8 & posY-1 > 0) && (estadoTablero[posX+1][posY-1] != '0')) { //miramos si podemos matar a otra pieza
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[posX+1][posY-1])) {
                    Movimiento r = new Movimiento(this.posX, this.posY, posX+1, posY-1, estadoTablero[posX+1][posY-1]);
                    listResult.add(r);
                }
            }
            if((posX+1 < 8 & posY+1 < 8) && (estadoTablero[posX+1][posY+1] != '0')) { //miramos si podemos matar a otra pieza
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[posX+1][posY+1])) {
                    Movimiento r = new Movimiento(this.posX, this.posY, posX+1, posY+1, estadoTablero[posX+1][posY+1]);
                    listResult.add(r);
                }
            }
        }
        if(!this.esNegra) {
            if((posX-1 >= 0) && (estadoTablero[posX-1][posY] == '0')) { //si no hay nada
                Movimiento r = new Movimiento(this.posX, this.posY, posX-1, posY);
                listResult.add(r);
            }
            if(this.firstMove) { //una pos más
                if((posX-2 >= 0) && (estadoTablero[posX-2][posY] == '0')) { //si no hay nada
                    Movimiento r = new Movimiento(this.posX, this.posY, posX-2, posY);
                    listResult.add(r);
                }
            }
            if((posX-1 >= 0 & posY-1 >= 0) && (estadoTablero[posX-1][posY-1] != '0')) { //miramos si podemos matar a otra pieza
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[posX-1][posY-1])) {
                    Movimiento r = new Movimiento(this.posX, this.posY, posX-1, posY-1, estadoTablero[posX-1][posY-1]);
                    listResult.add(r);
                }
            }
            if((posX-1 >= 0 & posY+1 < 8) && (estadoTablero[posX-1][posY+1] != '0')) { //miramos si podemos matar a otra pieza
                if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[posX-1][posY+1])) {
                    Movimiento r = new Movimiento(this.posX, this.posY, posX-1, posY+1, estadoTablero[posX-1][posY+1]);
                    listResult.add(r);
                }
            }
        }
        return listResult;
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