package Domini;

public abstract class Maquina extends Jugador {

    /* Pre: Cierto
     * Post: Crea un nuevo objeto Maquina con los parametros esMaquina, esNegro y estaAtacando
     * */
    public Maquina(boolean esNegro, boolean estaAtacando) {
        super(esNegro, estaAtacando);
    }

    /* Pre: Tablero existe y no esta vacio, N es el numero de movimientos que el Jugador puede hacer
     * Post: Devuelve un nuevo Tablero modificado
     * */
    @Override
    public Tablero jugar(final Tablero tablero, final int N) throws Exception {
        return tablero;
    }


    /* Pre: Tablero existe y no esta vacio, movimiento existe y no esta vacio con el movimiento que hace la Persona
     * Post: Devuelve un nuevo Tablero modificado
     * */
    @Override
    public Tablero jugar(Tablero t, Movimiento movimiento) throws Exception {
        return t;
    }
}
