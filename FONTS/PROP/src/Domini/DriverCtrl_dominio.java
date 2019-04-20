package Domini;

import java.util.Scanner;

public class DriverCtrl_dominio {
    static ctrl_dominio c = null;
    private static Problema p;
    private static String tmp;
    static private String f[][] = {{"b", "♝"}, {"B", "♗"}, {"n", "♞"}, {"N", "♘"},
            {"p", "♟"}, {"P", "♙"}, {"q", "♛"}, {"Q", "♕"},
            {"k", "♚"}, {"K", "♔"}, {"r", "♜"}, {"R", "♖"}};


    private static void pintaTablero() {
        System.out.println();
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
        }
        /*System.out.println(f[0][0] + ' ' + f[0][1]);
        System.out.println(f[1][0] + ' ' + f[1][1]);
        System.out.println(f[2][0] + ' ' + f[2][1]);
        System.out.println(f[3][0] + ' ' + f[3][1]);
        System.out.println(f[4][0] + ' ' + f[4][1]);
        System.out.println(f[5][0] + ' ' + f[5][1]);
        System.out.println(f[6][0] + ' ' + f[6][1]);
        System.out.println(f[7][0] + ' ' + f[7][1]);
        System.out.println(f[8][0] + ' ' + f[8][1]);
        System.out.println(f[9][0] + ' ' + f[9][1]);
        System.out.println(f[10][0] + ' ' + f[10][1]);
        System.out.println(f[11][0] + ' ' + f[11][1]);*/
    }

    /*
     * Pre: Cierto
     * Post: Imprime por consola las posibles opciones a probar con el driver
     */
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Controlador de Dominio. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Problema");
        System.out.println("    2- Empezar una partida");
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
        c = ctrl_dominio.getInstance();
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
                    //creo un problema
                    tmp = sc.nextLine();
                    p = new Problema();
                    p.setIniJuegoBlancas(tmp);
                    p.setFEN(tmp);
                    System.out.println("Introduce el valor de N:");
                    p.setN(Integer.parseInt(sc.nextLine()));
                    if (p != null)
                        System.out.println("Problema creado con éxito, valores: " + p.getFEN() + ' ' + p.getIniJuegoBlancas() + ' ' + p.getN());
                    else System.out.println("Error");
                    break;
                case 2:
                    //jugar
                    System.out.println("Introduce por terminal los jugadores que formaran parte de la partida, separados por un espacio");
                    System.out.println("Tanto para el jugador 1 como para el jugador 2");
                    System.out.println("1 -> H1, 2 -> M1, 3 -> M2");
                    tmp = sc.nextLine();
                    String aux[] = tmp.split(" ");
                    //if (!aux[0].equals("2") && !aux[1].equals("2")) System.out.println("La partida debe ser jugada por M1");
                    //else if (aux[0] != "2" && aux[1] != "2") System.out.println("M2 no forma parte de esta entrega");
                    if ((aux[0].equals("2") && aux[1].equals("2")) && p != null) {
                        //jugamos, input OK
                        c = ctrl_dominio.getInstance();
                        c.crearPartida(p, Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
                        System.out.println("Partida creada con éxito");
                        pintaTablero();
                        int n = p.getN();
                        //@TODO: Dejar de iterar si uno de los dos jugadores está en jaque mate
                        while (n > 0) {
                            try {
                                c.jugar(n);
                            }catch (Exception e) {}
                            --n;
                            pintaTablero();
                        }
                    }
                    else if(aux[0].equals("1") && aux[1].equals("2")) {
                        c.crearPartida(p, Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
                        System.out.println("Partida creada con éxito");
                        pintaTablero();
                        int n = p.getN();
                        while(n > 0) {
                            boolean inputOk = false;
                            while(!inputOk) {
                                try { //TODO: Si introduce un movimiento ilegal propagar la excepcion y pedirle otra vez un movimiento
                                    //juega humano
                                    System.out.println("Introduce el movimiento de la pieza: posX pieza, posY pieza, moveX, movY");
                                    String tmp[] = sc.nextLine().split(" ");
                                    if(verificarEntrada(tmp)) {
                                        c.jugar(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]));
                                        System.out.println("Estado del tablero después del movimiento:");
                                        pintaTablero();
                                        inputOk = true;
                                    } else System.out.println("Error en la entrada");
                                } catch (Exception e) {
                                    n = 0;
                                    break;
                                }
                            }
                            try {
                                //juega maquina
                                System.out.println("Juega maquina");
                                c.jugar(n);
                                System.out.println("Movimiento:");
                                pintaTablero();
                            } catch (Exception e) {
                                n = 0;
                                break;
                            }
                            --n;
                        }
                    }
                    else if((aux[0].equals("2") && aux[1].equals("1"))) {
                        System.out.println("Not yet");
                    }
                    //Scann jug1 y jug2
                    /*c.crearPartida(p, 0, 1);
                    while(N > 0 & !j1.esEnJaqueMate & !j1.estaEstancado & !j2.esEnJaqueMate & !j2.estaEstancado) {
                        if (j1.esSuTurno & j1.esMaquina) c.jugar();
                        if (j1.esSuTurno & !j1.esMaquina) {
                            //leer input
                            c.jugar(posX...)
                        }
                        --N;
                    }
                    si j1 esta en jaque mate pues ha perdido jajaj salu2
                    si j2 esta en ja.... ha perdido*/
                    break;
                case 3:
                    pintaTablero();

                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
            }
        }
    }
}