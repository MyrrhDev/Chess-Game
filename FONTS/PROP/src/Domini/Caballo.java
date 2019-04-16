package Domini;


import java.util.ArrayList;
import java.util.HashMap;

public class Caballo extends Pieza {

    public Caballo() {

    }

    /* Pre: Cierto
     * Post: Se crea un objeto torre con los parámetros esNegra, id, posX, posY
     */
    public Caballo(boolean esNegra, Integer id) {
        this.esNegra = esNegra;
        this.id = id;
        firstMove = false;
        if(this.esNegra) this.tipo = 'n';
        else this.tipo = 'N';
    }

    /*
     * Pre: Los atributos de la clase Pieza posX y posY están actualizados para la la verificacion del movimiento
     * Post: La funcion devuelve:
     *       * True: Si el movimiento que se quiere realizar es correcto
     *       * False: Si no se puede realizar el movimiento
     */
    @Override
    boolean esMovimientoOk(final Movimiento m, final char estadoTablero[][]) {
        int posX = m.getFromX(), posY = m.getFromY();
        int movX = m.getToX(), movY = m.getToY();
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            int auxX = movX - posX;
            int auxY = movY - posY;

            if((auxX > -3 && auxX < 3) && (auxY > -3 && auxY < 3)){ //movimiento alguno valido

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

    ArrayList<Movimiento> movimientosPosibles(int posX, int posY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
        ArrayList<Movimiento> listResult = new ArrayList<>();
        final int movPos[][] = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        final int sizei = 8, sizej = 2;
        for(int i = 0; i < sizei; ++i) {
            int moveX = posX + movPos[i][0], moveY = posY + movPos[i][1];
                if(moveX >= 0 & moveX < 8 & moveY >= 0 & moveY < 8) {
                    if(estadoTablero[moveX][moveY] == 0) {
                        Movimiento r = new Movimiento(moveX, moveY, '-');
                        listResult.add(r);
                    }
                    if(estadoTablero[moveX][moveY] != 0) {
                        if(piezasTablero.containsKey(estadoTablero[moveX][moveY])) {
                            Pieza p2 = piezasTablero.get(estadoTablero[moveX][moveY]);
                            if(this.esNegra != p2.esNegra) {
                                Movimiento r = new Movimiento(moveX, moveY, p2.getTipo());
                                listResult.add(r);
                            }
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
