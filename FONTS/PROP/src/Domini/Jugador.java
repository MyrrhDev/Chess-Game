package Domini;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Jugador {
    boolean enMate;
    boolean esNegro;
    boolean estaAtacando;
    public Rey esteRey;
    public ArrayList <Pieza> misPiezas;
    public ArrayList <Movimiento> posiblesMovimientos;
    public Tablero tablero;

    /* Pre: Cierto
     * Post: Crea un nuevo objeto Jugador con los parametros esMaquina, esNegro y estaAtacando
     * */
    public Jugador(boolean esNegro, boolean estaAtacando) {
        this.esNegro = esNegro;
        this.estaAtacando = estaAtacando;
    }

    /* Pre: Cierto
     * Post: Devuelve si el parametro implicito tiene algun Movimiento que se pueda hacer
     * */
    private boolean tieneEscape() {
        Tablero temp = new Tablero(this.tablero);
        return this.posiblesMovimientos.stream().anyMatch(movimiento -> hacerMovimiento(temp, movimiento).isSePuede());
    }

    /* Pre: Cierto
     * Post: Devuelve en un ArrayList si hay ataques al Rey del parametro implicito
     * */
    public static ArrayList<Movimiento> hayAtaquesPendientes(final int theX, final int theY, final ArrayList <Movimiento> movimientos) {
        return (ArrayList<Movimiento>) movimientos.stream().filter(movi -> movi.getToX() == theX && movi.getToY() == theY).collect(Collectors.toList());
    }

    //Copied from Refactoring:
    /* Pre: Tablero y movimiento existen y no esta vacios
     * Post: Devuelve en un objeto de MovimientoPrueba que indica si ha sido posible hacer el Movimiento
     * */
    public Movimiento hacerMovimiento(final Tablero tablero, final Movimiento movimiento) {
        Tablero tempTablero = movimiento.intentar(tablero);
        ArrayList <Movimiento> ataquesAlRey = Jugador.hayAtaquesPendientes(tempTablero.miOponenteEs(tempTablero.esSuTurno()).getReydelJugador().getPosX(),
                tempTablero.miOponenteEs(tempTablero.esSuTurno()).getReydelJugador().getPosY(), tempTablero.esSuTurno().getPosiblesMovimientos());
        if (!ataquesAlRey.isEmpty()) {
            Movimiento temp = new Movimiento(movimiento.getFromX(), movimiento.getFromY(), movimiento.getToX(), movimiento.getToY(), tablero, false);
            return temp;
        }

        Movimiento tempGood = new Movimiento(movimiento.getFromX(), movimiento.getFromY(), movimiento.getToX(), movimiento.getToY(), tempTablero, true);
        return tempGood; //todo bien
    }


    /* Pre: Cierto
     * Post: Devuelve el ArrayList de posibles Movimientos del parametro implicito posiblesMovimientos
     */
    public ArrayList<Movimiento> getPosiblesMovimientos() {
        return posiblesMovimientos;
    }

    /* Pre: Cierto
     * Post: Asigna al ArrayList de posibles Movimientos del parametro implicito posiblesMovimientos
     */
    public void setPosiblesMovimientos(ArrayList<Movimiento> posiblesMovimientos) {
        this.posiblesMovimientos = posiblesMovimientos;
    }

    /* Pre: Cierto
     * Post: Devuelve el boolean del parametro implicito que indica si el Jugador tiene las piezas negras
     */
    public boolean isEsNegro() {
        return esNegro;
    }

    /* Pre: Cierto
     * Post: Devuelve el ArrayList de Piezas del parametro implicito misPiezas
     */
    public ArrayList<Pieza> getMisPiezas() {
        return this.misPiezas;
    }

    /* Pre: Cierto
     * Post: Asigna al ArrayList de Piezas del parametro implicito misPiezas
     */
    public void setMisPiezas(ArrayList<Pieza> misPiezas) {
        this.misPiezas = misPiezas;
    }

    /* Pre: Cierto
     * Post: Devuelve el boolean del parametro implicito que indica si el Jugador esta en Mate
     */
    public boolean isEnMate() {
        return this.enMate;
    }

    /* Pre: Cierto
     * Post: Asigna al boolean del parametro implicito que indica si el Jugador esta en Mate
     */
    public void setEnMate(boolean enMate) {
        this.enMate = enMate;
    }

    /* Pre: Cierto
     * Post: Devuelve al boolean del parametro implicito que indica si el Jugador esta en Jaque Mate
     */
    public boolean isEnJaqueMate() {
        return this.enMate && !tieneEscape();
    }

    /* Pre: Cierto
     * Post: Devuelve al boolean del parametro implicito que indica si el Jugador puede hacer algun Movimiento
     */
    public boolean estaEstancado() {
        return !this.enMate && !tieneEscape();
    }

    /* Pre: Cierto
     * Post: Devuelve al boolean del parametro implicito que indica si el Jugador esta Atacando
     */
    public boolean isEstaAtacando() {
        return estaAtacando;
    }

    /* Pre: Cierto
     * Post: Asigna la pieza Rey al parametro implicito
     */
    public void setEsteRey(Rey esteRey) {
        this.esteRey = esteRey;
    }

    /* Pre: Cierto
     * Post: Devuelve la pieza Rey del parametro implicito
     */
    public Rey getReydelJugador() {
        return this.esteRey;
    }

    /* Pre: Cierto
     * Post: Devuelve el tablero del parametro implicito
     */
    public Tablero getTablero() {
        return tablero;
    }

    /* Pre: Cierto
     * Post: Asigna el tablero del parametro implicito
     */
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    /* Pre: Tablero existe y no esta vacio, N es el numero de movimientos que el Jugador puede hacer
     * Post: Devuelve un nuevo Tablero modificado
     * */
    public abstract Tablero jugar(Tablero t, int n) throws Exception;

    /* Pre: Tablero existe y no esta vacio, movimiento existe y no esta vacio con el movimiento que hace la Persona
     * Post: Devuelve un nuevo Tablero modificado
     * */
    public abstract Tablero jugar(Tablero t, Movimiento movimiento) throws Exception;
}