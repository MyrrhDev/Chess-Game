package Domini;
import java.util.ArrayList;

public class Tablero {

    public char tablero[][] = new char[8][8];
    private ArrayList<Pieza> PiezasBlancas;
    private ArrayList<Pieza> PiezasNegras;
    //Si false: Negras
    //Si true: Blancas
    public boolean turnoBlancas;
    private final Jugador jugador1;
    private final Jugador jugador2;

    public Tablero(final Tablero iniTablero) {
        if(iniTablero.jugador1.isEsMaquina()) {
            this.jugador1 = new Maquina(iniTablero.jugador1.esMaquina, iniTablero.jugador1.esNegro, iniTablero.jugador1.estaAtacando);
        } else {
            this.jugador1 = new Persona(iniTablero.jugador1.esMaquina, iniTablero.jugador1.esNegro, iniTablero.jugador1.estaAtacando);
        }
        if(iniTablero.jugador2.isEsMaquina()) {
            this.jugador2 = new Maquina(iniTablero.jugador2.esMaquina,iniTablero.jugador2.esNegro, iniTablero.jugador2.estaAtacando);
        } else {
            this.jugador2 = new Persona(iniTablero.jugador2.esMaquina,iniTablero.jugador2.esNegro, iniTablero.jugador2.estaAtacando);
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




        //this.PiezasNegras = iniTablero.PiezasNegras;
        //this.PiezasBlancas = iniTablero.PiezasBlancas;

        //this.jugador1 = iniTablero.jugador1;
        //this.jugador2 = iniTablero.jugador2;
    }

    public Tablero(final Jugador jugador1, final Jugador jugador2) {
        if(jugador1.isEsMaquina()) {
            this.jugador1 = new Maquina(jugador1.esMaquina,jugador1.esNegro, jugador1.estaAtacando);
        } else {
            this.jugador1 = new Persona(jugador1.esMaquina, jugador1.esNegro, jugador1.estaAtacando);
        }
        if(jugador2.isEsMaquina()) {
            this.jugador2 = new Maquina(jugador2.esMaquina,jugador2.esNegro, jugador2.estaAtacando);
        } else {
            this.jugador2 = new Persona(jugador2.esMaquina,jugador2.esNegro, jugador2.estaAtacando);
        }
        //this.jugador1 = jugador1;
        //this.jugador2 = jugador2;
    }

    public void setTurnoBlancas(boolean turnoBlancas) {
        this.turnoBlancas = turnoBlancas;
    }

    //Post: Despues de editar un problema en el Tablero, lo transformamos en un String
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



    //Necesito que Problema me corte el "w - - 0 1" del final:
    // "de forma que el sufix de la descripció FEN de la posició inicial serà sempre “- - 0 1”.
    //o Builder: 5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5
    //iniJuegoBlancas = true -> blancas
    //1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1
    //Pre: Asume que los jugadores ya han sido insertados al parametro implicito
    //Post: Las piezas del Problema en formato FEN han sido puestas en el atributo tablero[][]
    public void FENToTablero(String FEN, boolean turnoBlancas) {
        this.turnoBlancas = turnoBlancas;
        ArrayList<Pieza> blancas = new ArrayList<>();
        ArrayList<Pieza> negras = new ArrayList<>();

        int x = 0;
        int y = 0;

        for (int i = 0; i <= FEN.length()-1; i++) {
            if (Character.isDigit(FEN.charAt(i))) {
                int spaces = Character.getNumericValue(FEN.charAt(i));
                //y += spaces - 1;
                int j = 0;
                while(j < spaces) {
                    this.tablero[x][y] = '0';
                    ++j;
                    ++y;
                }
                //System.out.println(x);
                //System.out.println(y);
            } else if (Character.isLowerCase(FEN.charAt(i))) { //Negras
                //++y;
                //Aqui uso x e y [x][y] para meter el char
                this.tablero[x][y] = FEN.charAt(i);
                Pieza p;
                p = getPiezadeChar(FEN.charAt(i), true, x, y);
                negras.add(p);
                ++y;
            } else if (Character.isUpperCase(FEN.charAt(i))) { //Blancas
                //++y;
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

    //Post: Devuelve la pieza indicada por el char
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



    //Post: Devuelve todos los mov posibles de una collection de piezas
    ArrayList<Movimiento> todosLosMovimientosPosibles(final ArrayList<Pieza> piezasJugador) {
        ArrayList<Movimiento> movs = new ArrayList<Movimiento>();
        for (Pieza p: piezasJugador) {
            //movimientosPosibles(final Movimiento m, char estadoTablero[][]);
            ArrayList <Movimiento> piezaMov = p.movimientosPosibles(this.tablero);
            movs.addAll(piezaMov); //Añade de los movs de una pieza al array con todos los movimientos
        }
        return movs;
    }


    //
    //Post: Devuelve el jugador de blanco o negro dependiendo del bool
    public Jugador getJugador(boolean isNegro) {
        if (jugador1.isEsNegro() == isNegro) return jugador1;
        else return jugador2;
    }

    //Post: Me devuelve el Jugador que ataca
    public Jugador getAttackPlayer(boolean estaAtacando) {
        if (jugador1.isEstaAtacando() == estaAtacando) return jugador1;
        else return jugador2;
    }


    //TODO: En algun momento, tengo que cambiar el bool de turnoBlancas
    //Si false: Negras
    //Si true: Blancas
    //Post: Me devuelve el Jugador del cual es su turno
    public Jugador esSuTurno() {
        if (this.turnoBlancas) {
            if (!jugador1.isEsNegro()) return this.jugador1;
            else return this.jugador2;
        } else { //false
            if (jugador1.isEsNegro()) return this.jugador1;
            else return this.jugador2;
        }
    }

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

    public char[][] getTablero() {
        return tablero;
    }

    public ArrayList<Pieza> getPiezasBlancas() {
        return PiezasBlancas;
    }

    public ArrayList<Pieza> getPiezasNegras() {
        return PiezasNegras;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public boolean getTurnoBlancas() {
        return turnoBlancas;
    }

    //Necesito asignar jugadores a este tablero inicial, cual es negro, cual es blanca
    //y darles sus piezas, esto ccumpliria jugador.getMisPiezas() de Evaluacion
    void setJugadoresPiezas() {
        this.jugador1.setMisPiezas(this.PiezasNegras);
    }


    public void setPiezasNegras(ArrayList<Pieza> piezasNegras) {
        PiezasNegras = piezasNegras;
    }

    public void setPiezasBlancas(ArrayList<Pieza> piezasBlancas) {
        PiezasBlancas = piezasBlancas;
    }

    //Pre: De este nuevo Tablero creado, cogo las Piezas Blancas y al mismo array
    //Post: Ejecuta el movimiento, actualiza las arraylists de piezas blancas o negras que sea necesaria
    public void ejecutarMovimiento(final Movimiento movimiento) {

        //Si no hay una pieza en esa posicion
        if(this.tablero[movimiento.getToX()][movimiento.getToY()] == '0') {
            this.tablero[movimiento.getToX()][movimiento.getToY()] = this.tablero[movimiento.getFromX()][movimiento.getFromY()];
            this.tablero[movimiento.getFromX()][movimiento.getFromY()] = '0';

            if(this.turnoBlancas) {
                Pieza p = this.getPiezasBlancas().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
                this.getPiezasBlancas().remove(p);
                p.setPosX(movimiento.getToX());
                p.setPosY(movimiento.getToY());
                //actualizamos array del tablero
                this.getPiezasBlancas().add(p);
                //actualizamos array del jugador blanco
                /*if(!this.jugador2.isEsNegro()) {
                    this.jugador2.setMisPiezas(this.getPiezasBlancas());
                    this.jugador1.setMisPiezas(this.getPiezasNegras());
                }
                else {
                    this.jugador1.setMisPiezas(this.getPiezasBlancas());
                    this.jugador2.setMisPiezas(this.getPiezasNegras());
                }*/
            } else {
                Pieza p = this.getPiezasNegras().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
                this.getPiezasNegras().remove(p);
                p.setPosX(movimiento.getToX());
                p.setPosY(movimiento.getToY());
                //actualizamos array del tablero
                this.getPiezasNegras().add(p);
                //actualizamos array del jugador blanco
                /*if(this.jugador2.isEsNegro()) {
                    this.jugador2.setMisPiezas(this.getPiezasNegras());
                    this.jugador1.setMisPiezas(this.getPiezasBlancas());
                }
                else {
                    this.jugador1.setMisPiezas(this.getPiezasNegras());
                    this.jugador2.setMisPiezas(this.getPiezasBlancas());
                }*/
            }
        }
        else { //Si hay una pieza por matar
            if(this.turnoBlancas) {
                Pieza em = this.getPiezasNegras().stream().filter(pieza -> pieza.getPosX() == movimiento.getToX() && pieza.getPosY() == movimiento.getToY()).findAny().get();
                this.getPiezasNegras().remove(em);
                //
                this.tablero[movimiento.getToX()][movimiento.getToY()] = this.tablero[movimiento.getFromX()][movimiento.getFromY()];
                this.tablero[movimiento.getFromX()][movimiento.getFromY()] = '0';

                //Actulizo en piezas blancas
                Pieza p = this.getPiezasBlancas().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
                this.getPiezasBlancas().remove(p);
                p.setPosX(movimiento.getToX());
                p.setPosY(movimiento.getToY());
                //actualizamos array del tablero
                this.getPiezasBlancas().add(p);
                //actualizamos array del jugador blanco
                /*if (!this.jugador2.isEsNegro()) {
                    this.jugador2.setMisPiezas(this.getPiezasBlancas());
                }
                else this.jugador1.setMisPiezas(this.getPiezasBlancas());*/
            } else {
                Pieza em = this.getPiezasBlancas().stream().filter(pieza -> pieza.getPosX() == movimiento.getToX() && pieza.getPosY() == movimiento.getToY()).findAny().get();
                this.getPiezasBlancas().remove(em);
                //
                this.tablero[movimiento.getToX()][movimiento.getToY()] = this.tablero[movimiento.getFromX()][movimiento.getFromY()];
                this.tablero[movimiento.getFromX()][movimiento.getFromY()] = '0';

                Pieza p = this.getPiezasNegras().stream().filter(pieza -> pieza.getPosX() == movimiento.getFromX() && pieza.getPosY() == movimiento.getFromY()).findAny().get();
                this.getPiezasNegras().remove(p);
                p.setPosX(movimiento.getToX());
                p.setPosY(movimiento.getToY());
                //actualizamos array del tablero
                this.getPiezasNegras().add(p);
                //actualizamos array del jugador blanco
                /*if(this.jugador2.isEsNegro()) this.jugador2.setMisPiezas(this.getPiezasNegras());
                else this.jugador1.setMisPiezas(this.getPiezasNegras());*/
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


        //Actualizar posibles movimientos de los jugadores
        this.jugador1.setPosiblesMovimientos(this.todosLosMovimientosPosibles(jugador1.misPiezas));
        this.jugador2.setPosiblesMovimientos(this.todosLosMovimientosPosibles(jugador2.misPiezas));
        this.jugador2.setTablero(this);
        this.jugador1.setTablero(this);

        //Mate stuff:
        this.jugador2.enMate = this.jugador1.getPosiblesMovimientos().stream().anyMatch(movi -> this.jugador2.getReydelJugador()
                .getPosX() == movi.getToX() && this.jugador2.getReydelJugador().getPosX() == movi.getToY());
        this.jugador1.enMate = this.jugador2.getPosiblesMovimientos().stream().anyMatch(movi -> this.jugador1.getReydelJugador()
                .getPosX() == movi.getToX() && this.jugador1.getReydelJugador().getPosX() == movi.getToY());
    }


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


    //TODO: No estoy segura donde poner esto,
    //si juegan las maquinas, no hay manera de controlar la posicion de la pieza...
    /*
    void getPosiblesMovimientos(Movimiento movimiento){
        ArrayList <Pieza> lasPiezas;
        ArrayList <Pieza> losMovimientos;
        for (Pieza p: lasPiezas) {
            p.
            Movimiento m
            losMovimientos.add(m)
        }
        for (int i = 0; i < lasPiezas.size(); i++) {
        }
    }*/


    //TODO: necesito saber donde estan las piezas...
    //Aunque hiciera simplemente una nueva copia de tablero[][],
    //y aunque sepa de donde a donde va, tendre el char pero cual "pieza" reemplazo del arraylist?
    //y si se "mata" una pieza lo mismo, como se cual "peon" se ha matado
    //Ahora podria buscar el char dentro del arraylist y coger los dos alfiles... si no hay conexion de posicion
    //cual de los dos mato?
    /*
    Tablero nuevoTablero(Movimiento movimiento) {
        int x = movimiento.getfromX();
        int y = movimiento.getfromY();
        ArrayList <Pieza> lasPiezas;
        tablero[x][y]
    }*/





    /*private Pieza setelRey(boolean esNegra, int x, int y) {
        Pieza p = new Rey(esNegra,x,y);
        return p;
    }*/

    /*
    void setPiezaenTablero(char pieza, int x, int y) {
        if (this.tablero[x][y] = "") {
            this.tablero[x][y] = pieza;
        } else throw error("Can't place Pieza, there's already another one");
    }*/


    //Tiene que haber un Jugador asignado a las Negras y otro a las blancas
    //Esto se asigna al principio de la partida
    //Pre: Asume que desde el controlador dominio se han pasado instancias de jugador ya sea Maquina o Persona
    //por referencia a esta funcion y que se a dicho quien esNegro y quien es blanco

    /*public void asignarColorJugador(Jugador j1, Jugador j2) {
        this.jugador1 = j1;
        this.jugador2 = j2;
    }*/

    private void cambiarTurno() {
        if (turnoBlancas) this.turnoBlancas = false; //Negras
        else this.turnoBlancas = true; //Blancas
    }


    //Constructor de Tablero SOLO con Piezas que Existen "actualmente"
    //En la parte de FEN tendremos desde el principio un set de piezas que existen y
    //en esta entrega solo necesitamos comprobar un problema introcudio por el jugardor y sera en FEN
    //Entonces se tiene que crear un Tablero del String FEN
    //Nos dira cual de los jugadores le toca mover en turnoBlancas
    //TAmbien nos dice si white o black comienza/ataca
    //Tablero(String FEN) {


    //}


    //Creame un tablero con las mismas piezas en la misma ubicacion, con el movimiento
    /*public Tablero ponerPieza(Pieza p) {
        this.tablero[][]
    }*/





}