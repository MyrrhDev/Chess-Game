package Domini.DriversPrimeraEntrega;

import Domini.ctrl_dominio;

import java.util.Scanner;

public class DriverCtrl_dominio {
    static ctrl_dominio c = null;
    private static String problema;
    private static String tmp;
    private static int N;
    static private String f[][] = {{"b", "♝"}, {"B", "♗"}, {"n", "♞"}, {"N", "♘"},
            {"p", "♟"}, {"P", "♙"}, {"q", "♛"}, {"Q", "♕"},
            {"k", "♚"}, {"K", "♔"}, {"r", "♜"}, {"R", "♖"}};

    /*
    Pre: Cierto
    Post: Pinta el tablero por consola
     */
    private static void pintaTablero() {
        /*System.out.println();
        char[][] t = c.getTablero();
        System.out.println("   (a) (b) (c) (d) (e) (f) (g) (h)");
        for(int i = 0; i < 8; ++i) {
            System.out.print("(" + i + ")");
            for(int j = 0; j < 8; ++j) {
                if(t[i][j] == '0') System.out.print("[ ] ");
                else if(t[i][j] != '0') {
                    for(int k = 0; k < 12; ++k) {
                        if(String.valueOf(t[i][j]).equals(f[k][0])) {
                            System.out.print("[" + f[k][1] + "] ");
                            break;
                        }

                    }
                }
            }
            System.out.println();
        }*/
    }

