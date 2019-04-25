package Domini;

import java.util.Scanner;

public class DriverMaquina {
    static private char estadoTablero[][] = new char[8][8];
    static private boolean esNegraInput;
    private static Tablero t;
    private static Maquina m, m2;
    static private String f[][] = {{"b", "♝"}, {"B", "♗"}, {"n", "♞"}, {"N", "♘"},
            {"p", "♟"}, {"P", "♙"}, {"q", "♛"}, {"Q", "♕"},
            {"k", "♚"}, {"K", "♔"}, {"r", "♜"}, {"R", "♖"}};


    private static void pintaTablero() {
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

    public DriverMaquina() {

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
        System.out.println("Bienvenido al Driver de Maquina. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Maquina");
        System.out.println("    2- Verificar la función jugar de la clase Maquina");
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
                    System.out.println("Introduce, en orden y por terminal, los siguientes valores:");
                    System.out.println("Indica si el color del jugador maquina es negro (true) o es blanca (false)");
                    String s = sc.nextLine();
                    esNegraInput = Boolean.parseBoolean(s);
                    if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                        if (s.equals("true")) { esNegraInput = true; }
                        else if (s.equals("false")) { esNegraInput = false;}
                        else System.out.println("Valor incorrecto.");
                    } else System.out.println("Valor incorrecto.");
                    System.out.println("Indica si la maquina será la atacante(true) o la defensora (false)");
                    s = sc.nextLine();
                    Boolean estaAtacando = false;
                    if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                        if (s.equals("true")) { estaAtacando = true; }
                        else if (s.equals("false")) { estaAtacando = false;}
                        else System.out.println("Valor incorrecto.");
                    } else System.out.println("Valor incorrecto.");
                    m = new Maquina(false, esNegraInput, estaAtacando);
                    System.out.println("Maquina correctamente creada");
                    break;
                case 2:
                    if(m != null) {
                        System.out.println("Para verificar la funcion Jugar, de la clase maquina, debemos primero crear un tablero");
                        m2 = new Maquina(false, !esNegraInput, false);
                        t = new Tablero(m, m2);
                        System.out.println("Introduce un FEN para poblar el tablero");
                        s = sc.nextLine();
                        String r = setFEN(s);
                        t.FENToTablero(r, esNegraInput);
                        pintaTablero();
                        System.out.println("Introduce un valor N");
                        int n = Integer.parseInt(sc.nextLine());
                        try {
                            t = m.jugar(t, n);
                        } catch (Exception e) {
                        }
                        System.out.println("Movimiento:");
                        pintaTablero();
                    }
                    else System.out.println("Debes crear una instancia del objeto Maquina antes de poder usar sus funciones");
                    break;
                case 5:
                    System.out.println("Ejecucion del driver terminada");
                    driverIsRunning = false;
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
                    break;
            }
        }
    }
}