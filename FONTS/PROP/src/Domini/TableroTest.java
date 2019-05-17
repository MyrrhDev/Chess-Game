package Domini;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class TableroTest {
    private Jugador jugador2 = new M1(false, false);
    private Jugador jugador1 = new M1(true, true);
    private Tablero tablero = new Tablero(jugador1,jugador2);


    @BeforeClass
    public static void beforeClass() {
        System.out.println("JUNIT Test de la clase Tablero");
        System.out.println("Tests de todas las funciones");
    }
    @AfterClass
    public static void afterClass(){
        System.out.println("Todos los tests han terminado");
    }

    @Test
    public void FENToTablero() {
        tablero.FENToTablero("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B", true);
        assertEquals('N', tablero.tablero[0][1]);
        assertNotSame('3', tablero.tablero[0][2]);
        assertTrue(tablero.getTurnoBlancas());
        tablero.setTurnoBlancas(false);
        assertFalse(tablero.getTurnoBlancas());
    }

    @Test
    public void ejecutarMovimiento() {
        tablero.FENToTablero("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5", true);
        Movimiento m = new Movimiento(1,5,2,5);
        tablero.ejecutarMovimiento(m);
        assertNotSame('R', tablero.tablero[1][5]);
        assertEquals('R', tablero.tablero[2][5]);
    }

    @Test
    public void movimientoHumano() {
        Jugador j1 = new Persona(false, true);
        Jugador j2 = new M1(true, false);
        Tablero permaq = new Tablero(j1, j2);
        permaq.FENToTablero("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5", true);
        Movimiento m = new Movimiento(1,5,2,6);
        assertNotNull("Array no es Null",permaq.getPiezasBlancas());
        permaq.movimientoHumano(m);
        assertFalse(permaq.movimientoHumano(m));
    }

    @Test
    public void getAttackPlayer() {
        Jugador j1 = tablero.getJugador1();
        Jugador j2 = tablero.getJugador2();
        assertNotSame(j2, tablero.getAttackPlayer(true));
        assertEquals(j1, tablero.getAttackPlayer(true));
    }

    @Test
    public void esSuTurno() {
        tablero.setTurnoBlancas(true);
        Jugador j1 = tablero.getJugador1();
        Jugador j2 = tablero.getJugador2();
        assertNotSame(j1, tablero.esSuTurno());
        assertEquals(j2, tablero.esSuTurno());
    }

    @Test
    public void miOponenteEs() {
        Jugador j1 = tablero.getJugador1();
        Jugador j2 = tablero.getJugador2();

        assertNotSame(j2, tablero.miOponenteEs(jugador2));
        assertEquals(j1, tablero.miOponenteEs(jugador2));
    }

    @Test
    public void getTablero() {
    }

    @Test
    public void getPiezasBlancas() {
        ArrayList<Pieza> b = tablero.getPiezasBlancas();
        assertEquals(b, tablero.getPiezasBlancas());
    }

    @Test
    public void getPiezasNegras() {
        ArrayList<Pieza> n = tablero.getPiezasNegras();
        assertEquals(n, tablero.getPiezasNegras());
    }

    @Test
    public void getJugador1() {
        Jugador j1 = tablero.getJugador1();
        assertEquals(j1, tablero.getJugador1());
    }

    @Test
    public void getJugador2() {
        Jugador j2 = tablero.getJugador1();
        assertEquals(j2, tablero.getJugador1());
    }

    @Test
    public void getTurnoBlancas() {
        tablero.setTurnoBlancas(true);
        assertTrue("Es el turno de las blancas",tablero.getTurnoBlancas());
        tablero.setTurnoBlancas(false);
        assertFalse("Es el turno de las negras",tablero.getTurnoBlancas());
    }

    @Test
    public void setTurnoBlancas() {
        tablero.setTurnoBlancas(true);
        assertTrue(tablero.getTurnoBlancas());
        tablero.setTurnoBlancas(false);
        assertFalse(tablero.getTurnoBlancas());
    }

}