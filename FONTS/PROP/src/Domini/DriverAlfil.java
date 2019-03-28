package Domini;

import java.util.HashMap;
import java.util.Scanner;

public class DriverAlfil {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    static private int estadoTablero[][] = new int[8][8];
    static private int move[];
    static HashMap<Integer, Pieza> ph;

    public DriverAlfil() {

    }

    /*
     * Pre: El fichero chessTowerTest.txt está ubicado en la ruta especificada
     * Post: Lee la entrada del fichero para parsearla a objetos utiles del driver
     */
    public static void readTableroFromTerminal(Scanner sc) throws Exception {
        int i = 0;
        while (i < 8) {
            String s = sc.nextLine();
            for(int j = 0; j < s.length(); j = j+2) {
                char c = s.charAt(j);
                int test = Character.getNumericValue(c);
                if(c != ' ' || c != '\n' && i < 8) {
                    estadoTablero[i][j/2] = Character.getNumericValue(c);
                }
            }
            ++i;
        }
        /*for(i = 0; i < 8; ++i)
            for(int j = 0; j < 8; ++j)
                System.out.println(estadoTablero[i][j]);*/
    }

    /*
     * Pre: Cierto
     * Post: Devuelve un objeto Alfil con atributos iguales a los parámetros de la funcion
     */

    public static Alfil iniPieza(boolean esNegra, Integer idPieza, int posX, int posY) {
        Alfil a = new Alfil(esNegra, idPieza, posX, posY);
        return a;
    }

    /*
     * Pre: Cierto
     * Post: Imprime por consola las posibles opciones a probar con el driver
     */
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Alfil. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Pieza Alfil");
        System.out.println("    2- Introducir estado de tablero");
        System.out.println("    3- Verificar función esMovimientoOK de la clase Alfil");
        System.out.println("    4- Salir");
    }

    public static void main(String[] args) {
        ph = new HashMap<>();
        estadoTablero = new int[8][8];
        move = new int[2];
        Scanner sc = new Scanner(System.in);
        boolean driverIsRunning = true;
        while(driverIsRunning) {
            printMenuPrincipal();
            String input = sc.nextLine();
            int op;
            if(!input.equals("\r") && !input.equals("\n") && !input.equals("\t") && !input.equals("")
                    &&(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4"))) {
                op = Integer.parseInt(input);
            }
            else op = -1;
            switch(op) {
                case 1:
                    boolean esNegraInput = false;
                    System.out.println("Introduce, en orden y por terminal, los siguientes valores:");
                    boolean inputOK = false;
                    Integer idPiezaInput = -1;
                    while(!inputOK) {
                        System.out.println("Identificacion de la pieza (int)");
                        String s = sc.nextLine();
                        if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                            idPiezaInput = Integer.parseInt(s);
                            inputOK = true;
                        } else System.out.println("Valor incorrecto.");
                    }
                    inputOK = false;
                    while(!inputOK) {
                        System.out.println("Indica si el color de la pieza es negro (true) o es blanco (false)");
                        String s = sc.nextLine();
                        if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                            if (s.equals("true")) { esNegraInput = true; inputOK = true; }
                            else if (s.equals("false")) { esNegraInput = false; inputOK = true; }
                            else System.out.println("Valor incorrecto.");
                        } else System.out.println("Valor incorrecto.");
                    }
                    inputOK = false;
                    int posXinput = -1, posYinput = -1;
                    while(!inputOK) {
                        System.out.println("Posición de la pieza en el tablero. Valores posibles: [(0,0) ... (7,7)]");
                        String s = sc.nextLine();
                        if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                            String aux[] = new String[3];
                            aux = s.split("");
                            posXinput = Integer.parseInt(aux[0]);
                            posYinput = Integer.parseInt(aux[2]);
                            if(posXinput >= 0 && posYinput >= 0 && posXinput < 8 && posYinput < 8) inputOK = true;
                            else System.out.println("La posicion de la pieza en el tablero debe estar entre (0,0) y (7,7)");
                        } else System.out.println("Valor incorrecto.");
                    }
                    Alfil a = iniPieza(esNegraInput, idPiezaInput, posXinput, posYinput);
                    System.out.println("Objeto alfil creado con exito. Valores:");
                    ph.put(a.getId(), a);
                    break;
                case 2:
                    System.out.println("Introduce el estado del tablero. Se espera:");
                    System.out.println("0 si la casilla no contiene ninguna pieza");
                    System.out.println("id de la pieza si la casilla contiene la pieza");
                    try {
                        readTableroFromTerminal(sc);
                    }catch(Exception e) { }
                    break;
                case 3:
                    System.out.println("Introduce la id de la pieza a probar su movimiento");
                    String idPieza = sc.nextLine();
                    Pieza a2 = new Alfil();
                    if(ph.containsKey(Integer.parseInt(idPieza))) {
                        a2 = ph.get(Integer.parseInt(idPieza));
                    }
                    else System.out.println("id incorrecta");
                    System.out.println("Introduce la posicion donde debe moverse [(0,0) .. (7,7)]");
                    String m = sc.nextLine();
                    String aux[] = m.split("");
                    move[0] = Integer.parseInt(aux[0]);
                    move[1] = Integer.parseInt(aux[2]);
                    System.out.println("Que resultado esperas (true/false)?");
                    boolean resEsperado = Boolean.parseBoolean(sc.nextLine());
                    //a2.esMovimientoOk(move[0], move[1],estadoTablero,ph)
                    if(resEsperado == a2.esMovimientoOk(move[0], move[1],estadoTablero,ph)) System.out.println(ANSI_RED + "Test completado con exito"+ ANSI_RESET);
                    else System.out.println("Fallo en el test");
                    /*boolean resMovimientoOk = a2.esMovimientoOk(move[0], move[1],estadoTablero,ph);
                    boolean result = (resEsperado == resMovimientoOk);
                    int iu = -1;*/
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Ejecucion del driver terminada");
                    driverIsRunning = false;
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
            }
        }
    }
}
