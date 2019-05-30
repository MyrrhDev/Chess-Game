package Domini;

public class M1 extends Maquina {
    //private Evaluacion evaluacion = new Evaluacion();
    private Evaluacion evaluacionAtaque = new Ataque();
    private Evaluacion evaluacionDefensa = new Defensa();
    private int profundidadDada;

    public M1(boolean esNegro, boolean estaAtacando) {
        super(esNegro, estaAtacando);
    }


    /* Pre: Tablero existe y no esta vacio, N es el numero de movimientos que el Jugador puede hacer
     * Post: Devuelve un nuevo Tablero modificado
     * */
    @Override
    public Tablero jugar(final Tablero tablero, final int N) throws Exception {
        Tablero t = new Tablero(tablero);
        int level;
        if(N > 4) {
            level = 5;
        } else {
            level = N;
        }
        this.profundidadDada = level*2;
        Movimiento m = estrategiaSimple(tablero);
        if(m.esNada()) {
            Exception e = new Exception("No se puede mover, posible Jaque Mate");
            throw e;
        } else {
            t.ejecutarMovimiento(m);
        }
        return t;
    }


    /* Pre: Tablero existe y no esta vacio
     * Post: Devuelve el mejor movimiento del Jugador
     * */
    public Movimiento estrategiaSimple(final Tablero tablero) {
        Movimiento mejorMov = new Movimiento(null, -1,-1, -1, -1,null);
        int mayorPuntos = Integer.MIN_VALUE;
        int menorPuntos = Integer.MAX_VALUE;
        int puntosAhora;
        for (final Movimiento movimiento : tablero.esSuTurno().getPosiblesMovimientos()) {
            final Movimiento pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
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
            return this.evaluacionDefensa.evaluar(tablero, depth);
        }
        if(gameOver(tablero)) {
            return this.evaluacionDefensa.evaluar(tablero, depth);
        }
        int menorPuntos = Integer.MAX_VALUE;
        for (final Movimiento movimiento : tablero.esSuTurno().getPosiblesMovimientos()) {
            final Movimiento pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
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
            return this.evaluacionAtaque.evaluar(tablero, depth);
        }
        if (gameOver(tablero)) {
            return this.evaluacionAtaque.evaluar(tablero, depth);
        }
        int mayorPuntos = Integer.MIN_VALUE;
        for (final Movimiento movimiento : tablero.esSuTurno().getPosiblesMovimientos()) {
            final Movimiento pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
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
