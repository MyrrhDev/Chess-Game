package Domini;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverPeon {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    static private char estadoTablero[][] = new char[8][8];
    static private int move[];
    private static ArrayList <Pieza> PiezasBlancas;
    private static ArrayList <Pieza> PiezasNegras;
    static private String f[][] = {{"b", "♝"}, {"B", "♗"}, {"n", "♞"}, {"N", "♘"},
            {"p", "♟"}, {"P", "♙"}, {"q", "♛"}, {"Q", "♕"},
            {"k", "♚"}, {"K", "♔"}, {"r", "♜"}, {"R", "♖"}};


    private static void pintaTablero() {
        System.out.println();
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

    public DriverPeon() {

    }

    /*
     * Pre: Cierto
     * Post: Inicializa el tablero
     */
    public static void iniTablero() {
        for(int i = 0; i < 8; ++i)
            for(int j = 0; j < 8; ++j)
                estadoTablero[i][j] = '0';
    }

    private static void eliminarDuplicados(int posX, int posY) {
        for(int i = 0; i < PiezasBlancas.size(); ++i) {
            if(PiezasBlancas.get(i).getPosX() == posX && PiezasBlancas.get(i).getPosY() == posY) {
                PiezasBlancas.remove(i);
                break;
            }
        }
        for(int i = 0; i < PiezasNegras.size(); ++i) {
            if(PiezasNegras.get(i).getPosX() == posX && PiezasNegras.get(i).getPosY() == posY) {
                PiezasNegras.remove(i);
                break;
            }
        }
    }

    /*
     * Pre: Cierto
     * Post: Devuelve un objeto Caballo con atributos iguales a los parámetros de la funcion
     */

    public static Peon iniPieza(boolean esNegra, boolean firstMov,  String[] tmp) {
        Peon p = new Peon(esNegra, firstMov, Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
        eliminarDuplicados(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
        if(esNegra) {
            PiezasNegras.add(p);
            estadoTablero[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])] = 'p';
        }
        else  {
            PiezasBlancas.add(p);
            estadoTablero[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])] = 'P';
        }
        return p;
    }

    /*
     * Pre: Cierto
     * Post: Imprime por consola las posibles opciones a probar con el driver
     */
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Peon. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Pieza Peon");
        System.out.println("    2- Estado del tablero (para verificar que las piezas estén correctamente colocadas en el)");
        System.out.println("    3- Verificar función esMovimientoOK de la clase Peon");
        System.out.println("    4- Verificar función movimientosPosibles de la clase Peon");
        System.out.println("    5- Salir");
    }

    private static boolean verificarEntrada(String tmp[]) {
        int posX = Integer.parseInt(tmp[0]);
        int posY = Integer.parseInt(tmp[1]);
        if(posX >= 0 && posY >= 0 && posX < 8 && posY < 8) return true;
        return false;
    }

    public static void main(String[] args) {
        estadoTablero = new char[8][8];
        PiezasBlancas = new ArrayList<>();
        PiezasNegras = new ArrayList<>();
        move = new int[2];
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
                    boolean esNegraInput = false;
                    boolean firstMov = false;
                    System.out.println("Introduce, en orden y por terminal, los siguientes valores:");
                    boolean inputOK = false;
                    while(!inputOK) {
                        System.out.println("Indica si el color de la pieza es negra (true) o es blanca (false)");
                        String s = sc.nextLine();
                        if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                            if (s.equals("true")) { esNegraInput = true; }
                            else if (s.equals("false")) { esNegraInput = false;}
                            else System.out.println("Valor incorrecto.");
                        } else System.out.println("Valor incorrecto.");
                        System.out.println("Indica si es el primer movimiento del Peon (true) o no (false)");
                        s = sc.nextLine();
                        if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                            if (s.equals("true")) { firstMov = true; }
                            else if (s.equals("false")) { firstMov = false;}
                            else System.out.println("Valor incorrecto.");
                        } else System.out.println("Valor incorrecto.");
                        System.out.println("Indica la posicion de la pieza en el tablero. Representamos el tablero como una matriz. El extremo superior de la matriz sera la posicion 0 0 mentras que, la esquina inferior derecha, será la posicion 7 7:");
                        String tmp[] = sc.nextLine().split(" ");
                        if(verificarEntrada(tmp)) {
                            inputOK = true;
                            Peon p = iniPieza(esNegraInput, firstMov, tmp);
                            System.out.println("Objeto Peon creado con exito.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Este es el estado del tablero actual:");
                    pintaTablero();
                    break;
                case 3:
                    System.out.println("Introduce el movimiento a realizar (posicion inicial de la pieza y posicion final, separado por un espacio)");
                    String tmp = sc.nextLine();
                    String aux[] = tmp.split(" ");
                    Movimiento m = new Movimiento(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]), Integer.parseInt(aux[3]));
                    Pieza test = null;
                    if(Character.isUpperCase(estadoTablero[Integer.parseInt(aux[0])][Integer.parseInt(aux[1])])) { //pieza blanca
                        for(int i = 0; i < PiezasBlancas.size(); ++i) {
                            if(PiezasBlancas.get(i).getTipo() == 'P') test = PiezasBlancas.get(i);
                        }
                    }
                    else { //Pieza negra
                        for(int i = 0; i < PiezasNegras.size(); ++i) {
                            if(PiezasNegras.get(i).getTipo() == 'p') test = PiezasNegras.get(i);
                        }
                    }
                    if(test == null) { //no existe la pieza a probar
                        System.out.println("No existe ninguna pieza en la posición indicada");
                    }
                    else {
                        System.out.println("Que resultado esperas (true/false)?");
                        boolean resEsperado = Boolean.parseBoolean(sc.nextLine());
                        if(resEsperado == test.esMovimientoOk(m, estadoTablero)) System.out.println(ANSI_RED + "Test completado con exito"+ ANSI_RESET);
                        else System.out.println("Fallo en el test");
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Introduce, por terminal, la posicion de la pieza la cual quieres todos sus posibles movimientos");
                    String tmp2 = sc.nextLine();
                    String aux2[] = tmp2.split(" ");
                    int posX = Integer.parseInt(aux2[0]);
                    int posY = Integer.parseInt(aux2[1]);
                    Pieza test2 = null;
                    if(Character.isUpperCase(estadoTablero[posX][posY])) { //pieza blanca
                        for(int i = 0; i < PiezasBlancas.size(); ++i) {
                            if(PiezasBlancas.get(i).getTipo() == 'P' && PiezasBlancas.get(i).getPosX() == posX && PiezasBlancas.get(i).getPosY() == posY) test2 = PiezasBlancas.get(i);
                        }
                    }
                    else { //Pieza negra
                        for(int i = 0; i < PiezasNegras.size(); ++i) {
                            if(PiezasNegras.get(i).getTipo() == 'p' && PiezasNegras.get(i).getPosX() == posX && PiezasNegras.get(i).getPosY() == posY) test2 = PiezasNegras.get(i);
                        }
                    }
                    if(test2 == null) { //no existe la pieza a probar
                        System.out.println("No existe ninguna pieza en la posición indicada");
                    }
                    else {
                        System.out.println("Estos son todos los posibles movimientos de la pieza Caballo:");
                        ArrayList<Movimiento> res = test2.movimientosPosibles(estadoTablero);
                        for(int i = 0; i < res.size(); ++i) {
                            System.out.println("("+ "FromX: " + res.get(i).getFromX() + " FromY: " + res.get(i).getFromY() + " ToX: " + res.get(i).getToX() + " ToY: " + res.get(i).getToY() + " Pieza: " + res.get(i).getP() +")");
                        }
                    }
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