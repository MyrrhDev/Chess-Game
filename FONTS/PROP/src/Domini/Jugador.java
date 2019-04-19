
package Domini;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Jugador {
    private boolean esMaquina;
    boolean enMate;
    boolean esNegro;
    boolean estaAtacando;
    public Rey esteRey;
    public ArrayList <Pieza> misPiezas; //para evaluacion puntos
    public ArrayList <Movimiento> posiblesMovimientos;
    public Tablero tablero;


    public Jugador(boolean esMaquina, boolean esNegro, boolean estaAtacando) {
        this.esMaquina = esMaquina;
        this.esNegro = esNegro;
        this.estaAtacando = estaAtacando;
    }

    public ArrayList<Movimiento> getPosiblesMovimientos() {
        return posiblesMovimientos;
    }

    public void setPosiblesMovimientos(ArrayList<Movimiento> posiblesMovimientos) {
        this.posiblesMovimientos = posiblesMovimientos;
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

    public void setMisPiezas(ArrayList<Pieza> misPiezas) {
        this.misPiezas = misPiezas;
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


    public void setEsteRey(Rey esteRey) {
        this.esteRey = esteRey;
    }

    public Rey getReydelJugador() {
        return this.esteRey;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    private boolean tieneEscape() {
        return this.posiblesMovimientos.stream().anyMatch(movimiento -> this.hacerMovimiento(this.tablero, movimiento).isSePuede());
    }

    static ArrayList<Movimiento> hayAtaquesPendientes(int theX, int theY, ArrayList <Movimiento> movimientos) {
        return (ArrayList<Movimiento>) movimientos.stream().filter(movi -> movi.toX == theX && movi.toY == theY).collect(Collectors.toList());
    }

    //trata de hacer un movimiento y se crea nu objeto MovimientoPrueba que por el bool nos dice si se pudo o no
    public MovimientoPrueba hacerMovimiento(Tablero tablero, Movimiento movimiento) {
        if (!this.posiblesMovimientos.contains(movimiento)) {
            return new MovimientoPrueba(tablero, tablero, movimiento, false); //Este es un movimiento ilegal
        }
        //TODO: Implementar esto en Movimiento
        //En este tablero temporal hemos movido y se ha cambiado de turno
        //turnoBlancas ha cambiado
        Tablero tempTablero = movimiento.intentar(tablero); //move.execute()

        //Existen posibles ataques a mi Rey? Posicion de mi Rey, busqueda en
        // el array de posibles movimientos de mi oponente
        ArrayList <Movimiento> ataquesAlRey = Jugador.hayAtaquesPendientes(tempTablero.miOponenteEs(tempTablero.esSuTurno()).getReydelJugador().getPosX(),
                tempTablero.miOponenteEs(tempTablero.esSuTurno()).getReydelJugador().getPosY(), tempTablero.esSuTurno().getPosiblesMovimientos());

        if (!ataquesAlRey.isEmpty()) {
            return new MovimientoPrueba(tablero, tablero, movimiento, false); //No me puedo mover ahi xq sino yo estoy en MATE
        }
        return new MovimientoPrueba(tablero, tempTablero, movimiento, true); //todo bien
    }


    public abstract Tablero jugar(Tablero t, int n) throws Exception;

    public abstract Tablero jugar(Tablero t, Movimiento movimiento) throws Exception;
}