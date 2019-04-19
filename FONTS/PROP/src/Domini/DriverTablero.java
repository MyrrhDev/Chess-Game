package Domini;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverTablero {
    private static Tablero tab;
    private static String tmp;
    static private String f[][] = {{"b", "♝"}, {"B", "♗"}, {"n", "♘"}, {"N", "♞"},
            {"p", "♟"}, {"P", "♙"}, {"q", "♛"}, {"Q", "♕"},
            {"k", "♚"}, {"K", "♔"}, {"r", "♖"}, {"R", "♜"}};


    private static void pintaTablero() {
        System.out.println();
        char[][] t = tab.getTablero();
        System.out.println("   (a) (b) (c) (d) (e) (f) (g) (h)");
        for (int i = 0; i < 8; ++i) {
            System.out.print("(" + i + ")");
            for (int j = 0; j < 8; ++j) {
                if (t[i][j] == '0') System.out.print("[ ] ");
                else if (t[i][j] != '0') {
                    for (int k = 0; k < 12; ++k) {
                        if (String.valueOf(t[i][j]).equals(f[k][0])) {
                            System.out.print("[" + f[k][1] + "] ");
                            break;
                        }

                    }
                }
            }
            System.out.println();
        }
    }

    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Tablero. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Tablero");
        System.out.println("    5- Salir");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean driverIsRunning = true;
        while(driverIsRunning) {
            printMenuPrincipal();
            String input = sc.nextLine();
            int op;
            if(!input.equals("\r") && !input.equals("\n") && !input.equals("\t") && !input.equals("")
                    &&(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5"))) {
                op = Integer.parseInt(input);
            }
            else op = -1;
            switch(op) {
                case 1:
                    Jugador j1 = null, j2 = null;
                    tab = new Tablero(j1, j2);
                    try {
                        //tab.FENtoTablero("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B", true);
                        tab.getTablero();
                        pintaTablero();
                    } catch(Exception e) {}
                    break;
                case 5:
                    System.out.println("Ejecucion del driver terminada");
                    driverIsRunning = false;
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
            }
        }
    }
}
