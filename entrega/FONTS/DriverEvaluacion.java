package Domini;

import java.util.Scanner;

public class DriverEvaluacion {
    private static Problema p;
    private static Tablero tablero;
    private static Evaluacion eval;
    private static Jugador jugador2;
    private static Jugador jugador1;
    private static int N;

    public DriverEvaluacion() {

    }

    /*
     * Pre: Cierto
     * Post: Imprime por consola las posibles opciones a probar con el driver
     */
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Evaluacion. Sigue el orden instrucciones para comenzar:");
        System.out.println("    1- Alta objeto Evaluacion");
        System.out.println("    2- Crear un Tablero con un FEN");
        System.out.println("    3- Indica el nivel de profundidad");
        System.out.println("    4- Inicia la evaluacion como Jugador con Piezas Negras que ataca");
        System.out.println("    5- Inicia la evaluacion como Jugador con Piezas Blancas que ataca");
        System.out.println("    6- Salir");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean driverIsRunning = true;
        while(driverIsRunning) {
            printMenuPrincipal();
            String input = sc.nextLine();
            int op;
            if(!input.equals("\r") && !input.equals("\n") && !input.equals("\t") && !input.equals("")
                    &&(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6"))) {
                op = Integer.parseInt(input);
            }
            else op = -1;
            switch(op) {
                case 1:
                    eval = new Evaluacion();
                    System.out.println("Objeto Evaluacion creado!");
                    break;
                case 2:
                    jugador2 = new Maquina(true,false, false);
                    jugador1 = new Maquina(true, true, true);
                    tablero = new Tablero(jugador1,jugador2);
                    System.out.println("Inserte el codigo FEN!");
                    boolean go = false;
                    while(!go) {
                        String tocheck = sc.nextLine();
                        if(!tocheck.endsWith("- - 0 1")) {
                            System.out.println("El FEN no es correcto, intentalo de nuevo.");
                        } else {
                            fen = tocheck;
                            go = true;
                        }
                    }
                    p = new Problema();
                    p.setFEN(fen);
                    tablero.FENToTablero(p.getFEN(), p.getIniJuegoBlancas());
                    break;
                case 3:
                    System.out.println("Indica el nivel de profundidad:");
                    go = false;
                    while(!go) {
                        String tocheck = sc.nextLine();
                        char c = tocheck.charAt(0);
                        if (!(Character.isDigit(c))) {
                            System.out.println("El N no es correcto, intentalo de nuevo.");
                        } else {
                            N = Integer.parseInt(tocheck);
                            go = true;
                        }
                    }
                    p.setN(N);
                    break;
                case 4:
                    System.out.println("Puntos de la evaluacion: " + eval.evaluar(tablero,p.getN()));
                    break;
                case 5 :
                    jugador2 = new Maquina(true,false, true);
                    jugador1 = new Maquina(true, true, false);
                    tablero = new Tablero(jugador1,jugador2);
                    tablero.FENToTablero(p.getFEN(), p.getIniJuegoBlancas());
                    System.out.println("Puntos de la evaluacion: " + eval.evaluar(tablero,p.getN()));
                    break;
                case 6:
                    System.out.println("Ejecucion del driver terminada");
                    driverIsRunning = false;
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
            }
        }
    }
}
