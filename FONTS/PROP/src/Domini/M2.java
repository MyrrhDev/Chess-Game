package Domini;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class M2 extends Maquina {
    private Evaluacion evaluacion = new Evaluacion();
    private int profundidadDada;
    private int quiescenceCount;


    public M2(boolean esNegro, boolean estaAtacando) {
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
        Movimiento m = estrategiaCompleja(tablero);
        if(m.esNada()) {
            Exception e = new Exception("No se puede mover, posible Jaque Mate");
            throw e;
        } else {
            t.ejecutarMovimiento(m);
        }
        return t;
    }

    public ArrayList<Movimiento> sort(ArrayList<Movimiento> movimientos) {
        ArrayList <Movimiento> movimientosSorted = new ArrayList<>();

//        Comparator<Parent> byAttr1ofFirstChild = Comparator.comparing(
//                parent -> parent.getChildren().get(0).getAttr1()
//        );
//
//      movimientoPoneEnMate(movimiento.tablero)
        //movimientosSorted = movimientos;

        Comparator<Movimiento> enJaquees = Comparator.comparing(movimiento -> tablero.movimientoHumano(movimiento));

        Comparator<Movimiento> esUnAtaque = Comparator.comparing(movimiento -> movimiento.isEsUnAtaque());
        Comparator<Movimiento> valorPieza = Comparator.comparingInt(movimiento -> movimiento.getPieza().getPts());

        movimientos.stream().sorted(enJaquees.thenComparing(esUnAtaque).thenComparing(valorPieza)).collect(Collectors.toList());

        return movimientos;
    }



    public Movimiento estrategiaCompleja(final Tablero tablero) {
        Movimiento mejorMov = new Movimiento(-1,-1, -1, -1);
        int mayorPuntos = Integer.MIN_VALUE;
        int menorPuntos = Integer.MAX_VALUE;
        int puntosAhora;
        for (final Movimiento movimiento : tablero.esSuTurno().getPosiblesMovimientos()) {
            final Movimiento pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
            this.quiescenceCount = 0;
            if (pruebaMovimiento.isSePuede()) {
                if(tablero.esSuTurno().isEstaAtacando()) {
                    puntosAhora = min(pruebaMovimiento.getaTablero(), this.profundidadDada - 1, mayorPuntos, menorPuntos);
                } else {
                    puntosAhora = max(pruebaMovimiento.getaTablero(), this.profundidadDada - 1, mayorPuntos, menorPuntos);
                }
                if (tablero.esSuTurno().isEstaAtacando() && puntosAhora > mayorPuntos) {
                    mayorPuntos = puntosAhora;
                    mejorMov = movimiento;
                }
                else if (!tablero.esSuTurno().isEstaAtacando() && puntosAhora < menorPuntos) {
                    menorPuntos = puntosAhora;
                    mejorMov = movimiento;
                }
            }
        }
        return mejorMov;
    }

    public int max(final Tablero tablero, final int profundidad, final int highest, final int lowest) {
        if (profundidad == 0 || gameOver(tablero)) {
            return this.evaluacion.evaluar(tablero, profundidad);
        }
        int mayorPuntos = highest;
        for (final Movimiento movimiento : sort(tablero.esSuTurno().getPosiblesMovimientos())) {
            final Movimiento pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
            if (pruebaMovimiento.isSePuede()) {
                mayorPuntos = Math.max(mayorPuntos, min(pruebaMovimiento.getaTablero(), calcularQuiescence(tablero, movimiento, profundidad), mayorPuntos, lowest));
                if (lowest <= mayorPuntos) {
                    break;
                }
            }
        }
        return mayorPuntos;
    }

    public int min(final Tablero tablero, final int profundidad, final int highest, final int lowest) {
        if (profundidad == 0 || gameOver(tablero)) {
            return this.evaluacion.evaluar(tablero, profundidad);
        }
        int menorPuntos = lowest;
        for (final Movimiento movimiento : sort((tablero.esSuTurno().getPosiblesMovimientos()))) {
            final Movimiento pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
            if (pruebaMovimiento.isSePuede()) {
                menorPuntos = Math.min(menorPuntos, max(pruebaMovimiento.getaTablero(), calcularQuiescence(tablero, movimiento, profundidad), highest, menorPuntos));
                if (menorPuntos <= highest) {
                    break;
                }
            }
        }
        return menorPuntos;
    }

    private int calcularQuiescence(final Tablero tablero, final Movimiento movimiento, final int profundidad) {
        return profundidad - 1;
    }

    /* Pre: Tablero existe y no esta vacio
     * Post: Devuelve true si el Jugador esta en JaqueMate o si no le he es posible hacer Movimiento alguno
     * */
    private static boolean gameOver(final Tablero tablero) {
        return tablero.esSuTurno().isEnJaqueMate() || tablero.esSuTurno().estaEstancado();
    }

    /* Pre: Tablero existe y no esta vacio, movimiento existe y no esta vacio con el movimiento que hace la Persona
     * Post: Devuelve un nuevo Tablero modificado
     * */
    @Override
    public Tablero jugar(Tablero t, Movimiento movimiento) throws Exception {
        return t;
    }
}
