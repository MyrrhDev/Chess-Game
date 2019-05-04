package Domini;

import java.util.Scanner;

public class DriverEstrategiaSimple {
    private static void printMenuPrincipal() {
        System.out.println("Bienvenido al Driver de EstrategiaSimple. Hay que ejecutar el menu en el orden que se indica:");
        System.out.println("    1- Alta objeto EstrategiaSimple");
        System.out.println("    2- Verificar la función estrategiaSimple");
        System.out.println("    5- Salir");
    }

    private static String setFEN(String FEN) {
        String prob = "";
        int i = FEN.indexOf('w');
        if(i == -1) { //no existe el carácter w, verificamos que efectivamente empiezan las negras atacando
            i = FEN.indexOf('b');
        }
        int newLength = FEN.indexOf(' ');
        prob = FEN.substring(0, newLength);
        return prob;
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
                    System.out.println("Introduce la profundidad de la busqueda (maximo: 5)");
                    EstrategiaSimple es = new EstrategiaSimple(Integer.parseInt(sc.nextLine()));
                    if(es != null) System.out.println("Estrategia simple creada con exito");
                    else System.out.println("Error");
                    break;
                case 2:
                    System.out.println("Introduce un FEN para poblar el tablero");
                    Maquina m1 = new Maquina(true, false, true);
                    Maquina m2 = new Maquina(false, true, false);
                    System.out.println("Para verificar la funcion debemos primero crear un tablero");
                    Tablero t = new Tablero(m1, m2);
                    System.out.println("Introduce un FEN para poblar el tablero");
                    boolean go = false;
                    String s = "";
                    while(!go) {
                        String tocheck = sc.nextLine();
                        if(!tocheck.endsWith("- - 0 1")) {
                            System.out.println("El FEN no es correcto, intentalo de nuevo.");
                        } else {
                            s = tocheck;
                            go = true;
                        }
                    }
                    String r = setFEN(s);
                    t.FENToTablero(r, true);
                    System.out.println("Creando objeto estrategia simple. Profundidad default: 2");
                    EstrategiaSimple es2 = new EstrategiaSimple(2);
                    Movimiento mv = es2.estrategiaSimple(t);
                    System.out.println("El movimiento es:");
                    System.out.println(mv.getFromX() + " " + mv.getFromY() + " " + mv.getToX() + " " + mv.getToY());
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