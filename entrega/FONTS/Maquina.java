package Domini;


public class Maquina extends Jugador {
    EstrategiaSimple estrategia;

    /* Pre: Cierto
     * Post: Crea un nuevo objeto Maquina con los parametros esMaquina, esNegro y estaAtacando
     * */
    public Maquina(boolean esMaquina, boolean esNegro, boolean estaAtacando) {
        super(esMaquina, esNegro, estaAtacando);
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
        this.estrategia = new EstrategiaSimple(level*2);
        Movimiento m = this.estrategia.estrategiaSimple(tablero);
        if(m.esNada()) {
            Exception e = new Exception("No se puede mover, posible Jaque Mate");
            throw e;
        } else {
            t.ejecutarMovimiento(m);
        }
        return t;
    }

    /* Pre: Tablero existe y no esta vacio, movimiento existe y no esta vacio con el movimiento que hace la Persona
     * Post: Devuelve un nuevo Tablero modificado
     * */
    @Override
    public Tablero jugar(Tablero t, Movimiento movimiento) throws Exception {
        return t;
    }
}