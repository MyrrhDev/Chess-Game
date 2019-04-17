package Domini;

import java.util.ArrayList;

public abstract class Jugador {
    //private int id;
    private boolean esMaquina;
    boolean enMate;
    //Aunque juegues dos humanos, esto me ayuda a coger las piezas indicadas
    //y ver si realmente se puede hacer un movimiento o si se esta en jaque
    boolean esNegro;
    boolean estaAtacando;

    //Rey esteRey;
    ArrayList <Pieza> misPiezas; //para evaluacion puntos
    ArrayList <Movimiento> posiblesMovimientos;

    //Si no ponemos un default constructor no deja compilar
    public Jugador() {

    }

    public Jugador(boolean esMaquina, boolean esNegro) {
        this.esMaquina = esMaquina;
        this.esNegro = esNegro;
    }

    public ArrayList<Movimiento> getPosiblesMovimientos() {
        return posiblesMovimientos;
    }


    public boolean isEsMaquina() {
        return esMaquina;
    }

    public void setEsMaquina(boolean esMaquina) {
        this.esMaquina = esMaquina;
    }

    public boolean isEsNegro() {
        return esNegro;
    }

    public ArrayList<Pieza> getMisPiezas() {
        return this.misPiezas;
    }

    public boolean isEnMate() {
        return this.enMate;
    }

    public boolean isEnJaqueMate() {
        return this.enMate && !tieneEscape();
    }

    public boolean estaEstancado() {
        return !this.enMate && !tieneEscape();
    }

    public boolean isEstaAtacando() {
        return estaAtacando;
    }

    //TODO: Yo.
    /*public miOponenteEs() {
        //returns un jugador
    }*/

    /*public Rey getReydelJugador() {
        return this.playerKing;
    }*/

    private boolean tieneEscape() {
        //return this.posiblesMovimientos.stream().anyMatch(movimiento -> hacerMovimiento(movimiento).isSePuede());
        return false;
    }

    static ArrayList<Movimiento> hayAtaquesPendientes(int theX, int theY, ArrayList <Movimiento> movimientos) {
        /*return movimientos.stream().filter(movimientos -> movimientos.toX == theX && movimientos.toY == theY)
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));*/
        ArrayList<Movimiento> r = new ArrayList<>();
        return r;
    }

    //trata de hacer un movimiento y se crea nu objeto MovimientoPrueba que por el bool nos dice si se pudo o no
    public MovimientoPrueba hacerMovimiento(Tablero tablero, Movimiento movimiento) {
        /*if (!this.posiblesMovimientos.contains(movimiento)) {
            return new MovimientoPrueba(tablero, tablero, movimiento, false); //Este es un movimiento ilegal
        }
        //TODO: Implementar esto en Movimiento
        //En este tablero temporal hemos movido y se ha cambiado de turno
        //siguienteMovimiento ha cambiado
        Tablero tempTablero = movimiento.intentar(tablero); //move.execute()

        //Existen posibles ataques a mi Rey? Posicion de mi Rey, busqueda en
        // el array de posibles movimientos de mi oponente
        ArraList <Movimiento> ataquesAlRey = Jugador.hayAtaquesPendientes(tempTablero.esSuTurno().miOponenteEs().getReydelJugador().getPosX(),
                tempTablero.esSuTurno().miOponenteEs().getReydelJugador().getPosY(), tempTablero.esSuTurno().posiblesMovimientos());

        if (!ataquesAlRey.isEmpty()) {
            return new MovimientoPrueba(tablero, tablero, movimiento, false); //No me puedo mover ahi xq sino yo estoy en MATE
        }
        return new MovimientoPrueba(tablero, tempTablero, movimiento, true); //todo bien*/
        MovimientoPrueba m = new MovimientoPrueba();
        return m;
    }
}
