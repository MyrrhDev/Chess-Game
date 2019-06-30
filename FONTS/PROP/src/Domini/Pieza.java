package Domini;

<<<<<<< HEAD
public abstract class Pieza {
    abstract boolean esMovimientoOk();
}
=======
import java.util.ArrayList;

public abstract class Pieza {

    public boolean esNegra;
    public boolean firstMove;
    public char tipo;
    public int posX;
    public int posY;
    public int pts;

    public int getPts() {
        return pts;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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

    abstract boolean esMovimientoOk(final Movimiento m, final char estadoTablero[][]);

    abstract ArrayList<Movimiento> movimientosPosibles(Tablero tablero);

    //abstract ArrayList<Movimiento> movimientosPosibles(char estadoTablero[][]);
}
>>>>>>> Mayra-Logic
