package Domini;

public class Evaluacion {
    //Done for now

    public Evaluacion() {
    }

    public int evaluar(Tablero tablero, int depth) {
        //int jugPuntos = puntuacion(tablero.getJugador(false), depth);
        //int jugPuntos2 = puntuacion(tablero.getJugador(true), depth);

        int jugPuntos = puntuacion(tablero.getAttackPlayer(true), depth);
        int jugPuntos2 = puntuacion(tablero.getAttackPlayer(false), depth);

        //return puntuacion(tablero.getJugador(false), depth) - puntuacion(tablero.getJugador(true), depth);
        return jugPuntos - jugPuntos2;
    }

    //Tiene que ser Jugador porque examinamos igual si es Persona o Maquina
    private static int puntuacion(Jugador jugador, int depth) {
        return evaluarPuntosPiezas(jugador) + elOtroJugadorEnJaque(jugador);
    }

    private static int evaluarPuntosPiezas(Jugador jugador) {
        int puntosPiezas = 0;
        for (Pieza pieza : jugador.getMisPiezas()) {
            puntosPiezas += pieza.getPts();
        }
        return puntosPiezas;
    }


    private static int elOtroJugadorEnJaque(Jugador jugador) {
        int i = 0;
        if(jugador.getTablero().miOponenteEs(jugador).isEnMate()) {
            i = 100000;
        }
        return i;
    }

    //TODO Mejoras:
    //Puntos por posicion
    //puntos por numero de movimientos posibles que tiene el jugador +
    //Puntos por que quedan Alfiles
    //Puntos por numero total de piezas
    //por si el jugador llega a hacer MATE en la hoja
    //jaque mate serian mas puntos +
    //hacer jaque mate antes de que depth se acabe, mas puntos +

}