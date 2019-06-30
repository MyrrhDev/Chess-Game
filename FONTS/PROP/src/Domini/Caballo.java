package Domini;
import java.util.ArrayList;

public class Caballo extends Pieza {

    public Caballo() {
        this.firstMove = false;
    }

    /* Pre: Cierto
     * Post: Se crea un objeto torre con los parámetros esNegra, posX, posY
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
        //int posX = this.posX, posY = this.posY;
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

    /*
     * Pre: El parametro estadoTablero[][] existe y no esta vacio
     * Post: La funcion devuelve un array con todos los movimientos posibles del parametro implicito
     */
    @Override
    ArrayList<Movimiento> movimientosPosibles(Tablero tablero) {

        final char estadoTablero[][] = tablero.getTablero();
        ArrayList<Movimiento> listResult = new ArrayList<>();
        final int movPos[][] = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        final int sizei = 8, sizej = 2;
        for(int i = 0; i < sizei; ++i) {
            int moveX = posX + movPos[i][0], moveY = posY + movPos[i][1];
            if(moveX >= 0 & moveX < 8 & moveY >= 0 & moveY < 8) {
                if(estadoTablero[moveX][moveY] == '0') {
                    Movimiento r = new Movimiento(this, this.posX, this.posY, moveX, moveY, tablero);
                    listResult.add(r);
                }
                else if(estadoTablero[moveX][moveY] != '0') {
                    if(Character.isLowerCase(this.tipo) != Character.isLowerCase(estadoTablero[moveX][moveY])) {
                        Movimiento r = new Movimiento(this, this.posX, this.posY, moveX, moveY, estadoTablero[moveX][moveY], tablero);
                        listResult.add(r);
                    }
                }
            }
        }
        return listResult;
    }

    /* Pre: Cierto
     * Post: Devuelve un boolean que es true si es el primer movimientos de la pieza
     */
    public boolean isFirstMove() {
        return firstMove;
    }

    /* Pre: Cierto
     * Post: Asigna un boolean que es true si es el primer movimientos de la pieza
     */
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    /* Pre: Cierto
     * Post: Asigna el tipo del parametro implicito
     */
    public void setTipo(char t) {
        this.tipo = t;
    }

    /* Pre: Cierto
     * Post: Devuelve el tipo del parametro implicito
     */
    public char getTipo() {
        return this.tipo;
    }

}