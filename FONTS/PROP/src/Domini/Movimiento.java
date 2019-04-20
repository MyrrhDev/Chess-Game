
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

    //intenta construir un nuevo tablero con el nuevo movimiento
    public Tablero intentar(final Tablero iniTablero) {
        //Tablero temp = new Tablero(iniTablero.getJugador1(), iniTablero.getJugador2(), iniTablero.getPiezasBlancas(), iniTablero.getPiezasNegras());

        Tablero temp = new Tablero(iniTablero);

        temp.ejecutarMovimiento(this);

        //iniTablero.esSuTurno().getMisPiezas().; //ponerlas todas menos la a mover
        //poner las de mi oponente
        //poner "la piezas movida"

        //cambiar de turno en el tablero nuevo
        temp.setTurnoBlancas(!iniTablero.getTurnoBlancas());
        //
        //Guardamos el movimiento que se ha hecho en este nuevo tablero(????)
        return temp;
    }

    //Me dice si es -1-1-1-1
    public boolean esNada() {
        if(this.toY == -1 && this.toX == -1 && this.fromY == -1 && this.fromX == -1) return true;
        else return false;
    }
}