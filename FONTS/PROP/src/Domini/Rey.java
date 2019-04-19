package Domini;

import java.util.ArrayList;

public class Rey extends Pieza {
    @Override
    boolean esMovimientoOk(final Movimiento m, char estadoTablero[][]) {
        int movX = m.getToX(), movY = m.getToY();
        //primero verificamos que el movimiento deseado no salga del tablero
        if(movX >= 0 && movX < 8 && movY >= 0 && movY < 8) {
            if(estadoTablero[movX][movY] == '0') return true;
            else return false;
        }
        return false;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    @Override
    ArrayList<Movimiento> movimientosPosibles(final Movimiento m, char estadoTablero[][]) {
        ArrayList<Movimiento> Movimiento = new ArrayList<>();
        return Movimiento;
    }

    public void setTipo(char t) {
        this.tipo = t;
    }

    public char getTipo() {
        return this.tipo;
    }
}
