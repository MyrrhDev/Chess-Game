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