    /*
     * Pre: Cierto
     * Post: Imprime por consola las posibles opciones a probar con el driver
     */
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Controlador de Dominio. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Controlador de Dominio");
        System.out.println("    2- Alta objeto Problema y validación del mismo");
        System.out.println("    3- Empezar una partida");
        System.out.println("    5- Salir");
    }


    private static boolean verificarEntrada(String tmp[]) {
        int posX = Integer.parseInt(tmp[0]);
        int posY = Integer.parseInt(tmp[1]);
        int movX = Integer.parseInt(tmp[2]);
        int movY = Integer.parseInt(tmp[3]);
        if(posX >= 0 && posY >= 0 && posX < 8 && posY < 8 &&
                movX >= 0 && movY >= 0 && movX < 8 && movY <8) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean driverIsRunning = true;
        while (driverIsRunning) {
            printMenuPrincipal();
            String input = sc.nextLine();
            int op;
            if (!input.equals("\r") && !input.equals("\n") && !input.equals("\t") && !input.equals("")
                    && (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5"))) {
                op = Integer.parseInt(input);
            } else op = -1;
            switch (op) {
                case 1:
                    System.out.println("Creando una instancia de Controlador de Dominio, espera");
                    if(c == null) {
                        c = ctrl_dominio.getInstance();
                        System.out.println("Instancia de controlador creada con éxito");
                    }
                    else System.out.println("Ya existe una instancia de controlador");
                    break;
                case 2:
                    c = ctrl_dominio.getInstance();
                    System.out.println("Introduce el código FEN del problema:");
                    boolean go = false;
                    while(!go) {
                        String tocheck = sc.nextLine();
                        if(!tocheck.endsWith("- - 0 1")) {
                            System.out.println("El FEN no es correcto, intentalo de nuevo.");
                        } else {
                            problema = tocheck;
                            go = true;
                        }
                    }
                    System.out.println("Introduce el valor de N:");
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
                    System.out.println("Validando problema, espera");
                    if(c.esProblemaValidable(problema, N)) System.out.println("Problema verificado");
                    else System.out.println("No se puede verificar el problema");
                    break;
                case 3:
                    c = ctrl_dominio.getInstance();
                    System.out.println("Introduce por terminal el FEN del problema a jugar");
                    go = false;
                    while(!go) {
                        String tocheck = sc.nextLine();
                        if (!tocheck.endsWith("- - 0 1")) {
                            System.out.println("El FEN no es correcto, intentalo de nuevo.");
                        } else {
                            problema = tocheck;
                            go = true;
                        }
                    }
                    go = false;
                    System.out.println("Introduce el valor de N del problema");
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
                    go = false;

                    System.out.println("Introduce por terminal los jugadores que formaran parte de la partida, separados por un espacio");
                    System.out.println("Tanto para el jugador 1 como para el jugador 2");
                    System.out.println("1 -> H1, 2 -> M1");
                    String aux[]= new String[2];
                    while(!go) {
                        String tocheck = sc.nextLine();
                        aux = tocheck.split(" ");
                        if ((aux[0].equals("2") && aux[1].equals("2"))) {
                            go = true;
                            c.crearPartida(problema, N, Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
                            System.out.println("Partida creada con éxito");
                            pintaTablero();
                            int n = N;
                            while (n > 0) {
                                try {
                                    System.out.println("Calculando movimientos");
                                    c.jugar(n);
                                    System.out.println("Movimientos realizados:");
                                    pintaTablero();
                                } catch (Exception e) {
                                    if (e.getMessage().equals("Jugador 1 en Jaque mate"))
                                        System.out.println("J1 ha perdido");
                                    else if (e.getMessage().equals("Jugador 2 en Jaque mate"))
                                        System.out.println("J2 ha perdido");
                                    System.out.println("Tablero final:");
                                    pintaTablero();
                                    n = 0;
                                    break;
                                }
                                --n;
                                if (n == 0) {
                                    System.out.println("J1 ha perdido");
                                }
                            }
                        } else if (aux[0].equals("1") && aux[1].equals("2")) {
                            go = true;
                            c.crearPartida(problema, N, Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
                            System.out.println("Partida creada con éxito. Jugador humano tiene piezas blancas.");
                            pintaTablero();
                            int n = N;
                            while (n > 0) {
                                try {
                                    //juega humano
                                    System.out.println("Introduce el movimiento de la pieza: posX pieza, posY pieza, moveX, movY");
                                    String tmp[] = sc.nextLine().split(" ");
                                    if (verificarEntrada(tmp)) {
                                        c.jugar(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]));
                                        System.out.println("Movimiento:");
                                        pintaTablero();
                                    } else System.out.println("Error en la entrada");
                                } catch (Exception e) {
                                    System.out.println("J1 no puede moverse. Jaque Mate");
                                    n = 0;
                                    break;
                                }
                                try {
                                    System.out.println("Calculando movimientos");
                                    c.jugar(n);
                                    System.out.println("Movimientos realizados:");
                                    pintaTablero();
                                } catch (Exception e) {
                                    if (e.getMessage().equals("Jugador 1 en Jaque mate"))
                                        System.out.println("J1 ha perdido");
                                    else if (e.getMessage().equals("Jugador 2 en Jaque mate"))
                                        System.out.println("J2 ha perdido");
                                    System.out.println("Tablero final:");
                                    pintaTablero();
                                    n = 0;
                                    break;
                                }
                                --n;
                                if (n == 0) {
                                    System.out.println("J1 ha perdido");
                                }
                            }
                        } else if ((aux[0].equals("2") && aux[1].equals("1"))) {
                            go = true;
                            c.crearPartida(problema, N, Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
                            System.out.println("Partida creada con éxito. Jugador humano tiene piezas negras.");
                            pintaTablero();
                            int n = N;
                            while (n > 0) {
                                try {
                                    System.out.println("Calculando movimientos");
                                    c.jugar(n);
                                    System.out.println("Movimientos realizados:");
                                    pintaTablero();
                                } catch (Exception e) {
                                    if (e.getMessage().equals("Jugador 1 en Jaque mate"))
                                        System.out.println("J1 ha perdido");
                                    else if (e.getMessage().equals("Jugador 2 en Jaque mate"))
                                        System.out.println("J2 ha perdido");
                                    System.out.println("Tablero final:");
                                    pintaTablero();
                                    n = 0;
                                    break;
                                }
                                try {
                                    //juega humano
                                    System.out.println("Introduce el movimiento de la pieza: posX pieza, posY pieza, moveX, movY");
                                    String tmp[] = sc.nextLine().split(" ");
                                    if (verificarEntrada(tmp)) {
                                        c.jugar(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]));
                                        System.out.println("Estado del tablero después del movimiento:");
                                        pintaTablero();
                                    } else System.out.println("Error en la entrada");
                                } catch (Exception e) {
                                    if (e.getMessage().equals("Jugador 1 en Jaque mate"))
                                        System.out.println("J1 ha perdido");
                                    else if (e.getMessage().equals("Jugador 2 en Jaque mate"))
                                        System.out.println("J2 ha perdido");
                                    System.out.println("Tablero final:");
                                    pintaTablero();
                                    n = 0;
                                    break;
                                }
                                --n;
                                if (n == 0) {
                                    System.out.println("J1 ha perdido");
                                }
                            }
                        } else {
                            System.out.println("Los numeros solo pueden ser 1 o 2");
                        }
                    }

                    break;
                case 5:
                    System.out.println("Ejeccución del driver terminada");
                    driverIsRunning = false;
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
                    break;
            }
        }
    }
}