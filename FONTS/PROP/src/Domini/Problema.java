package Domini;

public class Problema {
    public String problema;
    int N;
    public boolean iniJuegoBlancas; // true -> empiezan blancas, false -> empiezan negras
    Evaluacion evaluacion = new Evaluacion();
    private boolean verificado;

    //constructoras
    public Problema() {
    }

    /*
     Pre: Cierto
     Post: el atributo problema pasa a ser el FEN que se le pasa por parámetro, pero tratado de forma
           que se eliminan los elementos inútiles de la codificacion
     */
    public void setFEN(String FEN) {
        //ejemplo: 1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1
        int newLength = FEN.indexOf(' ');
        this.problema = FEN.substring(0, newLength);
        System.out.println(problema.length() + " " + problema);
    }

    public void setIniJuegoBlancas(String FEN) {
        int i = FEN.indexOf('w');
        if(i == -1) { //no existe el carácter w, verificamos que efectivamente empiezan las negras atacando
            i = FEN.indexOf('b');
        }
        if(FEN.charAt(i) == 'w') iniJuegoBlancas = true;
        else iniJuegoBlancas = false;
    }

    public String getFEN() {
        return this.problema;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public boolean getIniJuegoBlancas() {
        return this.iniJuegoBlancas;
    }

    //FEN ya debe estar tratado
    public boolean verificarProblema() {
        String verificar = "";
        if(problema != null)  {
            verificar = this.problema;
            verificado = false;
            Maquina atacante = new Maquina(true, iniJuegoBlancas, true);
            Maquina defensora = new Maquina(true, !iniJuegoBlancas, false);
            Tablero t = new Tablero(atacante, defensora);
            t.FENToTablero(verificar, iniJuegoBlancas);
            verificar(t, true);
            return verificado;
        }
        //1. Nos va a entrar un FEN, queremos el tablero. Llamamos al tablero construido por el FEN
        //2. Verificar problema significa que haya mate en n o menos movimientos del jugador que ataca
        //3. estrategiaSimple(nuevo tablero creado, true);
        return false;
    }

    private void verificar(Tablero tablero, boolean maxAttackingPlayer) {
        int puntosAhora;

        //Para todos las piezas+movimientos posibles
        for (final Movimiento movimiento : tablero.getAttackPlayer(maxAttackingPlayer).getPosiblesMovimientos()) {

            //Probamos de hacer el movimiento en un tablero nuevo creado en la clase de Movimiento Prueba
            MovimientoPrueba pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);

            if (pruebaMovimiento.isSePuede()) {
                puntosAhora = min(pruebaMovimiento.getaTablero(), (this.N*2) - 1);
            }
        }
    }


    private int min(Tablero tablero, int depth) {
        if (depth == 0) {
            //return this.evaluacion.evaluar(tablero, depth);
        }
        if(gameOver(tablero)) {
            verificado = true;
            return this.evaluacion.evaluar(tablero, depth);
            //aqui en una var booleana de la classe canviar-la a true
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