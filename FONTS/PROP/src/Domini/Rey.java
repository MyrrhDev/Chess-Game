package Domini;

import java.util.ArrayList;
import java.util.HashMap;

public class Rey extends Pieza {
    @Override
    boolean esMovimientoOk(final Movimiento m, char estadoTablero[][]) { return false; }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    ArrayList<Movimiento> movimientosPosibles(int posX, int posY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) {
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
