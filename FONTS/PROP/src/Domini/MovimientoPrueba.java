package Domini;

public final class MovimientoPrueba {

    private final Tablero desdeTablero;
    private final Tablero aTablero;
    private final Movimiento movimientoPrueba;
    private final boolean sePuede;


    public MovimientoPrueba(Tablero desdeTablero,Tablero aTablero, Movimiento movimientoPrueba, boolean sePuede) {
        this.desdeTablero =  desdeTablero;
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
