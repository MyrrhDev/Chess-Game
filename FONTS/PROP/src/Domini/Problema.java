package Domini;

public class Problema {
    public String problema;
    int N;
    public boolean iniJuegoBlancas;
    Evaluacion evaluacion = new Evaluacion();
    private boolean verificado;
    private double dificultad;
    private int tiempo;

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Problema() {
    }


    private void calcularDificultad() {
        double diff = 0.0;
        if(problema != null) {
            //numero de piezas
            for(char c : problema.toCharArray()) {
                if(Character.isAlphabetic(c)) {
                    ++diff;
                }
            }
            diff *= N;
            dificultad = diff;
        }
    }

    /*
    Devolvera "Facil" si 2 <= dificultad < 10
    Devolvera "Medio" si 10 <= dificultad < 21
    Devolvera "Dificil" si 21 <= dificultad
     */
    public String getDificultad() {
        String ret = "";
        if(dificultad >= 2.0 && dificultad < 10.0) {
            ret = "Facil";
        }
        else if(dificultad >= 10.0 && dificultad < 21.0) {
            ret = "Medio";
        }
        else if(dificultad >= 21.0) {
            ret = "Dificil";
        }
        return ret;
    }

    /*
     Pre: Cierto
     Post: el atributo problema pasa a ser el FEN que se le pasa por parámetro, pero tratado de forma
           que se eliminan los elementos inútiles de la codificacion
     */
    public void setFEN(String FEN) {
        int i = -1;
        if(FEN.indexOf('-') != -1) {
            i = FEN.indexOf('w');
            if (i == -1) {
                i = FEN.indexOf('b');
            }
        }
        if(i != -1) {
            if(FEN.charAt(i) == 'w') iniJuegoBlancas = true;
            else iniJuegoBlancas = false;
            //ejemplo: 1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1
            int newLength = FEN.indexOf(' ');
            this.problema = FEN.substring(0, newLength);
        }
        else {
            this.problema = FEN;
            iniJuegoBlancas = true;
        }
    }

    public String getFEN() {
        return this.problema;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
        calcularDificultad();
    }

    public boolean getIniJuegoBlancas() {
        return this.iniJuegoBlancas;
    }

    /*
        Pre: La clase ya tiene los atributos de problema y N asignados
        Post: Se valida el problema introducido. En concreto: se busca en el minimax que haya una hoja del árbol con
        un jaque mate al color de las piezas contrarias al atributo iniJuegoBlancas
     */
    public boolean verificarProblema() {
        String verificar = "";
        if(problema != null)  {
            verificar = this.problema;
            verificado = false;
            boolean reyNegro = false, reyBlanco = false;
            for(int i = 0; i < problema.length(); ++i) {
                if(problema.charAt(i) == 'k') reyNegro = true;
                else if(problema.charAt(i) == 'K') reyBlanco = true;
            }
            if(reyBlanco && reyNegro) {
            /*
            Si w:
            Atacante: blancas, defensor: Negras
            Si b:
            Atacante: negras, defensor: Blancas
             */
                Maquina atacante = null, defensor = null;
                if (this.iniJuegoBlancas) {
                    atacante = new M1(false, true);
                    defensor = new M1(true, false);

                } else if (!this.iniJuegoBlancas) {
                    atacante = new M1(true, true);
                    defensor = new M1(false, false);

                }
                Tablero t = new Tablero(atacante, defensor);
                t.FENToTablero(verificar, iniJuegoBlancas);
                verificar(t);
                return verificado;
            }
        }
        return false;
    }

    /* Pre: Tablero existe y no esta vacio
     * Post: Calcula si el problema se puede verificar
     * */
    private void verificar(Tablero tablero) {
        int puntosAhora;
        for (final Movimiento movimiento : tablero.esSuTurno().getPosiblesMovimientos()) {
            Movimiento pruebaMovimiento = tablero.esSuTurno().hacerMovimiento(tablero,movimiento);
            if (pruebaMovimiento.isSePuede()) {
                if (tablero.esSuTurno().isEstaAtacando()) {
                    puntosAhora = min(pruebaMovimiento.getaTablero(), (this.N * 2) - 1);
                } else {
                    puntosAhora = max(pruebaMovimiento.getaTablero(), (this.N * 2) - 1);
                }
            }
        }
    }

    /* Pre: Tablero existe y no esta vacio, depth
     * Post: Devuelve la menor puntuacion posible de un movimiento dentro del tablero
     * */
    private int min(Tablero tablero, int depth) {
        if (depth == 0) {
        }
        if(gameOver(tablero)) {
            verificado = true;
            return this.evaluacion.evaluar(tablero, depth);
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
    private int max(Tablero tablero, int depth) {
        if (depth == 0) {
            return this.evaluacion.evaluar(tablero, depth);
        }
        if (gameOver(tablero)) {
            return this.evaluacion.evaluar(tablero, depth);
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

    boolean getVerificado() {
        return this.verificado;
    }
}