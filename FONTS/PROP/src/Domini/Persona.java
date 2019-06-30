package Domini;

public class Persona extends Jugador {

    /* Pre: Cierto
     * Post: Crea un nuevo objeto Maquina con los parametros esMaquina, esNegro y estaAtacando
     * */
    public Persona(boolean esNegro, boolean estaAtacando) {
        super(esNegro, estaAtacando);
    }

    /* Pre: Tablero existe y no esta vacio, movimiento existe y no esta vacio con el movimiento que hace la Persona
     * Post: Devuelve un nuevo Tablero modificado
     * */
    @Override
    public Tablero jugar(Tablero tablero, Movimiento movimiento) throws Exception {
        boolean sePuede = tablero.movimientoHumano(movimiento);
        if(!sePuede) throw new Exception("No se puede ejecutar el movimiento");
        return tablero;
    }

    /* Pre: Tablero existe y no esta vacio, N es el numero de movimientos que cada Jugador puede hacer
     * Post: Devuelve un nuevo Tablero modificado
     * */
    @Override
    public Tablero jugar(Tablero tablero, int N) throws Exception {
        return tablero;
    }
}
