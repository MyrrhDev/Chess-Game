package Domini;

public class M2 extends Maquina {


    public M2(boolean esNegro, boolean estaAtacando) {
        super(esNegro, estaAtacando);
    }






    /* Pre: Tablero existe y no esta vacio, movimiento existe y no esta vacio con el movimiento que hace la Persona
     * Post: Devuelve un nuevo Tablero modificado
     * */
    @Override
    public Tablero jugar(Tablero t, Movimiento movimiento) throws Exception {
        return t;
    }
}
