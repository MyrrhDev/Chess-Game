package Domini;

import java.util.HashMap;

public class Rey extends Pieza {
    int pts = 1;
    boolean esMovimientoOk(int posX, int posY, int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero) { return false; }

    void setPts(int pts) {
        this.pts = pts;
    }

    int getPts() {
        return this.pts;
    }
}
