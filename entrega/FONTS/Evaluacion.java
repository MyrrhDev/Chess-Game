package Domini;

public class Evaluacion {

    /* Pre: Cierto
     * Post: Devuelve un nuevo objeto Evaluacion
     */
    public Evaluacion() {
    }

    /* Pre: Cierto
     * Post: Devuelve un valor int de la puntuacion del nodo en cuestion
     */
    public int evaluar(Tablero tablero, int depth) {
        int jugPuntos = puntuacion(tablero.getAttackPlayer(true), depth);
        int jugPuntos2 = puntuacion(tablero.getAttackPlayer(false), depth);
        return jugPuntos - jugPuntos2;
    }

    /* Pre: Cierto
     * Post: Devuelve un valor int de la suma de las evaluaciones del Jugador del parametro
     */
    private static int puntuacion(Jugador jugador, int depth) {
        return evaluarPuntosPiezas(jugador) + elOtroJugadorEnJaque(jugador);
    }

    /* Pre: Cierto
     * Post: Devuelve un valor int de la suma de las piezas del Jugador del parametro
     */
    private static int evaluarPuntosPiezas(Jugador jugador) {
        int puntosPiezas = 0;
        for (Pieza pieza : jugador.getMisPiezas()) {
            puntosPiezas += pieza.getPts();
        }
        return puntosPiezas;
    }

    /* Pre: Cierto
     * Post: Devuelve un valor distinto de 0 si el oponente del Jugador del parametro esta en Mate
     */
    private static int elOtroJugadorEnJaque(Jugador jugador) {
        int i = 0;
        if(jugador.getTablero().miOponenteEs(jugador).isEnMate()) {
            i = 100000;
        }
        return i;
    }
}