package Domini;

public final class EstrategiaSimple {

    //Estrategia Simple NO extiende Maquina...
    //Done for now...

    private Evaluacion evaluacion;
    private final int profundidadDada;

    public EstrategiaSimple (final int profundidad) {
        this.profundidadDada = profundidad;
    }
    //Minimax M1

    //need:
    //
    // Tiene que ahber un forma de coger mis piezas: Tablero.getAllMoves(player)
    //Tiene que haber una forma de coger los movimientos de mis piezas

    //Esta version de minimax me devuelve dependiendo de quien la llame el mejor movimiento a hacer si ataca o defiende
    //He borrado int depth porque ahora es parte de la clase misma en profundidadDada
    public Movimiento estrategiaSimple(Tablero tablero, boolean maxAttackingPlayer) {
        Movimiento mejorMov = new Movimiento(-1,-1, -1, -1);
        int mayorPuntos = Integer.MIN_VALUE;
        int menorPuntos = Integer.MAX_VALUE;
        int puntosAhora;

        //Para todos las piezas+movimientos posibles
        for (final Movimiento movimiento : tablero.getAttackPlayer(maxAttackingPlayer).getPosiblesMovimientos()) {

            //Probamos de hacer el movimiento en un tablero nuevo creado en la clase de Movimiento Prueba
            MovimientoPrueba pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);

            if (pruebaMovimiento.isSePuede()) {
                if(!tablero.esSuTurno().isEsNegro()) { //this porque estamos dentro de Maquina (?)
                    puntosAhora = min(pruebaMovimiento.getaTablero(), this.profundidadDada - 1);
                } else {
                    puntosAhora = max(pruebaMovimiento.getaTablero(), this.profundidadDada - 1);
                }

                if (!tablero.esSuTurno().isEsNegro() && puntosAhora >= mayorPuntos) {
                    mayorPuntos = puntosAhora;
                    mejorMov = movimiento;
                } else if (tablero.esSuTurno().isEsNegro() && puntosAhora <= menorPuntos) {
                    menorPuntos = puntosAhora;
                    mejorMov = movimiento;
                }
            } else { //estoy en MATE o JAQUE MATE


            }
        }
        return mejorMov;
    }


    private int min(Tablero tablero, int depth) {
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


    private int max(Tablero tablero, int depth) {
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


    private static boolean gameOver(final Tablero tablero) {
        return tablero.esSuTurno().isEnJaqueMate() || tablero.esSuTurno().estaEstancado();
    }

}