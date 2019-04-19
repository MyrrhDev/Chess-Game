package Domini;

public class ctrl_dominio {
    private static Jugador j1, j2;
    private static Tablero t;
    private static int turnosBlancas, turnosNegras;
    private static ctrl_dominio singleInstance = null;

    private ctrl_dominio() {
        j1 = j2 = null;
    }

    public static ctrl_dominio getInstance() {
        if(singleInstance == null) singleInstance = new ctrl_dominio();
        return singleInstance;
    }

    /*
     Pre: j1 abre el juego
     Post: Tanto para j1 como para j2:
     Si jug1 == 1 -> j1 Persona
     Si jug1 == 2 -> j1 M1
     Si jug1 == 3 -> j1 M2
     Si colorJug1 true entonces blancas abren juego y Jug1 es blanco
     Si colorJug1 false entonces
     */

    public static void seleccionarJugadores(int jug1, int jug2, boolean colorJug1) {
        switch(jug1) {
            case 1:
                //if(colorJug1) j1 = new Persona(); //blanca //TODO: li he de passar es maquina, es negro i esta atacando
                //else j1 = new Persona(true); //negra
                break;
            case 2:
                //j1 = new M1();
                break;
            case 3:
                //j1 = new M2();
                break;
        }
        switch(jug2) {
            case 1:
                //if(colorJug1) j1 = new Persona(true); //negra porque jug1 blanca
                //else j1 = new Persona(false); //blanca porque jug1 negra
                break;
            case 2:
                //j2 = new M1();
                break;
            case 3:
                //j2 = new M2();
                break;
        }
    }

    //Si false: Negras
    //Si true: Blancas
    public boolean getTurno() {
        return this.t.isSiguienteMovimiento();
    }

    public boolean isJ1Blancas() {
        return !j1.isEsNegro();
    }

    public boolean isJ1M() {
        return j1.isEsMaquina();
    }

    public boolean isJ2Blancas() {
        return !j2.isEsNegro();
    }

    public boolean isJ2M() {
        return j2.isEsMaquina();
    }


    public static void jugar() throws Exception {
        if(!j1.isEnJaqueMate() && !j2.isEnJaqueMate()) {
            if (j1.isEsMaquina() && j1.isEsNegro() && !t.isSiguienteMovimiento()) {
                /*try {
                    j1.jugar(); // paso tablero y N
                } catch (e) {}*/
            }
            else if (j1.isEsMaquina() && !j1.isEsNegro() && t.isSiguienteMovimiento());
            else if (true); //@TODO: Aqí iría j2 haz tu movimiento (MINIMAX)
        }
        else {
            //tirar excepción que no se puede jugar más
        }
        /*if(j1.getEsMaquina() & j1.esSuTurno) {
            try {
                t = j1.M1Juega();
            }
            catch(Exception e) {
                //hay jaque mate o has perdido
            }
            !j1.esSuTurno;
            !j2.esSuTurno;
        }
        else if(!j1.esMaquina & j1.esSuTurno) {
        }*/
    }

    public char[][] getTablero() {
        //return t.getTablero(); @TODO
        char[][] a = {{'0', '0', '0', '0', 'B', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0'},
            {'b', '0', '0', '0', 'P', '0', '0', 'P'},
            {'0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', 'Q', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', 'q', '0', '0'}};
        return a;
    }


    public static void jugar(int posX, int posY, int movX, int movY) throws Exception {
        if(!j1.isEsMaquina() & t.isSiguienteMovimiento() & !j1.isEsNegro()) { //tira jugador 1
            try {
                Movimiento m = new Movimiento(posX, posY, movX, movY);
                //t = j1.HJuega(t, m);
            }
            catch(Exception e) {
                //hay jaque mate o has perdido
            }
            //tablero debe actualizar el turno
        }
        if(!j1.isEsMaquina() & !t.isSiguienteMovimiento() & j1.isEsNegro()) { //tira jugador 1
            try {
                Movimiento m = new Movimiento(posX, posY, movX, movY);
                //t = j1.HJuega(t, m);
            }
            catch(Exception e) {
                //hay jaque mate o has perdido
            }
        }
        if(!j2.isEsMaquina() & t.isSiguienteMovimiento() & !j2.isEsNegro()) { //tira jugador 1
            try {
                Movimiento m = new Movimiento(posX, posY, movX, movY);
                //t = j2.HJuega(t, m);
            }
            catch(Exception e) {
                //hay jaque mate o has perdido
            }
        }
        if(!j2.isEsMaquina() & t.isSiguienteMovimiento() & !j2.isEsNegro()) { //tira jugador 1
            try {
                Movimiento m = new Movimiento(posX, posY, movX, movY);
                //t = j2.HJuega(t, m); //no lo detecta
                //Persona p = new Persona(false);
                //t = p.HJuega(t, m); //si que lo detecta
                //Si implementamos una función en jugador que sea HJuega si que lo detectaria desde jugador
                //si implementamos una función abstracta en jugador que sea M1Juega y otra HJuega desde jugador
                //y hacemos override en las clases abstractas entonces si que detectaria HJuega desde jugador
            }
            catch(Exception e) {
                //hay jaque mate o has perdido
            }
        }
    }

    public static void crearPartida(Problema p, int jug1, int jug2) {
        seleccionarJugadores(jug1, jug2, p.getIniJuegoBlancas());
        //t = new Tablero(p.getFEN(), p.iniJuegoBlancas, j1, j2); //antigua FENToTablero
        turnosBlancas = turnosNegras = p.getN();
    }

    public static void main(String[] args) {
        /*DriverCtrl_dominio cd = new DriverCtrl_dominio();
        cd.main(args);*/
        DriverCaballo dc = new DriverCaballo();
        dc.main(args);

    }

    /*
    =================================== FUNCIONES A IMPLEMENTAR PARA SEGUNDA ENTREGA ===================================
     */
}