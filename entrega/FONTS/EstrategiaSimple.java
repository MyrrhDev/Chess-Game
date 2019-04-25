package Domini;

public final class EstrategiaSimple {

    private Evaluacion evaluacion = new Evaluacion();
    private final int profundidadDada;

    /* Pre: el parametro profundidad existe
     * Post: Crea una nueva instancia de EstrategiaSimple con una profundidadDada de profundidad
     * */
    public EstrategiaSimple (final int profundidad) {
        this.profundidadDada = profundidad;
    }

    /* Pre: Tablero existe y no esta vacio
     * Post: Devuelve el mejor movimiento del Jugador
     * */
    public Movimiento estrategiaSimple(final Tablero tablero) {
        Movimiento mejorMov = new Movimiento(-1,-1, -1, -1);
        int mayorPuntos = Integer.MIN_VALUE;
        int menorPuntos = Integer.MAX_VALUE;
        int puntosAhora;
        for (final Movimiento movimiento : tablero.esSuTurno().getPosiblesMovimientos()) {
            final MovimientoPrueba pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
            if (pruebaMovimiento.isSePuede()) {
                if(tablero.esSuTurno().isEstaAtacando()) {
                    puntosAhora = min(pruebaMovimiento.getaTablero(), this.profundidadDada - 1);
                } else {
                    puntosAhora = max(pruebaMovimiento.getaTablero(), this.profundidadDada - 1);
                }
                if (tablero.esSuTurno().isEstaAtacando() && puntosAhora >= mayorPuntos) {
                    mayorPuntos = puntosAhora;
                    mejorMov = movimiento;
                } else if (!tablero.esSuTurno().isEstaAtacando() && puntosAhora <= menorPuntos) {
                    menorPuntos = puntosAhora;
                    mejorMov = movimiento;
                }
            } else {
            }
        }
        return mejorMov;
    }

    /* Pre: Tablero existe y no esta vacio, depth
     * Post: Devuelve la menor puntuacion posible de un movimiento dentro del tablero
     * */
    private int min(final Tablero tablero, final int depth) {
        if (depth == 0) {
            return this.evaluacion.evaluar(tablero, depth);
        }
        if(gameOver(tablero)) {
            return this.evaluacion.evaluar(tablero, depth);
        }
        int menorPuntos = Integer.MAX_VALUE;
        for (final Movimiento movimiento : tablero.esSuTurno().getPosiblesMovimientos()) {
            final MovimientoPrueba pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
            if (pruebaMovimiento.isSePuede()) {
                final int puntosAhora = max(pruebaMovimiento.getaTablero(), depth - 1);
                if (puntosAhora <= menorPuntos) {
                    menorPuntos = puntosAhora;
                }
            }
        }
        return menorPuntos;
    }

    /* Pre: Tablero existe y no esta vacio
     * Post: Devuelve la mayor puntuacion posible de un movimiento dentro del tablero
     * */
    private int max(final Tablero tablero, final int depth) {
        if (depth == 0) {
            return this.evaluacion.evaluar(tablero, depth);
        }
        if (gameOver(tablero)) {
            return this.evaluacion.evaluar(tablero, depth);
        }
        int mayorPuntos = Integer.MIN_VALUE;
        for (final Movimiento movimiento : tablero.esSuTurno().getPosiblesMovimientos()) {
            final MovimientoPrueba pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
            if (pruebaMovimiento.isSePuede()) {
                final int puntosAhora = min(pruebaMovimiento.getaTablero(), depth - 1);
                if (puntosAhora >= mayorPuntos) {
                    mayorPuntos = puntosAhora;
                }
            }
        }
        return mayorPuntos;
    }

    /* Pre: Tablero existe y no esta vacio
     * Post: Devuelve true si el Jugador esta en JaqueMate o si no le he es posible hacer Movimiento alguno
     * */
    private static boolean gameOver(final Tablero tablero) {
        return tablero.esSuTurno().isEnJaqueMate() || tablero.esSuTurno().estaEstancado();
    }
}