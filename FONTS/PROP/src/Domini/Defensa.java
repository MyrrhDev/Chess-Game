package Domini;

import java.util.ArrayList;

public class Defensa extends Evaluacion {

    /* Pre: Cierto
     * Post: Devuelve un nuevo objeto Evaluacion
     */
    public Defensa() {
    }

    /* Pre: Cierto
     * Post: Devuelve un valor int de la puntuacion del nodo en cuestion
     */
    @Override
    public int evaluar(Tablero tablero, int depth) {
        //int jugPuntos = puntuacion(tablero, tablero.getAttackPlayer(true), depth);
        int jugPuntos2 = puntuacion(tablero, tablero.getAttackPlayer(false), depth);
        return  -jugPuntos2;
    }

    /* Pre: Cierto
     * Post: Devuelve un valor int de la suma de las evaluaciones del Jugador del parametro
     */
    private static int puntuacion(Tablero tablero, Jugador jugador, int depth) {
        return movimientoCercaDelRey(tablero,jugador) + nohayAtaquesAlRey(tablero,jugador);
    }

    private static int nohayAtaquesAlRey(Tablero tablero, Jugador jugador) {
        int posX = jugador.getReydelJugador().getPosX();
        int posY = jugador.getReydelJugador().getPosY();
        int points = 0;
        ArrayList<Movimiento> ataquesAlRey = jugador.hayAtaquesPendientes(posX,posY, tablero.miOponenteEs(jugador).getPosiblesMovimientos());
        if (ataquesAlRey.isEmpty()) {
            points += 500;
        }

        return points;
    }

    private static int movimientoCercaDelRey(Tablero tablero, Jugador jugador) {
        int posX = jugador.getReydelJugador().getPosX();
        int posY = jugador.getReydelJugador().getPosY();
        final char estadoTablero[][] = tablero.getTablero();

        int puntos = 0;

        int i = posX-1;
        int j= posY-1;

        for (int k = i; k <= posX+1; k++) {
            for (int l = j; l <= posY+1; l++) {
                if(estadoTablero[k][l] != '0') { //Si es una pieza mia alredor de mi Rey ++points
                    if(jugador.isEsNegro() && Character.isLowerCase(estadoTablero[k][l])) {
                        puntos += 100;
                        if(estadoTablero[k][l] == 'p') { //si es un peon, mejor
                            puntos += 200;
                        }
                    }

                }
            }
        }
        return puntos;
    }


}
