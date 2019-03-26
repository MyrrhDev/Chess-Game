package Domini;

import Domini.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DriverTorre {
    static private int estadoTablero[][] = new int[8][8];
    static private int move[];
    static File f;
    static Scanner sc;
    static Pieza verificar;
    static HashMap<Integer, Pieza> ph;

    public DriverTorre() {

    }

    public static void positionScanner(Scanner sc) {
        boolean cont = true;
        String s = sc.nextLine();
        //System.out.println("read: " + s);
        while (cont) {
            if(s.equals("begin tablero")) {
                cont = false;
                System.out.println("cont false");
            }
            else {
                s = sc.nextLine();
                //System.out.println("read: " + s);
            }
        }
    }

    /*
     * Pre: El fichero chessTowerTest.txt está ubicado en la ruta especificada
     * Post: Lee la entrada del fichero para parsearla a objetos utiles del driver
     */

    public static void readTableroFromFile(Scanner sc) throws Exception {
        int i = 0;
        boolean cont = true;
        String s = sc.nextLine();
        //System.out.println("read: " + s);
        while (cont) {
            int test2 = s.length();
            for(int j = 0; j < s.length(); j = j+2) {
                char c = s.charAt(j);
                int test = Character.getNumericValue(c);
                if(c != ' ' || c != '\n' && i < 8) {
                    estadoTablero[i][j/2] = Character.getNumericValue(c);
                }
            }
            ++i;
            s = sc.nextLine();
            //System.out.println("read en readTablero: " + s);
            if(s.equals("") || s.equals("\r") || s.equals("end tauler") || i == 7) cont = false; //en el editor de textos de ubuntu             //interpreta el enter como una linea vacia
        }
        /*for(int ii = 0; ii < 8; ++ii)
            for(int j = 0; j < 8; ++j)
                System.out.println(String.valueOf(estadoTablero[ii][j]) + ' ');*/
    }

    public static Torre iniPieza(boolean esNegra, String idPieza, int posX, int posY) {
        Torre t = new Torre(esNegra, idPieza, posX, posY);
        return t;
    }

    public static void readHashMapFromFile(Scanner sc) {
        boolean cont = true;
        //iteramos sobre el archivo hasta que encontramos el valor del HashMap
        String s = sc.nextLine();
        System.out.println("read: " + s);
        while (cont) {
            if(s.equals("begin HashMap")) {
                cont = false;
                System.out.println("cont false");
            }
            else {
                s = sc.nextLine();
                System.out.println("read: " + s);
            }
        }
        cont = true;
        s = sc.nextLine();
        System.out.println("read: " + s);
        boolean first = true;
        ph = new HashMap<>();
        while (cont) {
            if(s.equals("end HashMap")) {
                cont = false;
                System.out.println("cont false");
            }
            else {
                String aux[] = s.split(" ");
                Pieza p;
                //esNegra == true
                if(aux[2].equals("true")) p = iniPieza(true, aux[0], Integer.parseInt(aux[3]), Integer.parseInt(aux[4]));
                else p = iniPieza(false, aux[0], Integer.parseInt(aux[3]), Integer.parseInt(aux[4]));
                if(first) {
                    verificar = p;
                    first = false;
                }
                ph.put(Integer.parseInt(aux[1]), p);
                s = sc.nextLine();
                System.out.println("read: " + s);
            }
        }
    }

    public static void readMoveFromFile(Scanner sc) {
        String s = sc.nextLine();
        System.out.println("read: " + s);
        s = sc.nextLine();
        String aux[] = s.split(" ");
        move = new int[2];
        move[0] = Integer.parseInt(aux[2]);
        move[1] = Integer.parseInt(aux[3]);
    }

    public static boolean readExpectedOutput(Scanner sc) {
        String s = sc.nextLine();
        if(s.equals("true")) return true;
        return false;
    }

    public static boolean runTest(boolean expectedOutput) {
        if(verificar.esMovimientoOk(move[0], move[1], estadoTablero, ph) && expectedOutput) return true;
        return false;
    }

    public static void main(String[] args) {
        ph = new HashMap<>();
        estadoTablero = new int[8][8];
        move = new int[2];
        Scanner sc = new Scanner(System.in);
        boolean driverIsRunning = true;
        while(driverIsRunning) {
            System.out.println("Bienvenido al Driver de Torre. Selecciona qué deseas testear:");
            System.out.println("    1- Alta objeto Pieza Torre");
            System.out.println("    2- Introducir estado de tablero");
            System.out.println("    3- Verificar función esMovimientoOK de la clase Torre");
            System.out.println("    4- Salir");
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
                    String idPiezaInput = "";
                    while(!inputOK) {
                        System.out.println("Identificacion de la pieza (int)"); //@todo cambiar string a int
                        String s = sc.nextLine();
                        if (!s.equals("\r") && !s.equals("\n") && !s.equals("\t") && !s.equals("")) {
                            idPiezaInput = s;
                            System.out.println(idPiezaInput);
                            inputOK = true;
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
                    Torre t = iniPieza(esNegraInput, idPiezaInput, posXinput, posYinput);
                    System.out.println("Objeto torre creado con exito. Valores:");
                    ph.put(Integer.parseInt(t.getId()), t);
                    //System.out.println("color pieza: (true si es negra, false si es blanca)" +  +"id pieza: " + t.getId() + "");
                    break;
                case 2:
                    System.out.println("Introduce el estado del tablero. Se espera:");
                    System.out.println("0 si la casilla no contiene ninguna pieza");
                    System.out.println("id de la pieza si la casilla contiene la pieza");
                    try {
                        readTableroFromFile(sc);
                    }catch(Exception e) { }
                    break;
                case 3:
                    System.out.println("Introduce la id de la pieza a probar su movimiento");
                    String idPieza = sc.nextLine();
                    Pieza t2 = new Torre();
                    if(ph.containsKey(Integer.parseInt(idPieza))) {
                        t2 = ph.get(Integer.parseInt(idPieza));
                    }
                    else System.out.println("id incorrecta");
                    System.out.println("Introduce la posicion donde debe moverse [(0,0) .. (7,7)]");
                    String m = sc.nextLine();
                    String aux[] = m.split("");
                    move[0] = Integer.parseInt(aux[0]);
                    move[1] = Integer.parseInt(aux[2]);
                    System.out.println("Que resultado esperas (true/false)?");
                    boolean resEsperado = Boolean.parseBoolean(sc.nextLine());
                    if(resEsperado == t2.esMovimientoOk(move[0], move[1],estadoTablero,ph)) System.out.println("Test completado con exito");
                    else System.out.println("Fallo en el test");
                    break;
                case 4:
                    System.out.println("Ejecucion del driver terminada");
                    driverIsRunning = false;
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
            }
        }
        /*f = new File("/home/roger/Documentos/PROP/tests/chessTowerTest.txt");
        try {
            sc = new Scanner(f);
            positionScanner(sc);
            readTableroFromFile(sc);
            readHashMapFromFile(sc);
            readMoveFromFile(sc);
            boolean expectedOutput = readExpectedOutput(sc);
            boolean testResult = runTest(expectedOutput);
            if(testResult) System.out.println("Test successful");
            else System.out.println("Error");
        } catch(Exception e) {

        }*/
        /*Torre t = new Torre(true, "a", 3,3);
        Torre t1 = new Torre(false, "b", 3,6);
        HashMap<Integer, Pieza> piezasTablero = new HashMap<>();
        int estadoTablero[][] = new int[8][8];
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                if(i == 3 && j == 3) {
                    piezasTablero.put(1, t);
                    estadoTablero[i][j] = 1;
                }
                else if(i == 3 && j == 6) {
                    piezasTablero.put(2,t1);
                    estadoTablero[i][j] = 2;
                }
                else estadoTablero[i][j] = 0;
            }
        }

        boolean test = t.esMovimientoOk(3, 6, estadoTablero, piezasTablero);
        System.out.println(test);*/
    }
}
