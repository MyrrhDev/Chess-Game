package Domini;

public final class MovimientoPrueba {

    Tablero desdeTablero;
    Tablero aTablero;
    Movimiento movimientoPrueba;
    boolean sePuede;


    public MovimientoPrueba(Tablero desdeTablero,Tablero aTablero, Movimiento movimientoPrueba, boolean sePuede) {
        this.desdeTablero = desdeTablero;
        this.aTablero = aTablero;
        this.movimientoPrueba = movimientoPrueba;
        this.sePuede = sePuede;
    }

    public Movimiento getMovimientoPrueba() {
        return movimientoPrueba;
    }

    public Tablero getaTablero() {
        return aTablero;
    }

    public Tablero getDesdeTablero() {
        return desdeTablero;
    }

    public boolean isSePuede() {
        return sePuede;
    }
}
