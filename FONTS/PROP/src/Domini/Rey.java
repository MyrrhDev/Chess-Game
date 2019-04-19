package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Rey extends Pieza {
    //TODO: Necesitamos ver la posicion del Rey de alguna manera...
    int posX;
    int posY;

    public Rey (boolean esNegra, int x, int y) {
        this.posX = x;
        this.posY = y;
        this.esNegra = esNegra;
        if(this.esNegra) {
            this.tipo = 'k';
        }
        else this.tipo = 'K';
        this.firstMove = false;
    }

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

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    @Override
    ArrayList<Movimiento> movimientosPosibles(final char estadoTablero[][]) {
        ArrayList<Movimiento> movs = new ArrayList<>();

        int i = posX-1;
        int j= posY-1;

        for (int k = i; k <= posX+1; k++) {
            for (int l = j; l <= posY+1; l++) {
                if(k >= 0 && k < 8 && l >= 0 && l < 8) {
                    if (k != posX || l != posY) {
                        if (estadoTablero[k][l] == '0') {
                            Movimiento m = new Movimiento(this.posX, this.posY, k, l);
                            movs.add(m);
                        } else {
                            if ((this.esNegra && !Character.isLowerCase(estadoTablero[k][l])) || (!this.esNegra && Character.isLowerCase(estadoTablero[k][l]))) {
                                Movimiento m = new Movimiento(this.posX, this.posY, k, l, estadoTablero[k][l]);
                                movs.add(m);
                            }
                        }

                    }
                }
            }
        }
        return movs;
    }

    public void setTipo(char t) {
        this.tipo = t;
    }

    public char getTipo() {
        return this.tipo;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}