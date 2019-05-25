package Domini;
import java.util.ArrayList;

public class Tablero {

    public char tablero[][] = new char[8][8];
    private ArrayList<Pieza> PiezasBlancas;
    private ArrayList<Pieza> PiezasNegras;
    public boolean turnoBlancas;
    private final Jugador jugador1;
    private final Jugador jugador2;


    /* Pre: Cierto
     * Post: Se crea un nuevo objeto Tablero con un Tablero pasado por parametro.
     */
    public Tablero(final Tablero iniTablero) {
        if(iniTablero.jugador1 instanceof M1) {
            this.jugador1 = new M1(iniTablero.jugador1.esNegro, iniTablero.jugador1.estaAtacando);
        } else if(iniTablero.jugador1 instanceof M2) {
            this.jugador1 = new M2(iniTablero.jugador1.esNegro, iniTablero.jugador1.estaAtacando);
        } else {
            this.jugador1 = new Persona(iniTablero.jugador1.esNegro, iniTablero.jugador1.estaAtacando);
        }

        if(iniTablero.jugador2 instanceof M1) {
            this.jugador2 = new M1(iniTablero.jugador2.esNegro, iniTablero.jugador2.estaAtacando);
        } else if(iniTablero.jugador2 instanceof M2) {
            this.jugador2 = new M2(iniTablero.jugador2.esNegro, iniTablero.jugador2.estaAtacando);
        } else {
            this.jugador2 = new Persona(iniTablero.jugador2.esNegro, iniTablero.jugador2.estaAtacando);
        }
        this.PiezasBlancas = new ArrayList<>();
        this.PiezasNegras = new ArrayList<>();
        char newtablero[][] = new char[8][8];
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                newtablero[i][j] = iniTablero.tablero[i][j];
                if(iniTablero.tablero[i][j] != '0') {
                    if (Character.isLowerCase(iniTablero.tablero[i][j])) {
                        Pieza p;
                        p = getPiezadeChar(iniTablero.tablero[i][j], true, i, j);
                        this.PiezasNegras.add(p);
                    } else {
                        Pieza o;
                        o = getPiezadeChar(iniTablero.tablero[i][j], false, i, j);
                        this.PiezasBlancas.add(o);
                    }
                }
            }
        }
        this.tablero = newtablero;
        this.turnoBlancas = iniTablero.turnoBlancas;
    }

    /* Pre: Cierto
     * Post: Se crea un nuevo objeto Tablero con nuevos objetos Jugadores pasados por parametros
     */
    public Tablero(final Jugador jugador1, final Jugador jugador2) {
        if(jugador1 instanceof M1) {
            this.jugador1 = new M1(jugador1.esNegro, jugador1.estaAtacando);
        } else if(jugador1 instanceof M2) {
            this.jugador1 = new M2(jugador1.esNegro, jugador1.estaAtacando);
        } else {
            this.jugador1 = new Persona(jugador1.esNegro, jugador1.estaAtacando);
        }
        if(jugador2 instanceof M1) {
            this.jugador2 = new M1(jugador2.esNegro, jugador2.estaAtacando);
        } else if(jugador2 instanceof M2) {
            this.jugador2 = new M2(jugador2.esNegro, jugador2.estaAtacando);
        } else {
            this.jugador2 = new Persona(jugador2.esNegro, jugador2.estaAtacando);
        }
    }


    /* Pre: El parametro implicito existe
     * Post: Despues de editar un problema en el Tablero, lo transformamos en un String
     */
    public String TableroToFEN() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int spaces = 0;
            sb.append('/');
            for (int j = 0; j < 8; j++) {
                if(this.tablero[i][j] == '0') ++spaces;
                else if(this.tablero[i][j] != '0') {
                    if(spaces != 0) {
                        sb.append((char)(spaces+'0'));
                        spaces = 0;
                    }
                    sb.append(tablero[i][j]);
                }
            }
        }
        //Esto no da: la w o b del final, tampoco el "- -0 1", lo necesitamos?
        String fen = sb.toString();
        return fen;
    }



    /* Pre: El parametro implicito ha sido creado con Jugadores inicializados
     * Post: Las piezas del Problema en formato FEN han sido puestas en el atributo tablero[][]
     */
    public void FENToTablero(String FEN, boolean turnoBlancas) {
        this.turnoBlancas = turnoBlancas;
        ArrayList<Pieza> blancas = new ArrayList<>();
        ArrayList<Pieza> negras = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (int i = 0; i <= FEN.length()-1; i++) {
            if (Character.isDigit(FEN.charAt(i))) {
                int spaces = Character.getNumericValue(FEN.charAt(i));
                int j = 0;
                while(j < spaces) {
                    this.tablero[x][y] = '0';
                    ++j;
                    ++y;
                }
            } else if (Character.isLowerCase(FEN.charAt(i))) {
                this.tablero[x][y] = FEN.charAt(i);
                Pieza p;
                p = getPiezadeChar(FEN.charAt(i), true, x, y);
                negras.add(p);
                ++y;
            } else if (Character.isUpperCase(FEN.charAt(i))) {
                this.tablero[x][y] = FEN.charAt(i);
                Pieza p;
                p = getPiezadeChar(FEN.charAt(i), false, x, y);
                blancas.add(p);
                ++y;
            } else if (FEN.charAt(i) == '/') {
                ++x;
                y = 0;
            }
        }
        this.PiezasBlancas = blancas;
        this.PiezasNegras = negras;
        if(this.jugador1.isEsNegro()) this.jugador1.setMisPiezas(this.getPiezasNegras());
        else if(!this.jugador1.isEsNegro()) this.jugador1.setMisPiezas(this.getPiezasBlancas());
        if(this.jugador2.isEsNegro()) this.jugador2.setMisPiezas(this.getPiezasNegras());
        else this.jugador2.setMisPiezas(this.getPiezasBlancas());
        this.jugador1.setPosiblesMovimientos(this.todosLosMovimientosPosibles(this.jugador1.getMisPiezas()));
        this.jugador2.setPosiblesMovimientos(this.todosLosMovimientosPosibles(this.jugador2.getMisPiezas()));
        this.jugador1.setTablero(this);
        this.jugador2.setTablero(this);
    }

    /* Pre: Cierto
     * Post: Devuelve el objeto Pieza de ajedrez indicado por el char que ha sido evaluado
     */
    private Pieza getPiezadeChar(char piezasCode, boolean esNegra, int posX, int posY) {
        Pieza p = null;
        if (piezasCode == 'r' || piezasCode == 'R') {
            p = new Torre(esNegra,posX,posY);
        } else if (piezasCode == 'b' || piezasCode == 'B') {
            p = new Alfil(esNegra,posX,posY);
        } else if (piezasCode == 'n' || piezasCode == 'N') {
            p = new Caballo(esNegra,posX,posY);
        } else if (piezasCode == 'p' || piezasCode == 'P') {
            if(piezasCode == 'p'&& posX == 1) p = new Peon(esNegra,true, posX,posY);
            else if(piezasCode == 'P'&& posX == 6) p = new Peon(esNegra,true, posX,posY);
            else p = new Peon(esNegra,false, posX,posY);
        } else if (piezasCode == 'q' || piezasCode == 'Q') {
            p = new Reina(esNegra,posX,posY);
        } else if (piezasCode == 'k' || piezasCode == 'K') {
            p = new Rey(esNegra, posX, posY);
            if(piezasCode == 'k') {
                if(this.jugador1.esNegro) {
                    this.jugador1.setEsteRey(new Rey(esNegra, posX, posY));
                } else {
                    this.jugador2.setEsteRey(new Rey(esNegra, posX, posY));
                }
            } else {
                if(!this.jugador1.esNegro) {
                    this.jugador1.setEsteRey(new Rey(esNegra, posX, posY));
                } else {
                    this.jugador2.setEsteRey(new Rey(esNegra, posX, posY));
                }
            }
        }
        return p;
    }

    /* Pre: Cierto
     * Post: Devuelve todos los movimientos posibles de un ArrayList de Piezas
     */
    private ArrayList<Movimiento> todosLosMovimientosPosibles(final ArrayList<Pieza> piezasJugador) {
        ArrayList<Movimiento> movs = new ArrayList<Movimiento>();
        for (Pieza p: piezasJugador) {
            ArrayList <Movimiento> piezaMov = p.movimientosPosibles(this);
            movs.addAll(piezaMov);
        }
        return movs;
    }

    /* Pre: Cierto
     * Post: Ejecuta el Movimiento dentro del char tablero[][] del parametro implicito,
     * actualiza las arraylists de piezas blancas y/o negras que sean necesario
     */
    public void ejecutarMovimiento(final Movimiento movimiento) {
        Pieza b;
        Pieza n;
        if(this.tablero[movimiento.getToX()][movimiento.getToY()] == '0') {
            this.tablero[movimiento.getToX()][movimiento.getToY()] = this.tablero[movimiento.getFromX()][movimiento.getFromY()];
            this.tablero[movimiento.getFromX()][movimiento.getFromY()] = '0';
            if(this.turnoBlancas) {
                b = this.getPiezasBlancas().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
                this.getPiezasBlancas().remove(b);
                b.setPosX(movimiento.getToX());
                b.setPosY(movimiento.getToY());
                if((b.getTipo() == 'p' || b.getTipo() == 'P') && b.isFirstMove()) b.setFirstMove(false);
                this.getPiezasBlancas().add(b);
            } else {
                n = this.getPiezasNegras().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
                this.getPiezasNegras().remove(n);
                n.setPosX(movimiento.getToX());
                n.setPosY(movimiento.getToY());
                if((n.getTipo() == 'p' || n.getTipo() == 'P') && n.isFirstMove()) n.setFirstMove(false);
                this.getPiezasNegras().add(n);
            }
        }
        else {
            if(this.turnoBlancas) {
                n = this.getPiezasNegras().stream().filter(pieza -> pieza.getPosX() == movimiento.getToX() && pieza.getPosY() == movimiento.getToY()).findAny().get();
                this.getPiezasNegras().remove(n);
                this.tablero[movimiento.getToX()][movimiento.getToY()] = this.tablero[movimiento.getFromX()][movimiento.getFromY()];
                this.tablero[movimiento.getFromX()][movimiento.getFromY()] = '0';
                b = this.getPiezasBlancas().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
                this.getPiezasBlancas().remove(b);
                b.setPosX(movimiento.getToX());
                b.setPosY(movimiento.getToY());
                if((b.getTipo() == 'p' || b.getTipo() == 'P') && b.isFirstMove()) b.setFirstMove(false);
                this.getPiezasBlancas().add(b);
            } else {
                b = this.getPiezasBlancas().stream().filter(pieza -> pieza.getPosX() == movimiento.getToX() && pieza.getPosY() == movimiento.getToY()).findAny().get();
                this.getPiezasBlancas().remove(b);
                this.tablero[movimiento.getToX()][movimiento.getToY()] = this.tablero[movimiento.getFromX()][movimiento.getFromY()];
                this.tablero[movimiento.getFromX()][movimiento.getFromY()] = '0';
                n = this.getPiezasNegras().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
                this.getPiezasNegras().remove(n);
                n.setPosX(movimiento.getToX());
                n.setPosY(movimiento.getToY());
                if((n.getTipo() == 'p' || n.getTipo() == 'P') && n.isFirstMove()) n.setFirstMove(false);
                this.getPiezasNegras().add(n);
            }
        }
        if(!this.jugador2.isEsNegro()) {
            this.jugador2.setMisPiezas(this.getPiezasBlancas());
            this.jugador1.setMisPiezas(this.getPiezasNegras());
        }
        else {
            this.jugador1.setMisPiezas(this.getPiezasBlancas());
            this.jugador2.setMisPiezas(this.getPiezasNegras());
        }
        Rey r1 = (Rey) this.jugador1.getMisPiezas().stream().filter(pieza -> pieza.getTipo() == 'k' || pieza.getTipo() == 'K').findAny().get();
        this.jugador1.setEsteRey(r1);
        Rey r2 = (Rey) this.jugador2.getMisPiezas().stream().filter(pieza -> pieza.getTipo() == 'k' || pieza.getTipo() == 'K').findAny().get();
        this.jugador2.setEsteRey(r2);
        this.jugador1.setPosiblesMovimientos(this.todosLosMovimientosPosibles(jugador1.misPiezas));
        this.jugador2.setPosiblesMovimientos(this.todosLosMovimientosPosibles(jugador2.misPiezas));
        this.jugador2.setTablero(this);
        this.jugador1.setTablero(this);
        this.jugador2.setEnMate(this.jugador1.getPosiblesMovimientos().stream().anyMatch(movi -> (movi.getToX() == this.jugador2.getReydelJugador().getPosX() && movi.getToY() == this.jugador2.getReydelJugador().getPosY())));
        this.jugador1.setEnMate(this.jugador2.getPosiblesMovimientos().stream().anyMatch(movi -> (movi.getToX() == this.jugador1.getReydelJugador().getPosX() && movi.getToY() == this.jugador1.getReydelJugador().getPosY())));
    }

    public void setPiezasBlancas(ArrayList<Pieza> piezasBlancas) {
        PiezasBlancas = piezasBlancas;
    }

    public void setPiezasNegras(ArrayList<Pieza> piezasNegras) {
        PiezasNegras = piezasNegras;
    }

    /* Pre: Cierto
     * Post: Devuelve un booleano
     *      true: si el Movimiento que se ha intentado es posible, y si es asi se ejecuta el Movimiento
     *      false: si es un Movimiento ilegal o no existe alguna Pieza con esa posicion en el tablero
     */
    public boolean movimientoHumano(Movimiento movimiento) {
        Pieza p;
        if(this.turnoBlancas) {
            p = this.getPiezasBlancas().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
        } else {
            p = this.getPiezasNegras().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
        }
        boolean b = p.esMovimientoOk(movimiento, this.tablero);
        if(b) {
            this.ejecutarMovimiento(movimiento);
        }
        return b;
    }

    /* Pre: Cierto
     * Post: Devuelve el Jugador que tiene al estrategia de atacar
     */
    public Jugador getAttackPlayer(boolean estaAtacando) {
        if (jugador1.isEstaAtacando() == estaAtacando) return jugador1;
        else return jugador2;
    }

    /* Pre: Cierto
     * Post: Devuelve el Jugador del cual es su turno
     */
    public Jugador esSuTurno() {
        if (this.turnoBlancas) {
            if (!jugador1.isEsNegro()) return this.jugador1;
            else return this.jugador2;
        } else { //false
            if (jugador1.isEsNegro()) return this.jugador1;
            else return this.jugador2;
        }
    }

    /* Pre: Cierto
     * Post: Devuelve el oponente del Jugador del cual es su turno
     */
    public Jugador miOponenteEs(final Jugador jugador) {
        //returns un jugador
        if(jugador.esNegro) {
            if(this.jugador2.isEsNegro()) return this.jugador1;
            else return this.jugador2;
        } else {
            if(!this.jugador2.isEsNegro()) return this.jugador1;
            else return this.jugador2;
        }
    }

    public boolean movimientoPoneEnMate() {
        return this.getAttackPlayer(true).isEnMate() || this.getAttackPlayer(false).isEnMate();
    }



    /* Pre: Cierto
     * Post: Devuelve el char[][] tablero del parametro implicito
     */
    public char[][] getTablero() {
        return tablero;
    }

    /* Pre: Cierto
     * Post: Devuelve el array de piezas blancas del parametro implicito
     */
    public ArrayList<Pieza> getPiezasBlancas() {
        return PiezasBlancas;
    }

    /* Pre: Cierto
     * Post: Devuelve el array de piezas negras del parametro implicito
     */
    public ArrayList<Pieza> getPiezasNegras() {
        return PiezasNegras;
    }

    /* Pre: Cierto
     * Post: Devuelve el Jugador1 del parametro implicito
     */
    public Jugador getJugador1() {
        return jugador1;
    }

    /* Pre: Cierto
     * Post: Devuelve el Jugador2 del parametro implicito
     */
    public Jugador getJugador2() {
        return jugador2;
    }

    /* Pre: Cierto
     * Post: Devuelve el boolean que nos dice si es el turno de las piezas blancas del parametro implicito
     */
    public boolean getTurnoBlancas() {
        return turnoBlancas;
    }

    /* Pre: Cierto
     * Post: Asocia el parametro turnoBlancas al del parametro implicito
     */
    public void setTurnoBlancas(boolean turnoBlancas) {
        this.turnoBlancas = turnoBlancas;
    }

    /* Pre: Cierto
     * Post: Cambia el turno del Jugador del parametro implicito
     */
    private void cambiarTurno() {
        if (turnoBlancas) this.turnoBlancas = false;
        else this.turnoBlancas = true;
    }
}