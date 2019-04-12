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
                    c = ctrl_dominio.getInstance();
                    if(c != null) System.out.println("Alta controlador de dominio correcta");
                    else System.out.println("Error! Ya existe una instancia de controlador creada");
                    break;
                case 2:
                    System.out.println("Introduce el nombre de usuario y la contraseña del nuevo jugador");
                    String datos = sc.nextLine();
                    String tmp[] = datos.split(" ");
                    int personasSize = c.jugadoresSize();
                    int res = c.nuevaPersona(tmp[0], tmp[1]);
                    if(personasSize+1 == c.jugadoresSize() && res == 1) System.out.println("Nueva persona creada con éxito");
                    else System.out.println("Error al crear una nueva persona");
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Introduce el nombre de usuario y la contraseña, separados por un espacio");
                    datos = sc.nextLine();
                    String tmp2[] = datos.split(" ");
                    c.loginPersona(tmp2[0], tmp2[1]);
                    break;
                case 5:
                    c.logoutPersona();
                    System.out.println("Persona deslogueada con exito");
                    break;
                default:
                    System.out.println("La opción introducida no es correcta. Por favor, seleccione una de las siguiente del menu");
            }
        }
    }

}
