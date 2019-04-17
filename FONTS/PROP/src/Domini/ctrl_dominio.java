package Domini;

import java.util.ArrayList;
import java.util.HashMap;

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
     */
    public static void seleccionarJugadores(int jug1, int jug2, char colorJug1) {
        switch(jug1) {
            case 1:
                if(colorJug1 == 'w') j1 = new Persona(false); //blanca
                else j1 = new Persona(true); //negra
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
                if(colorJug1 == 'w') j1 = new Persona(true); //negra
                else j1 = new Persona(false); //blanca
                break;
            case 2:
                //j2 = new M1();
                break;
            case 3:
                //j2 = new M2();
                break;
        }
    }

    /*public static void Jugar() throws Exception {
        if(j1.esMaquina & j1.esSuTurno) {
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
        }
    }*/

    /*
    Tu entiendes que j1 o j2 hace un movimiento
    Yo entiendo que yo hago el movimiento
     */
    /*public static void Jugar(int posX, int posY, int movX, int movY) throws Exception {
        if(!j1.esMaquina & j1.esSuTurno) {
            try {
                Movimiento m(posx, posy, movx, movy);
                t = j1.M1Juega(t,m);
            }
            catch(Exception e) {
                //hay jaque mate o has perdido
            }
            !j1.esSuTurno;
            !j2.esSuTurno;
        }
        else if(!j2.esMaquina & j2.esSuTurno) {
            try {
                Movimiento m(posx, posy, movx, movy);
                t = j1.M1Juega(t,m);
            }
            catch(Exception e) {
                //hay jaque mate o has perdido
            }
            !j1.esSuTurno;
            !j2.esSuTurno;
        }
    }*/

    public static void crearPartida(Problema p, int jug1, int jug2) {
        //seleccionarJugadores(jug1, jug2, p.abreJuego); @TODO Arreglar el 3 atributo de la función
        t = new Tablero(p.getFEN(), p.abreJuego); //antigua FENToTablero
        turnosBlancas = turnosNegras = p.getN();
    }

    public static void main(String[] args) {
        DriverTorre dt = new DriverTorre();
        dt.main(args);
    }

    /*
    =================================== FUNCIONES A IMPLEMENTAR PARA SEGUNDA ENTREGA ===================================
     */
}