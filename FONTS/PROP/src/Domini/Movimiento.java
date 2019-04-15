package Domini;

public class Movimiento {
    int x; //posicion x del tablero donde me puedo mover
    int y; //posicion y del tablero donde me puedo mover
    char p; //posible pieza p que puedo matar. si no hay ninguna pieza a matar valor de p = '-'

    public Movimiento(int x, int y, char p) {
        this.x = x;
        this.y = y;
        this.p = p;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getP() {
        return p;
    }

    public void setP(char p) {
        this.p = p;
    }
}