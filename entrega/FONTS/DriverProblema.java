package Domini;

import java.util.Scanner;

public class DriverProblema {
    private static Scanner sc;
    private static Problema p;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public DriverProblema() {

    }

    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Problema. Para testear el driver es necesario seguir el orden siguiente:");
        System.out.println("    1- Alta objeto Problema");
        System.out.println("    2- Verificar problema");
        System.out.println("    5- Salir");
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
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
                    //creo un problema
                    String tmp;
                    System.out.println("Introduce el FEN:");
                    boolean go = false;
                    while(!go) {
                        String tocheck = sc.nextLine();
                        if(!tocheck.endsWith("- - 0 1")) {
                            System.out.println("El FEN no es correcto, intentalo de nuevo.");
                        } else {
                            tmp = tocheck;
                            go = true;
                        }
                    }
                    p = new Problema();
                    //p.setIniJuegoBlancas(tmp);
                    p.setFEN(tmp);
                    System.out.println("Introduce el valor de N:");
                    go = false;
                    while(!go) {
                        String tocheck = sc.nextLine();
                        char c = tocheck.charAt(0);
                        if (!(Character.isDigit(c))) {
                            System.out.println("El N no es correcto, intentalo de nuevo.");
                        } else {
                            tmp = Integer.parseInt(tocheck);
                            go = true;
                        }
                    }
                    p.setN(Integer.parseInt(tmp));
                    System.out.println("Problema creado con éxito, valores: " + p.getFEN() + ' ' + p.getIniJuegoBlancas());
                    break;
                case 2:
                    if(p != null) {
                        System.out.println("Qué resultado esperas? true/false");
                        System.out.println("Calculando solucion. Espera");
                        boolean resEsperado = Boolean.parseBoolean(sc.nextLine());
                        if (resEsperado == p.verificarProblema())
                            System.out.println(ANSI_RED + "Test completado con exito" + ANSI_RESET);
                        else System.out.println("Fallo en el test");
                        p.verificarProblema();
                    }
                    else {
                        System.out.println("Debes introducir primero un problema (opcion 1)");
                    }
                    break;
                case 5:
                    driverIsRunning = false;
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
            }
        }
    }
}
