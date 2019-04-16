package Domini;

public class Movimiento {
    int fromX;
    int fromY;

    int toX; //posicion x del tablero donde me puedo mover
    int toY; //posicion y del tablero donde me puedo mover
    char p; //posible pieza p que puedo matar. si no hay ninguna pieza a matar valor de p = '-'

    public int getFromX() {
        return fromX;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public int getToX() {
        return toX;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public int getToY() {
        return toY;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }

    public char getP() {
        return p;
    }

    public void setP(char p) {
        this.p = p;
    }
}
