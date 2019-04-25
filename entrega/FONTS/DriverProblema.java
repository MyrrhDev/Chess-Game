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
        System.out.println("Bienvenido al Driver de Problema. Selecciona qué deseas testear:");
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
                    System.out.println("Introduce el FEN:");
                    String tmp = sc.nextLine();
                    p = new Problema();
                    //p.setIniJuegoBlancas(tmp);
                    p.setFEN(tmp);
                    System.out.println("Introduce el valor de N:");
                    tmp = sc.nextLine();
                    p.setN(Integer.parseInt(tmp));
                    System.out.println("Problema creado con éxito, valores: " + p.getFEN() + ' ' + p.getIniJuegoBlancas());
                    break;
                case 2:
                    System.out.println("Qué resultado esperas? true/false");
                    boolean resEsperado = Boolean.parseBoolean(sc.nextLine());
                    if(resEsperado == p.verificarProblema()) System.out.println(ANSI_RED + "Test completado con exito"+ ANSI_RESET);
                    else System.out.println("Fallo en el test");
                    p.verificarProblema();
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
