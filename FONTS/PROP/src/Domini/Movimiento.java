package Domini;

public class Movimiento {
    int fromX;
    int fromY;

    int toX; //posicion x del tablero donde me puedo mover
    int toY; //posicion y del tablero donde me puedo mover
    char p; //posible pieza p que puedo matar. si no hay ninguna pieza a matar valor de p = '-'

    Movimiento(int fromX, int fromY, int toX, int toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.p = '-';
    }

    Movimiento(int fromX, int fromY, int toX, int toY, char p) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.p = p;
    }

    Movimiento(int fromX, int fromY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.p = '-';
    }

    public int getFromY() {
        return fromY;
    }

    public int getFromX() {
        return fromX;
    }

    public char getP() {
        return p;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public void setP(char p) {
        this.p = p;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }

}