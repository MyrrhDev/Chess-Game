package Domini;

import java.util.ArrayList;

public class Rey extends Pieza {
    private int posX;
    private int posY;

    /* Pre: Cierto
     * Post: Se crea un objeto Rey con los parámetros esNegra, posX, posY
     */
    public Rey (boolean esNegra, int x, int y) {
        this.posX = x;
        this.posY = y;
        this.esNegra = esNegra;
        if(this.esNegra) {
            this.tipo = 'k';
        }
        else this.tipo = 'K';
        this.firstMove = false;
        this.pts = 1000;
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
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            if (Math.abs(posX - movX) <= 1 && Math.abs(posY - movY) <= 1) {
                if(estadoTablero[movX][movY] == '0') return true;
                else if(Character.isLowerCase(estadoTablero[movX][movY]) !=
                        Character.isLowerCase(estadoTablero[movX][movY])) return true;
            } else return false;
        }else return false;
        return false;
    }

    /*
     * Pre: El parametro estadoTablero[][] existe y no esta vacio
     * Post: La funcion devuelve un array con todos los movimientos posibles del parametro implicito
     */
    @Override
    ArrayList<Movimiento> movimientosPosibles(Tablero tablero) {
        final char estadoTablero[][] = tablero.getTablero();
        ArrayList<Movimiento> movs = new ArrayList<>();

        int i = posX-1;
        int j= posY-1;

        for (int k = i; k <= posX+1; k++) {
            for (int l = j; l <= posY+1; l++) {
                if(k >= 0 && k < 8 && l >= 0 && l < 8) {
                    if (k != posX || l != posY) {
                        if (estadoTablero[k][l] == '0') {
                            Movimiento m = new Movimiento(this, this.posX, this.posY, k, l);
                            movs.add(m);
                        } else {
                            if ((this.esNegra && !Character.isLowerCase(estadoTablero[k][l])) || (!this.esNegra && Character.isLowerCase(estadoTablero[k][l]))) {
                                Movimiento m = new Movimiento(this, this.posX, this.posY, k, l, estadoTablero[k][l]);
                                movs.add(m);
                            }
                        }

                    }
                }
            }
        }
        return movs;
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

    /* Pre: Cierto
     * Post: Devuelve la X de la posicion del parametro implicito
     */
    public int getPosX() {
        return posX;
    }

    /* Pre: Cierto
     * Post: Devuelve la Y de la posicion del parametro implicito
     */
    public int getPosY() {
        return posY;
    }

    /* Pre: Cierto
     * Post: Asigna la X de la posicion del parametro implicito
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /* Pre: Cierto
     * Post: Asigna la Y de la posicion del parametro implicito
     */
    public void setPosY(int posY) {
        this.posY = posY;
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
}