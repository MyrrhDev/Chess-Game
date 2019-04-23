package Domini;


import java.util.ArrayList;

public class Caballo extends Pieza {


    public Caballo() {
        this.firstMove = false;
    }

    /* Pre: Cierto
     * Post: Se crea un objeto torre con los parámetros esNegra, id, posX, posY
     */
    public Caballo(boolean esNegra, int posX, int posY) {
        this.esNegra = esNegra;
        firstMove = false;
        if(this.esNegra) this.tipo = 'n';
        else this.tipo = 'N';
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
            int auxX = movX - this.posX;
            int auxY = movY - this.posY;

            if((auxX > -3 && auxX < 3) && (auxY > -3 && auxY < 3)) { //movimiento alguno valido

                if ((auxX == -2 && auxY == -1) || (auxX == -1 && auxY == -2) || (auxX == 2 && auxY == -1) ||
                        (auxX == 1 && auxY == -2) || (auxX == -2 && auxY == 1) || (auxX == -1 && auxY == 2) || (auxX == 2 && auxY == 1) || (auxX == 1 && auxY == 2)) {
                    if (estadoTablero[movX][movY] != '0') { //me encuentro una pieza en mi camino
                        if(Character.isLowerCase(this.tipo) == Character.isLowerCase(estadoTablero[movX][movY])) return false;
                        else return true;
                    } else return true;
                }
            }
            else return false;
        }
        return false;
    }

    @Override
    ArrayList<Movimiento> movimientosPosibles(char estadoTablero[][]) {
        ArrayList<Movimiento> listResult = new ArrayList<>();
        final int movPos[][] = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        final int sizei = 8, sizej = 2;
        for(int i = 0; i < sizei; ++i) {
            int moveX = posX + movPos[i][0], moveY = posY + movPos[i][1];
            if(moveX >= 0 & moveX < 8 & moveY >= 0 & moveY < 8) {
                if(estadoTablero[moveX][moveY] == '0') {
                    Movimiento r = new Movimiento(this.posX, this.posY, moveX, moveY);
                    listResult.add(r);
                }
                else if(estadoTablero[moveX][moveY] != '0') {
                    if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[moveX][moveY])) {
                        Movimiento r = new Movimiento(this.posX, this.posY, moveX, moveY, estadoTablero[moveX][moveY]);
                        listResult.add(r);
                    }
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