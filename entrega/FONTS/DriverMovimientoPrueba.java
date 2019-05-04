package Domini;

import java.util.Scanner;

public class DriverMovimientoPrueba {
    static private char estadoTablero[][] = new char[8][8];
    static private boolean esNegraInput;
    private static Tablero t, t2;
    private static Maquina m1, m2;
    private static MovimientoPrueba mp;
    private static Movimiento m;
    static private String f[][] = {{"b", "♝"}, {"B", "♗"}, {"n", "♞"}, {"N", "♘"},
            {"p", "♟"}, {"P", "♙"}, {"q", "♛"}, {"Q", "♕"},
            {"k", "♚"}, {"K", "♔"}, {"r", "♜"}, {"R", "♖"}};


    private static void pintaTablero(Tablero t) {
        estadoTablero = t.getTablero();
        System.out.println("   (a) (b) (c) (d) (e) (f) (g) (h)");
        for (int i = 0; i < 8; ++i) {
            System.out.print("(" + i + ")");
            for (int j = 0; j < 8; ++j) {
                if (estadoTablero[i][j] == '0') System.out.print("[ ] ");
                else if (estadoTablero[i][j] != '0') {
                    for (int k = 0; k < 12; ++k) {
                        if (String.valueOf(estadoTablero[i][j]).equals(f[k][0])) {
                            System.out.print("[" + f[k][1] + "] ");
                            break;
                        }

                    }
                }
            }
            System.out.println();
        }
    }

    public DriverMovimientoPrueba() {

    }

    /*
     * Pre: Cierto
     * Post: Lee el estado del tablero desde la terminal
     */
    public static void iniTablero() {
        for(int i = 0; i < 8; ++i)
            for(int j = 0; j < 8; ++j)
                estadoTablero[i][j] = '0';
    }

    private static String setFEN(String FEN) {
        String prob = "";
        int i = FEN.indexOf('w');
        if(i == -1) { //no existe el carácter w, verificamos que efectivamente empiezan las negras atacando
            i = FEN.indexOf('b');
        }
        int newLength = FEN.indexOf(' ');
        prob = FEN.substring(0, newLength);
        return prob;
    }

    /*
     * Pre: Cierto
     * Post: Imprime por consola las posibles opciones a probar con el driver
     */
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de MovimientoPrueba. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto MovimientoPrueba");
        System.out.println("    5- Salir");
    }


    public static void main(String[] args) {
        estadoTablero = new char[8][8];
        iniTablero();
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
                    m1 = new Maquina(true, false, true);
                    m2 = new Maquina(true, true, false);
                    t = new Tablero(m1, m2);
                    t2 = new Tablero(m1, m2);
                    System.out.println("Introduce un FEN para poblar el primer tablero");
                    boolean go = false;
                    String fen = "";
                    while(!go) {
                        String tocheck = sc.nextLine();
                        if(!tocheck.endsWith("- - 0 1")) {
                            System.out.println("El FEN no es correcto, intentalo de nuevo.");
                        } else {
                            fen = tocheck;
                            go = true;
                        }
                    }
                    String r = setFEN(fen);
                    t.FENToTablero(r, esNegraInput);
                    pintaTablero(t);
                    System.out.println("Introduce, por orden, los valores del objeto Movimiento.");
                    System.out.print("fromX: ");
                    System.out.println();
                    int fromX = Integer.parseInt(sc.nextLine());
                    System.out.print("fromY: ");
                    System.out.println();
                    int fromY = Integer.parseInt(sc.nextLine());
                    System.out.print("ToX: ");
                    System.out.println();
                    int ToX = Integer.parseInt(sc.nextLine());
                    System.out.print("ToY: ");
                    System.out.println();
                    int ToY = Integer.parseInt(sc.nextLine());
                    System.out.print("Hay alguna Pieza a matar? si (true) o no (false):");
                    System.out.println();
                    String s = sc.nextLine();
                    if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                        if (s.equals("true")) {
                            System.out.print("Indica su tipo mediante un char");
                            System.out.println();
                            char c = sc.nextLine().charAt(0);
                            m = new Movimiento(fromX, fromY, ToX, ToY, c);
                        }
                        else if (s.equals("false")) {
                            m = new Movimiento(fromX,fromY,ToX,ToY);
                        }
                        else System.out.println("Valor incorrecto.");
                    } else System.out.println("Valor incorrecto.");
                    System.out.println("Objeto movimiento creado con éxito");
                    System.out.println("Se puede realizar el movimiento true/false?");
                    boolean resEsperado = Boolean.parseBoolean(sc.nextLine());
                    mp = new MovimientoPrueba(t, t2, m, resEsperado);
                    System.out.println("Objeto creado con éxito");

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