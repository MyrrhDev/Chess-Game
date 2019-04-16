package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DriverCaballo {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    static private char estadoTablero[][] = new char[8][8];
    private static ArrayList <Pieza> PiezasBlancas;
    private static ArrayList <Pieza> PiezasNegras;

    public DriverCaballo() {

    }

    /*
     * Pre: Cierto
     * Post: Lee el estado del tablero desde la terminal
     */
    public static void readTableroFromTerminal(Scanner sc) throws Exception {
        int i = 0;
        while (i < 8) {
            String s = sc.nextLine();
            char[] chr = s.toCharArray();
            for(int j = 0; j < 8; ++j) {
                estadoTablero[i][j] = chr[j*2];
            }
            ++i;
        }
        /*for(i = 0; i < 8; ++i)
            for(int j = 0; j < 8; ++j)
                System.out.println(estadoTablero[i][j]);*/
    }

    /*
     * Pre: Cierto
     * Post: Devuelve un objeto Caballo con atributos iguales a los parámetros de la funcion
     */

    public static Caballo iniPieza(boolean esNegra) {
        Caballo t = new Caballo(esNegra);
        if(esNegra) PiezasNegras.add(t);
        else PiezasNegras.add(t);
        return t;
    }

    /*
     * Pre: Cierto
     * Post: Imprime por consola las posibles opciones a probar con el driver
     */
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Caballo. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Pieza Caballo");
        System.out.println("    2- Introducir estado de tablero");
        System.out.println("    3- Verificar función esMovimientoOK de la clase Caballo");
        System.out.println("    4- Verificar función movimientosPosibles de la clase Caballo");
        System.out.println("    5- Salir");
    }

    public static void main(String[] args) {
        estadoTablero = new char[8][8];
        PiezasBlancas = new ArrayList<Pieza>();
        PiezasNegras = new ArrayList<Pieza>();
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
                    System.out.println("Introduce, en orden y por terminal, los siguientes valores:");
                    boolean inputOK = false;
                    while(!inputOK) {
                        System.out.println("Indica si el color de la pieza es negra (true) o es blanca (false)");
                        String s = sc.nextLine();
                        if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                            if (s.equals("true")) { esNegraInput = true; inputOK = true; }
                            else if (s.equals("false")) { esNegraInput = false; inputOK = true; }
                            else System.out.println("Valor incorrecto.");
                        } else System.out.println("Valor incorrecto.");
                    }
                    Caballo c = iniPieza(esNegraInput);
                    System.out.println("Objeto caballo creado con exito.");
                    break;
                case 2:
                    System.out.println("Introduce el estado del tablero. Se espera:");
                    System.out.println("0 si la casilla no contiene ninguna pieza");
                    System.out.println("tipo de pieza, en formato FEN, que contiene la casilla");
                    try {
                        readTableroFromTerminal(sc);
                    }catch(Exception e) { }
                    break;
                case 3:
                    System.out.println("Introduce el movimiento a realizar (posicion inicial de la pieza y posicion final, separado por un espacio)");
                    String tmp = sc.nextLine();
                    String aux[] = tmp.split(" ");
                    Movimiento m = new Movimiento(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]), Integer.parseInt(aux[3]));
                    Pieza test = null;
                    if(Character.isUpperCase(estadoTablero[Integer.parseInt(aux[0])][Integer.parseInt(aux[1])])) { //pieza blanca
                        for(int i = 0; i < PiezasBlancas.size(); ++i) {
                            if(PiezasBlancas.get(i).getTipo() == 'N') test = PiezasBlancas.get(i);
                        }
                    }
                    else { //Pieza negra
                        for(int i = 0; i < PiezasNegras.size(); ++i) {
                            if(PiezasNegras.get(i).getTipo() == 'n') test = PiezasNegras.get(i);
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
                    Movimiento m2 = new Movimiento(Integer.parseInt(aux2[0]), Integer.parseInt(aux2[1]));
                    Pieza test2 = null;
                    if(Character.isUpperCase(estadoTablero[Integer.parseInt(aux2[0])][Integer.parseInt(aux2[1])])) { //pieza blanca
                        for(int i = 0; i < PiezasBlancas.size(); ++i) {
                            if(PiezasBlancas.get(i).getTipo() == 'N') test2 = PiezasBlancas.get(i);
                        }
                    }
                    else { //Pieza negra
                        for(int i = 0; i < PiezasNegras.size(); ++i) {
                            if(PiezasNegras.get(i).getTipo() == 'n') test2 = PiezasNegras.get(i);
                        }
                    }
                    if(test2 == null) { //no existe la pieza a probar
                        System.out.println("No existe ninguna pieza en la posición indicada");
                    }
                    else {
                        System.out.println("Estos son todos los posibles movimientos de la pieza Alfil:");
                        ArrayList<Movimiento> res = test2.movimientosPosibles(m2, estadoTablero);
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
