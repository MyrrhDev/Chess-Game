package Domini;

import java.util.Scanner;

public class DriverMovimiento {
    private static Movimiento m;
    private Tablero desdeTablero;
    private Tablero aTablero;
    private Movimiento movimientoPrueba;
    private boolean sePuede;

    public DriverMovimiento() {

    }

    /*
     * Pre: Cierto
     * Post: Imprime por consola las posibles opciones a probar con el driver
     */
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Movimiento. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Movimiento");
        System.out.println("    2- Consultar valores del objeto Movimiento");
        System.out.println("    5- Salir");
    }

    private static boolean verificarEntrada(String tmp[]) {
        int posX = Integer.parseInt(tmp[0]);
        int posY = Integer.parseInt(tmp[1]);
        if(posX >= 0 && posY >= 0 && posX < 8 && posY < 8) return true;
        return false;
    }

    public static void main(String[] args) {
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
                    break;
                case 2:
                    System.out.println("fromX: " + m.getFromX());
                    System.out.println("fromY: " + m.getFromY());
                    System.out.println("ToX: " + m.getToX());
                    System.out.println("ToY: " + m.getToY());
                    System.out.println("p: " + m.getP());
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