package Domini;

public class Evaluacion {
    //Done for now

    public Evaluacion() {
    }

    public int evaluar(Tablero tablero, int depth) {
        return puntuacion(tablero.getJugador(false), depth) - puntuacion(tablero.getJugador(true), depth);
    }

    //Tiene que ser Jugador porque examinamos igual si es Persona o Maquina
    private static int puntuacion(Jugador jugador, int depth) {
        return evaluarPuntosPiezas(jugador);
    }

    private static int evaluarPuntosPiezas(Jugador jugador) {
        int puntosPiezas = 0;
        for (Pieza pieza : jugador.getMisPiezas()) {
            puntosPiezas += pieza.getPts();
        }
        return puntosPiezas;
    }

    //TODO Mejoras:
    //Puntos por posicion
    //Puntos por que quedan Alfiles
    //Puntos por numero total de piezas
    //por si el jugador llega antes a hacer MATE en la hoja
    //jaque mate serian mas puntos
    //hacer jaque mate antes de que depth se acabe, mas puntos

}