package Domini;

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
        System.out.println("read: " + s);
        while (cont) {
            if(s.equals("begin tablero")) {
                cont = false;
                System.out.println("cont false");
            }
            else {
                s = sc.nextLine();
                System.out.println("read: " + s);
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
        System.out.println("read: " + s);
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
            System.out.println("read en readTablero: " + s);
            if(s.equals("") || s.equals("\r") || s.equals("end tauler")) cont = false; //en el editor de textos de ubuntu             //interpreta el enter como una linea vacia
        }
        for(int ii = 0; ii < 8; ++ii)
            for(int j = 0; j < 8; ++j)
                System.out.println(String.valueOf(estadoTablero[ii][j]) + ' ');
    }

    public static Pieza iniPieza(boolean esNegra, String idPieza, int posX, int posY) {
        switch (idPieza) {
            case "t":
                Torre t = new Torre(esNegra, idPieza, posX, posY);
                return t;
            case "c":
                Caballo c = new Caballo();
                return c;
            case "a":
                Alfil a = new Alfil();
                return a;
            case "rey":
                Rey re = new Rey();
                return re;
            case"reina":
                Reina reina = new Reina();
                return reina;
            case "p":
                Peon p = new Peon();
                return p;
            default:
                return null;
        }
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
        f = new File("/home/roger/Documentos/PROP/tests/chessTowerTest.txt");
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

        }
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
