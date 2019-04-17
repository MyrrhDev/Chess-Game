package Domini;

import java.util.Scanner;

public class DriverProblema {
    private static Scanner sc;

    public DriverProblema() {

    }

    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Torre. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Problema");
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
                    String tmp = sc.nextLine();
                    Problema p = new Problema();
                    p.setAbreJuego(tmp);
                    p.setFEN(tmp);
                    System.out.println("Problema creado con éxito, valores: " + p.getFEN() + ' ' + p.getAbreJuego());
                    break;
                case 2:
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
            }
        }
    }
}
