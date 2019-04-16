package Domini;

import java.util.Scanner;

public class DriverCtrl_dominio {
    static ctrl_dominio c = null;

    /*
     * Pre: Cierto
     * Post: Imprime por consola las posibles opciones a probar con el driver
     */
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de Torre. Selecciona qué deseas testear:");
        System.out.println("    1- Alta objeto Controlador de Dominio");
        System.out.println("    2- Alta objeto Persona");
        System.out.println("    3- Baja objeto Persona");
        System.out.println("    4- Login Persona");
        System.out.println("    5- Salir");
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
                    //creo un problema
                    break;
                case 2:
                    //jugar
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
                }
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
            }
        }
    }

}
