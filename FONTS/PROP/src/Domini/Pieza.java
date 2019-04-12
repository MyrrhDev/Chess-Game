package Domini;

import java.util.HashMap;

public abstract class Pieza {
    public boolean esNegra; //true si la pieza es de color negro. Sino false que representa el blanco
    public int id;

    public boolean isEsNegra() {
        return esNegra;
    }

    public void setEsNegra(boolean esNegra) {
        this.esNegra = esNegra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    abstract boolean esMovimientoOk(int posX, int posY, int movX, int movY, int estadoTablero[][], HashMap<Integer, Pieza> piezasTablero);
}
