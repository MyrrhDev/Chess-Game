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


    public static void seleccionarJugadores(int jug1, int jug2, boolean j1EsBlanco) {
        switch(jug1) {
            case 1:
                if(j1EsBlanco) j1 = new Persona(false, false, true); //blanca //TODO: li he de passar es maquina, es negro i esta atacando
                else j1 = new Persona(false, true, true); //negra
                break;
            case 2:
                if(j1EsBlanco) j1 = new Maquina(true, false, true);
                else j1 = new Maquina(true, true, true);
                break;
            case 3:
                //j1 = new M2();
                break;
        }
        switch(jug2) {
            case 1:
                if(j1EsBlanco) j2 = new Persona(false, true, false); //negra porque jug1 blanca
                else j2 = new Persona(false, false, false); //blanca porque jug1 negra
                break;
            case 2:
                if(j1EsBlanco) j2 = new Maquina(true, true, false);
                else j2 = new Maquina(true, false, false);
                //j2 = new M1();
                break;
            case 3:
                //j2 = new M2();
                break;
        }
    }

    public static void jugar(int n) throws Exception {
        if(!j1.isEnJaqueMate() && !j2.isEnJaqueMate()) {
            if (((j1.isEsNegro() && !t.getTurnoBlancas()) || (!j1.isEsNegro() && t.getTurnoBlancas())) && j1.isEsMaquina()) {
                try {
                    t = j1.jugar(t,n); // paso tablero y N
                    t.setTurnoBlancas(!t.getTurnoBlancas());
                } catch(Exception e) {
                    Exception e1 = new Exception("Jugador 1 en Jaque mate");
                    throw e1;
                }

            }
            if (((j2.isEsNegro() && !t.getTurnoBlancas()) || (!j2.isEsNegro() && t.getTurnoBlancas())) && j2.isEsMaquina()) {
                try {
                    t = j2.jugar(t,n); // paso tablero y N
                    t.setTurnoBlancas(!t.getTurnoBlancas());
                } catch(Exception e) {
                    Exception e1 = new Exception("Jugador 2 en Jaque mate");
                    throw e1;
                }

            }
        }
        else if(j1.isEnJaqueMate()) {
            Exception e = new Exception("Jugador 1 en Jaque mate");
            throw e;
        }
        else if(j2.isEnJaqueMate()) {
            Exception e = new Exception("Jugador 2 en Jaque mate");
            throw e;
        }
    }

    public char[][] getTablero() {
        return t.getTablero();
    }

    public Jugador getJ1() {
        return j1;
    }

    public Jugador getJ2() {
        return j2;
    }

    public static void jugar(int posX, int posY, int movX, int movY) throws Exception {
        if(!j1.isEnJaqueMate() && !j2.isEnJaqueMate()) {
            Movimiento m = new Movimiento(posX, posY, movX, movY);
            if ((j1.isEsNegro() && !t.getTurnoBlancas()) || (!j1.isEsNegro() && t.getTurnoBlancas())) {
                try {
                    t = j1.jugar(t, m); // paso tablero y N
                    t.setTurnoBlancas(!t.getTurnoBlancas());
                } catch(Exception e) {
                    throw e;
                }
            }
            else if ((j2.isEsNegro() && !t.getTurnoBlancas()) || (!j2.isEsNegro() && t.getTurnoBlancas())) {
                try {
                    t = j2.jugar(t,m); // paso tablero y N
                    t.setTurnoBlancas(!t.getTurnoBlancas());
                } catch(Exception e) {
                    throw e;
                }

            }
        }
        else {
            Exception e = new Exception("H1 en Mate");
            throw e;
        }
    }

    public static void crearPartida(Problema p, int jug1, int jug2) {
        seleccionarJugadores(jug1, jug2, p.getIniJuegoBlancas());
        t = new Tablero(j1, j2); //antigua FENToTablero
        t.FENToTablero(p.getFEN(), p.getIniJuegoBlancas());
    }

    public static void main(String[] args) {
        /*DriverCtrl_dominio cd = new DriverCtrl_dominio();
        cd.main(args);*/
        /*DriverCaballo dc = new DriverCaballo();
        dc.main(args);*/
        /*DriverAlfil da = new DriverAlfil();
        da.main(args);*/
        /*DriverProblema dp = new DriverProblema();
        dp.main(args);*/
        /*DriverPeon dp = new DriverPeon();
        dp.main(args);*/
        /*DriverPersona dp = new DriverPersona();
        dp.main(args);*/
        /*DriverMaquina dm = new DriverMaquina();
        dm.main(args);*/
        /*DriverMovimiento dm = new DriverMovimiento();
        dm.main(args);*/
        DriverMovimientoPrueba dmp = new DriverMovimientoPrueba();
        dmp.main(args);

    }

    /*
    =================================== FUNCIONES A IMPLEMENTAR PARA SEGUNDA ENTREGA ===================================
     */
}
