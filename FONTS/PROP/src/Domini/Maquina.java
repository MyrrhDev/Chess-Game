package Domini;


public class Maquina extends Jugador {
    //boolean esSimple;
    //boolean estaAtacando;
    //boolean esNegro;
    EstrategiaSimple estrategia;

    //Cambiado por Roger 21-04-2019 para solucionar problemas en verificarProblema()
    public Maquina(boolean esMaquina, boolean esNegro, boolean estaAtacando) {
        //super(esMaquina, esNegro, estaAtacando);
        this.esMaquina = esMaquina;
        this.estaAtacando = estaAtacando;
        this.esNegro = esNegro;
    }


    //EstrategiaCompleja estrategiaCompl;


    //int N es el numero de movimientos dados en el que se "resuelve" el problema para uno de los Jugadores
    //TODO: N se tiene que reducir en el controlador dominio
    @Override
    public Tablero jugar(final Tablero tablero, final int N) throws Exception {
        Tablero t = new Tablero(tablero);

        this.estrategia = new EstrategiaSimple(N*2);
        //if(esSimple) { //Considera N*2 xq con cada min/max se reduce uno y N es por jugador
        Movimiento m = this.estrategia.estrategiaSimple(tablero,this.isEstaAtacando()); //No se si le pasa esto Jugador -> Maquina -> aqui
        //} else Movimiento m = estrategiaCompl.estrategiaCompleja();
        Exception e = new Exception("No se puede mover, posible Jaque Mate");
        if(m.esNada()) {
            throw e;
        } else {
            t.ejecutarMovimiento(m);
        }


        return t;
    }

    //private abstract Movimiento estrategiaSimple(Tablero tablero, int depth, boolean maxOfensivePlayer);
    @Override
    public Tablero jugar(Tablero t, Movimiento movimiento) throws Exception {
        return t;
    }


}