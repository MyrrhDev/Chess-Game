package Domini.DriversPrimeraEntrega;

import Domini.Movimiento;
import Domini.Tablero;

public final class MovimientoPrueba {

    private final Tablero desdeTablero;
    private final Tablero aTablero;
    private final Movimiento movimientoPrueba;
    private final boolean sePuede;

    /* Pre: Cierto
     * Post: Crea un nuevo objeto MovimientoPrueba con los parametros desdeTablero, aTablero, el movimiento y sePuede
     * */
    public MovimientoPrueba(Tablero desdeTablero,Tablero aTablero, Movimiento movimientoPrueba, boolean sePuede) {
        this.desdeTablero = desdeTablero;
        this.aTablero = aTablero;
        this.movimientoPrueba = movimientoPrueba;
        this.sePuede = sePuede;
    }

    /* Pre: Cierto
     * Post: Devuelve el Movimiento del parametro implicito
     */
    public Movimiento getMovimientoPrueba() {
        return movimientoPrueba;
    }

    /* Pre: Cierto
     * Post: Devuelve el Tablero al que el movimiento hecho nos lleva del parametro implicito
     */
    public Tablero getaTablero() {
        return aTablero;
    }

    /* Pre: Cierto
     * Post: Devuelve el Tablero desde el cual hemos hecho el movimiento del parametro implicito
     */
    public Tablero getDesdeTablero() {
        return desdeTablero;
    }

    /* Pre: Cierto
     * Post: Devuelve el boolean si el Movimiento del parametro implicito dado se puede hacer
     */
    public boolean isSePuede() {
        return sePuede;
    }
}